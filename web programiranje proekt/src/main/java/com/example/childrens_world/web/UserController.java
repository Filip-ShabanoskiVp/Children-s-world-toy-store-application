package com.example.childrens_world.web;

import com.example.childrens_world.model.User;
import com.example.childrens_world.service.AuthService;
import com.example.childrens_world.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-profile")
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String userProfilePage(Model model){
        User user = this.userService.findById(this.authService.getCurrentUserId());
        model.addAttribute("user",user);
        return "user-profile";
    }
}
