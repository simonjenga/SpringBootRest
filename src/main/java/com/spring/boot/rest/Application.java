package com.spring.boot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This is the main entry class to this Project/Application.
 * It is an ordinary java class with a main method {@link #main(String[])}
 * 
 * -> /api/org/springframework/boot/context/web/SpringBootServletInitializer.html
 * 
 * @author Simon Njenga
 * @since 0.1
 */
@Configuration
@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
