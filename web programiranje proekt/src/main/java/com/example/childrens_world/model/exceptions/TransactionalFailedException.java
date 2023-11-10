package com.example.childrens_world.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class TransactionalFailedException extends RuntimeException {
    public TransactionalFailedException(String username, String message) {
        super(String.format("Transaction with username %s failed! Message: %s",username,
                message));
    }
}
