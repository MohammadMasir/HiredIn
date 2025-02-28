package com.hiredin.app.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hiredin.app.model.User;
import com.hiredin.app.model.enums.Role;
import com.hiredin.app.repository.UserRepository;

//@Service
public class CustomUserDetailsService {

//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        if (userOptional.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        User user = userOptional.get();
//        
//        // Handle null roles
//        Set<Role> userRoles = user.getRoles();
//        if (userRoles == null) {
//            userRoles = new HashSet<>();
//            userRoles.add(Role.USER); // Add default role
//        }
//
//        // Convert Set<Role> to Set<GrantedAuthority>
//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
//                .collect(Collectors.toSet());
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .authorities(authorities)
//                .build();
//    }
}
