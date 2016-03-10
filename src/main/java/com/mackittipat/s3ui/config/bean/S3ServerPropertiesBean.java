package com.mackittipat.s3ui.config.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "s3ui")
public class S3ServerPropertiesBean {

    private Map<String, Object> server = new LinkedHashMap();

    public Map<String, Object> getServer() {
        return server;
    }

    public void setServer(Map<String, Object> server) {
        this.server = server;
    }
}
