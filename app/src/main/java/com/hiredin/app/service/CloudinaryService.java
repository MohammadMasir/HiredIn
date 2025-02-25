package com.hiredin.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    @SuppressWarnings("unchecked")
    public Map<String, Object> uploadFile(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map<String, Object> params = ObjectUtils.asMap(
                "resource_type", "auto", // Auto-detect if it's an image or PDF
                "public_id", "files/" + UUID.randomUUID().toString() // Organize files in a folder with unique names
            );
            
            // Cast the result explicitly to Map<String, Object>
            Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(uploadedFile, params);
            uploadedFile.delete(); // Remove the file locally after uploading
            return uploadResult;
        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }
    
    // Method for deleting files with explicit casting and suppressed warning
    @SuppressWarnings("unchecked")
    public Map<String, Object> deleteFile(String publicId) {
        try {
            // Cast the result explicitly to Map<String, Object>
            return (Map<String, Object>) cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException("File deletion failed: " + e.getMessage());
        }
    }
}