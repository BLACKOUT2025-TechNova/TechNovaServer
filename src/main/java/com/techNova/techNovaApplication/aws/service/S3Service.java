package com.techNova.techNovaApplication.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3Service {

    private final AmazonS3 s3Client;

    public S3Service() {
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-1")
                .build();
    }

    public void uploadFile(String bucketName, String fileName, InputStream inputStream, long contentLength) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        s3Client.putObject(bucketName, fileName, inputStream, metadata);
    }

    public InputStream downloadFile(String bucketName, String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        return s3Object.getObjectContent();
    }
}
