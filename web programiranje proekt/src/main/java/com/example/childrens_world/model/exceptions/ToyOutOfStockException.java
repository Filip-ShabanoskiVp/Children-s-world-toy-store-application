package com.example.childrens_world.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class ToyOutOfStockException extends RuntimeException {
    public ToyOutOfStockException(String name) {
        super(String.format("Toy with name %s is out of stock",name));
    }
}
