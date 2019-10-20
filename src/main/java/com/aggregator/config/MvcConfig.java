package com.aggregator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/news").setViewName("news");
        registry.addViewController("/").setViewName("news");
//        registry.addViewController("/category").setViewName("category");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/registration").setViewName("registration");
    }

}