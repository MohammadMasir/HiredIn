package com.hiredin.app.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiredin.app.model.User;
import com.hiredin.app.repository.UserRepository;
import com.hiredin.app.repository.UserServiceInterface;

@Service
public class UserServiceImpl implements UserServiceInterface {
    private final UserRepository userRepository;
    
    
    private ObjectId id;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public Boolean createUser(User user) {
		userRepository.save(user);
		return true;
	}

	@Override
	public User getUserById(String id) {
		this.id = new ObjectId(id);
		return userRepository.findById(this.id).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public Boolean updateUserEFL(String id, User updatedUser) {
		User oldUser = getUserById(id);
		if (oldUser != null) {
			oldUser.setEmail(updatedUser.getEmail() != null && !updatedUser.getEmail().equals("") ? updatedUser.getEmail() : oldUser.getEmail());
			oldUser.setFirstName(updatedUser.getFirstName() != null && !updatedUser.getFirstName().equals("") ? updatedUser.getFirstName() : oldUser.getFirstName());
			oldUser.setLastName(updatedUser.getLastName() != null && !updatedUser.getLastName().equals("") ? updatedUser.getLastName() : oldUser.getLastName());
			
			userRepository.save(oldUser);
			return true;
		}
		return false;
	}

	@Override
	public void deleteUser(String id) {
		this.id = new ObjectId(id);
		userRepository.deleteById(this.id);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			return null;
		}
		return user;
	}
	
	public Boolean updateProfilePic(String id, User updatedUser, String oldUrl) {
		User oldUser = getUserById(id);
		oldUser.setProfilePictureUrl(updatedUser.getProfilePictureUrl() != null && !updatedUser.getProfilePictureUrl().equals("") ? updatedUser.getProfilePictureUrl() : oldUser.getProfilePictureUrl());
		
		userRepository.save(oldUser);
		return true;
	}

}