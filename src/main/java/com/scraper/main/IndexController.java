package com.scraper.main;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Robert on 7/27/16.
 */
@RestController
public class IndexController implements ErrorController {

    private static final String ERROR_PATH = "/error";

//    @RequestMapping(value = ERROR_PATH)
//    public String printError() {
//        return "Invalid parameters";
//    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH, method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/pages/error.htm";
    }
}
