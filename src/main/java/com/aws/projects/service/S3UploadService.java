package com.aws.projects.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.AmazonServiceException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.aws.projects.common.S3Constant;

public class S3UploadService {
	
	private static Logger log = LogManager.getLogger(S3UploadService.class);
			
	public final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
			.withRegion(Regions.US_EAST_2)
			.withCredentials(new DefaultAWSCredentialsProviderChain()).build();

	public void uploadFile() throws Exception {
		try {
			String bucketName = S3Constant.BUCKET_NAME;
			int count = new Random().nextInt(100);
			log.debug("file index: "+count);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MMM/dd");
			Date date = new Date();
			String bucketPrefix = simpleDateFormat.format(date);
			log.debug("bucket prefix: "+bucketPrefix);
			
			if(s3Client.doesBucketExistV2(bucketName)) {
				log.debug("S3 bucket already exists >> "+ bucketName);
			} else {
				s3Client.createBucket(bucketName);
				log.debug("Successfully created S3 bucket >> "+ bucketName);
			}
			s3Client.putObject(bucketName, bucketPrefix+"/test_"+count+".txt", "This is a test file of index: "+count);
		} catch(AmazonServiceException ex) {
			log.error("An AWS exception occurred >> "+ ex.getMessage());
			throw new Exception("An AWS exception occurred");
		}
	}
}
