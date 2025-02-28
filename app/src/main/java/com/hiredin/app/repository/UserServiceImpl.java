package com.hiredin.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiredin.app.model.User;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
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
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public Boolean updateUser(String id, User updatedUser) {
		User oldUser = getUserById(id);
		if (oldUser != null || !oldUser.equals("")) {
			oldUser.setEmail(updatedUser.getEmail() != null && !updatedUser.equals("") ? updatedUser.getEmail() : oldUser.getEmail());
			oldUser.setFirstName(updatedUser.getFirstName() != null && !updatedUser.equals("") ? updatedUser.getFirstName() : oldUser.getFirstName());
			oldUser.setLastName(updatedUser.getLastName() != null && !updatedUser.equals("") ? updatedUser.getLastName() : oldUser.getLastName());
			return true;
		}
		return false;
	}

	@Override
	public void deleteUser(String id) {
		userRepository.deleteById(id);
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
		oldUser.setProfilePictureUrl(updatedUser.getProfilePictureUrl() != null && !updatedUser.equals("") ? updatedUser.getProfilePictureUrl() : oldUser.getProfilePictureUrl());
		return true;
	}

}