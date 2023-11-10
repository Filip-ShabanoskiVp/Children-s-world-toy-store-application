package com.example.childrens_world.web;

import com.example.childrens_world.model.Manufacturer;
import com.example.childrens_world.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/add-manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String addManufacturerPage(Model model){
        model.addAttribute("manufacturer",new Manufacturer());
        return "add-manufacturer";
    }

    @PostMapping
    public String saveManufacture(@Valid Manufacturer manufacturer){
        this.manufacturerService.addNew(manufacturer);
        return "redirect:/toys/add-toy";
    }
}
