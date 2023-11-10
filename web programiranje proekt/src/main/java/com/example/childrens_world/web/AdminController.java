package com.example.childrens_world.web;

import com.example.childrens_world.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAdminPage(Model model){
        model.addAttribute("users",this.userService.findAllUsers());
        return "admin";
    }

    @PostMapping("/{username}/delete")
    public String deleteByUsername(@PathVariable String username){
        this.userService.deleteById(username);
        return "redirect:/admin";
    }
}
