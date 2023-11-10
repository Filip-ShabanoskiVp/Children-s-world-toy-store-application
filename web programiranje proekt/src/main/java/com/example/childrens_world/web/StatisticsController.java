package com.example.childrens_world.web;

import com.example.childrens_world.model.Product;
import com.example.childrens_world.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    private final ProductRepository productRepository;

    public StatisticsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String getStatisticsPage(Model model){
        List<Product> products = this.productRepository.findAll();
        Map<String,Float>productData = new HashMap<>();
        for (Product p : products){
            float cost = p.getCost();
            productData.put(p.getName(),cost);
        }
        model.addAttribute("productData",productData);
        Map<String,Integer> productPieData = new HashMap<>();
        for (Product p: products){
            int quantity = p.getQuantity();
            productPieData.put(p.getName(),quantity);
        }
        model.addAttribute("productPieData",productPieData);
        return "statistics";
    }
}
