package com.example.childrens_world.service.impl;


import com.example.childrens_world.model.Manufacturer;
import com.example.childrens_world.model.Product;
import com.example.childrens_world.model.enumerations.Category;
import com.example.childrens_world.model.exceptions.ProductNotFoundException;
import com.example.childrens_world.repository.ProductRepository;
import com.example.childrens_world.service.ManufacturerService;
import com.example.childrens_world.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerService manufacturerService;

    public ProductServiceImpl(ProductRepository productRepository, ManufacturerService manufacturerService) {
        this.productRepository = productRepository;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findAllById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Product saveProduct(Product product, MultipartFile image) throws IOException {
        Manufacturer manufacturer = this.manufacturerService.findAllById(product.getManufacturer().getId());
        product.setManufacturer(manufacturer);
        if(image!=null && !image.getName().isEmpty()){
            byte[] bytes = image.getBytes();
            String imageInBase64 = String.format("data:%s;base64,%s",image.getContentType(),
                    Base64.getEncoder().encodeToString(bytes));

            product.setProductImage(imageInBase64);
        }
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product, MultipartFile image) throws IOException {
        Product p = this.findAllById(id);
        Manufacturer manufacturer = this.manufacturerService.findAllById(product.getManufacturer().getId());

        p.setManufacturer(manufacturer);
        p.setName(product.getName());
        p.setQuantity(product.getQuantity());
        p.setCost(product.getCost());
        p.setColor(product.getColor());
        p.setDescription(product.getDescription());
        p.setCategory(product.getCategory());


        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            p.setProductImage(base64Image);
        }
        return this.productRepository.save(p);
    }

        @Override
        @Transactional
        public List<Product> ListToysByName(String name) {
        String nameLike = "%" + name + "%";
        if(name!=null){
            return this.productRepository.findAllByNameLike(nameLike);
        }else{
            return this.productRepository.findAll();
        }
    }

}
