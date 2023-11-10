package com.example.childrens_world.web;

import com.example.childrens_world.model.Manufacturer;
import com.example.childrens_world.model.Product;
import com.example.childrens_world.model.enumerations.Category;
import com.example.childrens_world.service.ManufacturerService;
import com.example.childrens_world.service.ProductService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productServicee, ManufacturerService manufacturerService) {
        this.productService = productServicee;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/toys")
    public String getToyPage(@RequestParam(required = false) String nameSearch, Model model,
                             @RequestParam(required = false)String category){
        List<Product> toys;
        if (nameSearch == null){
            toys = this.productService.findAll();
        }
       else {
           toys = this.productService.ListToysByName(nameSearch);
       }
       if(category!=null){
           if(category.contains("<")){
               category = category.substring(0,category.indexOf("<"))+" " + category.substring((category.indexOf("<")+1));
           }
           if(category.contains(">")){
               category = category.substring(0,category.indexOf(">"))+" & " + category.substring((category.indexOf(">")+1));
           }
           if(category.contains("$")){
               category = category.substring(0,category.indexOf("$"))+", "+category.substring(category.indexOf("$")+1);
           }
       }
       model.addAttribute("toys",toys);
       model.addAttribute("category",category);
        return "toy";
    }

    @GetMapping("toys/add-toy")
    @Secured("ROLE_ADMIN")
    public String addToyPage(Model model){
        model.addAttribute("manufacturers",this.manufacturerService.findAll());
        model.addAttribute("toy",new Product());
        model.addAttribute("categories", Category.values());
        return "add-toy";
    }

    @PostMapping("/toys")
    @Secured("ROLE_ADMIN")
    public String addToy(@Valid Product toy,
                         BindingResult bindingResult,
                         @RequestParam MultipartFile image,
                         Model model){
        if(bindingResult.hasErrors()){
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers",manufacturers);
            return "add-toy";
        }
        try {
            this.productService.saveProduct(toy,image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/toys";
    }

    @PostMapping("toys/{id}/delete")
    @Secured("ROLE_ADMIN")
    public String deleteToy(@PathVariable Long id){
        this.productService.deleteById(id);
        return "redirect:/toys";
    }

    @GetMapping("toys/{id}/edit")
    @Secured("ROLE_ADMIN")
    public String editToy(@PathVariable Long id,Model model){
        model.addAttribute("toy",this.productService.findAllById(id));
        model.addAttribute("manufacturers",this.manufacturerService.findAll());
        model.addAttribute("categories",Category.values());
        return "add-toy";
    }


    @PostMapping("toys/{id}/update")
    @Secured("ROLE_ADMIN")
    public String updateToy(
            @PathVariable Long id,
            @Valid Product toy,
            @RequestParam MultipartFile image,
            Model model,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers",manufacturers);
             return "add-toy";
        }
        try {
            this.productService.updateProduct(id,toy,image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/toys";
    }


}
