package com.example.childrens_world.service;

import com.example.childrens_world.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findById(String username);
    User registerUser(User user);
    List<User> findAllUsers();
    void deleteById(String username);
}
