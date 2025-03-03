package com.hiredin.app.service;

import java.io.IOException;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.bson.types.Binary;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hiredin.app.repository.FileServiceInterface;

@Service
@Primary
public class CloudinaryFileServiceImpl implements FileServiceInterface {
    private final Cloudinary cloudinary;
    
    public CloudinaryFileServiceImpl() {
        // Initialize with your Cloudinary credentials
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dcv6fsl1w",
            "api_key", "429399431649335",
            "api_secret", "ruKnJACLQpbfFU5ur4-RtX_UtF0"
        ));
    }
    
    @Override
    public String storeFile(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto")
            );
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload to Cloudinary", e);
        }
    }

	@Override
	public Binary convertToBinary(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadFileAsResource(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}
    
    // Implement other methods...
}
