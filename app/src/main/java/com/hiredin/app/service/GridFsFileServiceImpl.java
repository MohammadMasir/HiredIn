package com.hiredin.app.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hiredin.app.repository.FileServiceInterface;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
//@Primary
public class GridFsFileServiceImpl implements FileServiceInterface {
    private final GridFsTemplate gridFsTemplate;
    
    @Autowired
    public GridFsFileServiceImpl(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }
    
    @Override
    public String storeFile(MultipartFile file) {
        try {
            ObjectId fileId = gridFsTemplate.store(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType()
            );
            return fileId.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
    
    @Override
    public Binary convertToBinary(MultipartFile file) throws IOException {
        return new Binary(BsonBinarySubType.BINARY, file.getBytes());
    }
    
    @Override
    public Resource loadFileAsResource(String fileId) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
        if (gridFSFile == null) {
            throw new RuntimeException("File not found");
        }
        
        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);
        return resource;
    }
}
