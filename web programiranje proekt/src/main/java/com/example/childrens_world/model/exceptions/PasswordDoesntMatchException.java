package com.example.childrens_world.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PasswordDoesntMatchException extends RuntimeException {
    public PasswordDoesntMatchException(){
        super("Password doesn't match");
    }
}
