package com.aws.projects.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aws.projects.service.S3UploadService;

public class S3UploadRunner {

	private static Logger log = LogManager.getLogger(S3UploadRunner.class);
	
	public static void main(String[] args) {
		log.debug("S3UploadRunner - START");
		System.out.println("S3UploadRunner - START");
		try {
			S3UploadService uploadService = new S3UploadService();
			uploadService.uploadFile();
		} catch(Exception ex) {
			log.error("An error occurred while S3 file upload >> "+ex.getMessage());
		}
		System.out.println("S3UploadRunner - END");
		log.debug("S3UploadRunner - END");
	}
}
