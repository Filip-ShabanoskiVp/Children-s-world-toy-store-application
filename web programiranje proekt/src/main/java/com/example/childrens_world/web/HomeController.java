package com.example.childrens_world.web;

import com.example.childrens_world.model.enumerations.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/home","/"})
public class HomeController {

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("categories", Category.values());
        return "home";
    }
}
