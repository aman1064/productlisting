package com.wingify.apiserver.service.impl;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonS3FileUploadService {
	private AmazonS3 amazonS3Client = null;
	@Value(value = "${aws.access.key}")
	private String accessKey;

	@Value(value = "${aws.secret.key}")
	private String secretKey;
	

	@SuppressWarnings("all")
	@PostConstruct
	public void initAmazonS3Client() {
		BasicAWSCredentials creds = new BasicAWSCredentials(accessKey,
				secretKey);
		amazonS3Client = new AmazonS3Client(creds);
	}

	public String uploadFile(MultipartFile file) throws Exception {
		return uploadFileToS3(file);
	}

	private String uploadFileToS3(MultipartFile file) throws Exception{
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentType(file.getContentType());
		String randomImageName = UUID.randomUUID().toString();
		String arr[] = file.getOriginalFilename().split("\\.");
		String fileName = getName(randomImageName, arr);
		PutObjectRequest request = new PutObjectRequest("wingifytest", fileName, file.getInputStream(),objectMeta)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		amazonS3Client.putObject(request);
		return amazonS3Client.getUrl("wingifytest", fileName).toString();
	}

	private String getName(String randomImagePath, String arr[]) {
		return randomImagePath + "." + arr[arr.length - 1];
	}
}
