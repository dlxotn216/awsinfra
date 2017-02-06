package com.taesu.test.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by leetaesu on 2017-01-26.
 *
 * @ClassName AwsConfiguration
 * @Description AWS와 연동을 위한 설정 클래스
 * AWS S3 서비스와 연동되어 있으며
 * 필요한 정보는 /resources/properties/awsconfig.properties의 키 값을 이용한다
 */
@Configuration
@PropertySource("classpath:properties/awsconfig.properties")
public class AwsConfiguration {

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @Value("${aws.s3.region}")
    private String region;

    //PropertySource 애노테이션 이용을 위함
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer configurer =
                    new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        //System.out.println("DEBUG CHECK ACCESSKEY :"+accessKey);
        //System.out.println("DEBUG CHECK SECRETEKEY: "+secretKey);
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public AmazonS3Client amazonS3Client(AWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2));
        return amazonS3Client;
    }
}