package com.example.fa_blog_study.config;

import com.example.fa_blog_study.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FaMvcConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(loginInterceptor);
        ir.addPathPatterns("/test");
        ir.addPathPatterns("/comments/create/change");
        ir.addPathPatterns("/articles/publish");
//        ir.addPathPatterns("/**");
//        ir.excludePathPatterns("/login");
//        ir.excludePathPatterns("register");
    }
}
