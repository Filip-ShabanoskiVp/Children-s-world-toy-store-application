package com.example.childrens_world.service;

import com.example.childrens_world.model.Product;
import com.example.childrens_world.model.enumerations.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findAllById(Long id);

    void deleteById(Long id);

    Product saveProduct(Product product, MultipartFile image) throws IOException;

    Product updateProduct(Long id, Product product, MultipartFile image) throws IOException;

    List<Product> ListToysByName(String name);




}
