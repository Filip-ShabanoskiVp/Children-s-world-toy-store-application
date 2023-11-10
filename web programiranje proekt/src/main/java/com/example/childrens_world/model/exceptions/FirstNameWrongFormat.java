package com.example.childrens_world.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FirstNameWrongFormat extends RuntimeException{
    public FirstNameWrongFormat(){
        super("Wrong First Name Format");
    }
}
