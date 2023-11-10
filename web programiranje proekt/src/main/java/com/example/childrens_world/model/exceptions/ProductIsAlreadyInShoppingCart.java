package com.example.childrens_world.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProductIsAlreadyInShoppingCart extends RuntimeException {
    public ProductIsAlreadyInShoppingCart(String name) {
        super(String.format("Product with name: %s is already in shopping cart",name));
    }
}
