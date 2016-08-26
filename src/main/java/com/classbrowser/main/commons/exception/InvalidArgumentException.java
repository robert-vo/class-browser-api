package com.classbrowser.main.commons.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InvalidArgumentException is a custom exception that handles
 * invalid arguments with respect to parameters passed through the URL
 * to the class browser.
 *
 * @author Robert Vo
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Argument")
public class InvalidArgumentException extends Exception {

    private static Logger log = Logger.getLogger(InvalidArgumentException.class);

    /**
     * Calls the parent class, Exception, with a pre-defined message.
     *
     * @param parameter - The invalid parameter that was passed through the URL.
     */
    public InvalidArgumentException(String parameter){
        super("InvalidArgumentException with parameter of: " + parameter);
        log.debug("InvalidArgumentException with parameter of: " + parameter);
    }

}

