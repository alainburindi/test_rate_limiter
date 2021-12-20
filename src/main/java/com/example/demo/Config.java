package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 *
 * @Author: Alain Burindi
 */
@Configuration
public class Config implements WebMvcConfigurer {
    @Autowired
    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Custom interceptor, add intercept path and exclude intercept path
        registry.addInterceptor(requestInterceptor).addPathPatterns("/test");
    }
}
