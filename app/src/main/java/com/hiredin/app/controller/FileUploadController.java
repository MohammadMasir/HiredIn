package com.hiredin.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hiredin.app.service.CloudinaryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/hiredin/application")
public class FileUploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Operation(
        summary = "Upload a file to Cloudinary",
        description = "Uploads PDF or image file to Cloudinary storage"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "File uploaded successfully",
        content = @Content(mediaType = "application/json")
    )
    @PostMapping(
        value = "/upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> uploadFile(
            @Parameter(
                description = "File to upload",
                required = true,
                content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestParam("file") MultipartFile file) {
        
        Map<String, Object> result = cloudinaryService.uploadFile(file);
        return ResponseEntity.ok(result);
    }
}