package com.mackittipat.s3ui.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.mackittipat.s3ui.config.bean.S3ServerPropertiesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private S3ServerPropertiesBean s3ServerPropertiesBean;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<String> serverNameList = s3ServerPropertiesBean.getServer().entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        model.addAttribute("serverNameList", serverNameList);
        return "index";
    }

    @RequestMapping(value = "/{serverName}/buckets")
    public String listBucket(Model model, @PathVariable String serverName) {
        Map<String, String> serverMap = (Map<String, String>) s3ServerPropertiesBean.getServer().get(serverName);
        String url = serverMap.get("url");
        String accessKeyId = serverMap.get("access-key-id");
        String secretKey = serverMap.get("secret-key");

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretKey);
        AmazonS3Client amazonS3Client = (AmazonS3Client) applicationContext.getBean("amazonS3Client", awsCredentials);
        amazonS3Client.setEndpoint(url);

        model.addAttribute("bucketList", amazonS3Client.listBuckets());
        return "bucket";
    }

    @RequestMapping(value = "/{serverName}/buckets/{bucketName}")
    public String listBucketObject(Model model, @PathVariable String serverName, @PathVariable String bucketName) {
        AmazonS3Client amazonS3Client = (AmazonS3Client) applicationContext.getBean("amazonS3Client");
        model.addAttribute("objectSummaryList", amazonS3Client.listObjects(bucketName).getObjectSummaries());
        return "bucket_object";
    }
}
