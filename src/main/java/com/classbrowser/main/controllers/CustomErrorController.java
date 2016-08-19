package com.classbrowser.main.controllers;

import com.classbrowser.main.pojo.ErrorMessage;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    ErrorMessage error() {
        return new ErrorMessage("Error");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}