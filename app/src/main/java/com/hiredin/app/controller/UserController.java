package com.hiredin.app.controller;

import java.io.IOException;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hiredin.app.model.User;
import com.hiredin.app.repository.FileServiceInterface;
import com.hiredin.app.repository.UserServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name="User Management", description="Endpoints for managing users")
public class UserController {
	
	private final UserServiceInterface userService ;
	private final FileServiceInterface fileService;
	
    @Autowired
    public UserController(UserServiceInterface userService, FileServiceInterface fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }
	
	@GetMapping
	public ResponseEntity<?> allUser(){
		List<User> users = userService.getAllUsers();
		try {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> newUser(@RequestBody User user){
		if(userService.createUser(user)) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable String id) {
		try {
			return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/by/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable String email) {
		try {
			return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id,@RequestBody User user){
		if (userService.updateUser(id, user)) {
			User updatedUSer = userService.getUserById(id);
			return new ResponseEntity<>(updatedUSer,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id){
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
    @PostMapping(
    		value = "/{id}/profile-picture",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload profile picture", description = "Upload a profile picture for a user")
    public ResponseEntity<String> uploadProfilePicture(
        @PathVariable String id,
        @Parameter(
                description = "Image to upload",
                required = true,
                content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestParam("file") MultipartFile file
    ) {
        User user = userService.getUserById(id);
        
        try {
            // Option 1: Store in MongoDB directly
//            Binary pictureBinary = fileService.convertToBinary(file); // Directly..
//            user.setProfilePicture(pictureBinary);
        	
        	//Storing as Resource for by FieldId
//        	String fieldId =  fileService.storeFile(file);
//            user.setProfilePictureUrl(fieldId);
//        	 resourceInGrid = fileService.loadFileAsResource(fieldId);
            
//             Option 2: Store in Cloudinary
            String pictureUrl = fileService.storeFile(file);
            user.setProfilePictureUrl(pictureUrl);
            
            userService.updateUser(id, user);
            return ResponseEntity.ok("Profile picture uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to upload profile picture");
        }
    }
}
