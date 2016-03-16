package com.mackittipat.s3ui.controller;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
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
    public String showBucketList(Model model,
                                 @PathVariable String serverName) {
        AmazonS3Client amazonS3Client = createAmazonS3Client(serverName);
        model.addAttribute("bucketList", amazonS3Client.listBuckets());
        return "bucket";
    }

    @RequestMapping(value = "/{serverName}/{bucketName}")
    public String showObjectList(Model model,
                                 @PathVariable String serverName,
                                 @PathVariable String bucketName,
                                 @RequestParam(required = false) String nextMarker) {
        AmazonS3Client amazonS3Client = createAmazonS3Client(serverName);

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucketName);
        listObjectsRequest.setMaxKeys(4);
        if(nextMarker != null) {
            listObjectsRequest.setMarker(nextMarker);
        }

        ObjectListing objectListing = amazonS3Client.listObjects(listObjectsRequest);
        List<S3ObjectSummary> s3ObjectSummaryList = objectListing.getObjectSummaries();
        model.addAttribute("objectSummaryList", s3ObjectSummaryList);

        if(objectListing.isTruncated()) {
            model.addAttribute("nextMarker", objectListing.getNextMarker());
        }

        return "object";
    }

    @RequestMapping(value = "/{serverName}/{bucketName}/object")
    public String showObjectInfo(Model model,
                                 @PathVariable String serverName,
                                 @PathVariable String bucketName,
                                 @RequestParam String key) {
        AmazonS3Client amazonS3Client = createAmazonS3Client(serverName);

        S3Object s3Object = amazonS3Client.getObject(bucketName, key);
        model.addAttribute("s3Object", s3Object);

        Map<String, String> s3ObjectMetaDataMap = s3Object.getObjectMetadata().getRawMetadata().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toString()));
        model.addAttribute("s3ObjectMetaDataMap", s3ObjectMetaDataMap);

        model.addAttribute("key", key);
        return "object_info";
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
