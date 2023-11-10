package com.example.childrens_world.service;

import com.example.childrens_world.model.User;

public interface AuthService {
    User getCurrentUser();
    String getCurrentUserId();
    User signUpUser(String username, String password, String repeatedPassword,
                    String first_name, String last_name,
                    String email, String personal_number);
}
