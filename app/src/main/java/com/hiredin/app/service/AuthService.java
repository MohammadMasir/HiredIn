package com.hiredin.app.service;

import java.util.HashSet;
import java.util.Set;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hiredin.app.model.User;
import com.hiredin.app.model.enums.Role;
import com.hiredin.app.repository.UserRepository;

//@Service
public class AuthService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public User registerUser(String username, String email, String password) {
//        if (userRepository.existsByEmail(email)) {
//            throw new RuntimeException("Email already taken");
//        }
//        
//        // Create default roles set
//        Set<Role> defaultRoles = new HashSet<>();
//        defaultRoles.add(Role.USER);
//        
//        User user = new User(
//            username, 
//            email, 
//            passwordEncoder.encode(password),
//            defaultRoles  // Set default roles
//        );
//        
//        return userRepository.save(user);
//    }
}
