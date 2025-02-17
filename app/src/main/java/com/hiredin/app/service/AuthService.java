package com.hiredin.app.service;

import com.hiredin.app.model.enums.Role;
import com.hiredin.app.model.User;
import com.hiredin.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already taken");
        }
        User user = new User(username, email, passwordEncoder.encode(password), Collections.singleton(Role.USER));
        return userRepository.save(user);
    }
}
