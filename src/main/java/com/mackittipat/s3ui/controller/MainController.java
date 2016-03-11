package com.mackittipat.s3ui.controller;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.mackittipat.s3ui.config.bean.S3ServerPropertiesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private S3ServerPropertiesBean s3ServerPropertiesBean;

    @RequestMapping(value = "/")
    public String showServerList(Model model) {
        List<String> serverNameList = s3ServerPropertiesBean.getServer().entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        model.addAttribute("serverNameList", serverNameList);
        return "server";
    }

    @RequestMapping(value = "/{serverName}")
    public String showBucketList(Model model, @PathVariable String serverName) {
        AmazonS3Client amazonS3Client = createAmazonS3Client(serverName);
        model.addAttribute("bucketList", amazonS3Client.listBuckets());
        return "bucket";
    }

    @RequestMapping(value = "/{serverName}/{bucketName}")
    public String showObjectList(Model model, @PathVariable String serverName, @PathVariable String bucketName) {
        AmazonS3Client amazonS3Client = createAmazonS3Client(serverName);
        model.addAttribute("objectSummaryList", amazonS3Client.listObjects(bucketName).getObjectSummaries());
        return "bucket_object";
    }

    @RequestMapping(value = "/{serverName}/{bucketName}/object")
    public String showObject(Model model,
                             @PathVariable String serverName,
                             @PathVariable String bucketName,
                             @RequestParam String objectKey) {
        AmazonS3Client amazonS3Client = createAmazonS3Client(serverName);

        S3Object s3Object = amazonS3Client.getObject(bucketName, objectKey);
        model.addAttribute("s3Object", s3Object);

        Map<String, String> s3ObjectMetaDataMap = s3Object.getObjectMetadata().getRawMetadata().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toString()));
        model.addAttribute("s3ObjectMetaDataMap", s3ObjectMetaDataMap);

        return "object";
    }

    private AmazonS3Client createAmazonS3Client(String serverName) {
        Map<String, String> serverMap = (Map<String, String>) s3ServerPropertiesBean.getServer().get(serverName);
        String url = serverMap.get("url");
        String accessKeyId = serverMap.get("access-key-id");
        String secretKey = serverMap.get("secret-key");

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("S3SignerType");

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretKey);

        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials, clientConfiguration);
        amazonS3Client.setEndpoint(url);

        return amazonS3Client;
    }
}
