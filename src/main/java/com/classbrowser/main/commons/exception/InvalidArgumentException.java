package com.classbrowser.main.commons.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Argument")
public class InvalidArgumentException extends Exception {

    private static Logger log = Logger.getLogger(InvalidArgumentException.class);

    public InvalidArgumentException(String parameter){
        super("InvalidArgumentException with parameter of: " + parameter);
        log.debug("InvalidArgumentException with parameter of: " + parameter);
    }

}
