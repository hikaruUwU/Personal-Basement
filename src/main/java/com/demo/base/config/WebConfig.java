package com.demo.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final PassInterceptor passInterceptor;

    @Autowired
    public WebConfig(PassInterceptor passInterceptor) {
        this.passInterceptor = passInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passInterceptor).addPathPatterns("/**").excludePathPatterns("/auth");
    }
}
