package com.classbrowser.main.controllers;

import com.classbrowser.main.pojo.ErrorMessage;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    private static Logger log = Logger.getLogger(CustomErrorController.class);
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    ErrorMessage error() {
        log.error("User entered invalid page and/or parameters.");
        return new ErrorMessage("Error");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}