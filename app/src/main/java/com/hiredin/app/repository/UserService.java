package com.hiredin.app.repository;

import java.util.List;
import java.util.Optional;

import com.hiredin.app.model.User;

public interface UserService {
    Boolean createUser(User user);
    User getUserById(String id);
    List<User> getAllUsers();
    Boolean updateUser(String id, User updatedUser);
    void deleteUser(String id);
    Optional<User> findByEmail(String email);
}