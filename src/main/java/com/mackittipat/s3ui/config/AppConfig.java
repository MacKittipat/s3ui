package com.mackittipat.s3ui.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials("", "");
    }

    @Bean
    @Lazy
    public AmazonS3Client amazonS3Client(AWSCredentials awsCredentials) {
        return new AmazonS3Client(awsCredentials);
    }
}
