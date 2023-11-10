package com.example.childrens_world.service.impl;

import com.example.childrens_world.model.User;
import com.example.childrens_world.model.exceptions.UserAlreadyExistsException;
import com.example.childrens_world.model.exceptions.UserNotFoundException;
import com.example.childrens_world.repository.UserRepository;
import com.example.childrens_world.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findById(String username) {
        return this.userRepository.findById(username)
                .orElseThrow(()->new UserNotFoundException(username));
    }

    @Override
    public User registerUser(User user) {
        if(this.userRepository.existsById(user.getUsername())){
            throw new UserAlreadyExistsException(user.getUsername());
        }
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteById(String username) {
        User user = this.findById(username);
        user.setRoles(null);
        this.userRepository.deleteById(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findById(username)
                .orElseThrow(()->new UsernameNotFoundException(username));
    }
}
