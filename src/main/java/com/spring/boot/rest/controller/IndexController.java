package com.spring.boot.rest.controller;

//import org.springframework.boot.autoconfigure.web.ErrorController;
// import org.springframework.boot.autoconfigure.web.servlet.error.ErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Remove Whitelabel Error Page message:
 * "This application has no explicit mapping for /error, so you are seeing this as a fallback."
 * 
 * This class creates a controller mapping for "/error"
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@RestController
public class IndexController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error Handling Page";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}