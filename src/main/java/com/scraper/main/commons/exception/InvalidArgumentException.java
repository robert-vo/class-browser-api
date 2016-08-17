package com.scraper.main.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Argument")
public class InvalidArgumentException extends Exception {

    public InvalidArgumentException(String parameter){
        super("InvalidArgumentException with parameter of: " + parameter);
    }

}
