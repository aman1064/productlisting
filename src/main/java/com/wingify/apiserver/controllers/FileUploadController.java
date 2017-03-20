package com.wingify.apiserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wingify.apiserver.entities.GenericResponse;
import com.wingify.apiserver.service.impl.AmazonS3FileUploadService;

import io.swagger.annotations.Api;

@CrossOrigin
@RestController
@Api(description="Api to upload any file to S3. This will upload the file and return https url")
public class FileUploadController {
	@Autowired
	private AmazonS3FileUploadService fileUploadService;

	@RequestMapping(value = "/api/file/upload", method = RequestMethod.POST)
	public GenericResponse<String> uploadImage(@RequestParam(value = "file") MultipartFile multiPartFile)
			throws Exception {
		return new GenericResponse<String>(fileUploadService.uploadFile(multiPartFile));
	}
}
