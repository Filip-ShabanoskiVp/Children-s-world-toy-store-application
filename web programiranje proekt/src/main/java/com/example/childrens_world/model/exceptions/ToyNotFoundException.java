package com.example.childrens_world.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ToyNotFoundException extends RuntimeException {
    public ToyNotFoundException(Long id){
        super(String.format("Toy with id %d was not found",id));
    }
}
