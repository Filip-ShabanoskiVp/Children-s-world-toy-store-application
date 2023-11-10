package com.example.childrens_world.web;

import com.example.childrens_world.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/logUp")
public class LogUpController {
    private final AuthService authService;

    public LogUpController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String logUpPage(){
        return "logup";
    }

    @PostMapping
    public String LogUpPage(@RequestParam String username, @RequestParam String password,
                            @RequestParam String repeatedPassword,
                            @RequestParam String first_name, @RequestParam String last_name,
                            @RequestParam String email, @RequestParam String personal_number){
        try {
            this.authService.signUpUser(username,password,repeatedPassword,first_name,last_name,email,
                    personal_number);
            return "redirect:/login?info=SuccessfulRegistration!";
        }catch (RuntimeException ex){
            return "redirect:/logUp?error="+ ex.getLocalizedMessage();
        }
    }
}
