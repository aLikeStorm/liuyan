package com.example.liuyan.config;

import com.example.liuyan.controller.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/20 10:58
 * @packagename com.example.liuyan.config
 * @classname MyConfig
 * @description
 */
@Configuration(proxyBeanMethods = false)
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登陆拦截
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }
}
