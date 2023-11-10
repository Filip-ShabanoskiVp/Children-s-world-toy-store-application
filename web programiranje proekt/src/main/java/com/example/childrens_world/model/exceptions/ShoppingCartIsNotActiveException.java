package com.example.childrens_world.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ShoppingCartIsNotActiveException extends RuntimeException {
    public ShoppingCartIsNotActiveException(String username) {
        super(String.format("Shopping cart with username %s is not active!",username));
    }
}
