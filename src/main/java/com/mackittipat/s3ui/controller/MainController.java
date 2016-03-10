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
import org.springframework.web.bind.annotation.ResponseBody;

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


}
