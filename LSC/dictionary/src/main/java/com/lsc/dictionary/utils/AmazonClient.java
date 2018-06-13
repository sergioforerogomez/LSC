package com.lsc.dictionary.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AmazonClient {
    private AmazonS3 amazonS3;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazon.accessKey.path}")
    private String accessKeyPath;
    @Value("${amazon.secretKey.path}")
    private String secretKeyPath;

    @PostConstruct
    private void initialize() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(Utils.getAccessKeyFromFile(accessKeyPath), Utils.getSecretKeyFromFile(secretKeyPath));
        this.amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(Regions.US_EAST_1).build();
    }

    private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }

    private void uploadFileTos3bucket(File file, String bucket, String fileName) {
        this.amazonS3.putObject(new PutObjectRequest(this.bucketName + "/" + bucket, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile, String bucket) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            fileUrl = this.endpointUrl + "/" + this.bucketName + "/" + bucket + "/" + multipartFile.getOriginalFilename();
            uploadFileTos3bucket(file, bucket, multipartFile.getOriginalFilename());
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    public void deleteFile(String bucket, String fileName) {
        try {
            this.amazonS3.deleteObject(this.bucketName, bucket + "/" + fileName);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }
    }
}