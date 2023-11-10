package com.example.childrens_world.service.impl;

import com.example.childrens_world.model.Role;
import com.example.childrens_world.model.User;
import com.example.childrens_world.model.exceptions.*;
import com.example.childrens_world.repository.RoleRepository;
import com.example.childrens_world.repository.UserRepository;
import com.example.childrens_world.service.AuthService;
import com.example.childrens_world.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserService userService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }


    @PostConstruct
    public void init(){
        if(!this.userRepository.existsById("admin")){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPersonal_Number("0506997440010");
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            this.roleRepository.save(role);
            Role adminRole = this.roleRepository.findByName("ROLE_ADMIN");
            admin.setRoles(Collections.singletonList(adminRole));
            this.userRepository.save(admin);
        }
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String getCurrentUserId() {
        return this.getCurrentUser().getUsername();
    }

    @Override
    public User signUpUser(String username, String password, String repeatedPassword, String first_name, String last_name, String email, String personal_number) {
        if(!password.equals(repeatedPassword)){
            throw new PasswordDoesntMatchException();
        }
        User user = new User();
        user.setUsername(username);
        if(!email.matches("^([\\w]+)@([\\w]+)((\\.(\\w){2,})+)$")){
            throw new EmailWrongFormatException();
        }
        user.setEmail(email);
        if(!first_name.matches("^[A-Z][a-z]+")){
            throw new FirstNameWrongFormat();
        }
        user.setFirstName(first_name);
        if(!last_name.matches("^[A-Z][a-z]+")){
            throw new LastNameWrongFormat();
        }
        user.setLastName(last_name);
        if(!personal_number.matches("[0-9]{13}")){
            throw new PersonalNumberWrongFormat();
        }
        user.setPersonal_Number(personal_number);
        if(this.roleRepository.findByName("ROLE_USER")==null){
            Role role = new Role();
            role.setName("ROLE_USER");
            this.roleRepository.save(role);
        }
        user.setPassword(passwordEncoder.encode(password));
        Role userRole = this.roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(userRole));
        return this.userService.registerUser(user);
    }

}
