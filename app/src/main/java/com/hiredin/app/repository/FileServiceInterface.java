package com.hiredin.app.repository;

import java.io.IOException;

import org.bson.types.Binary;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

//FileService.java
public interface FileServiceInterface {
	String storeFile(MultipartFile file);
	Binary convertToBinary(MultipartFile file) throws IOException;
	Resource loadFileAsResource(String fileId);
}