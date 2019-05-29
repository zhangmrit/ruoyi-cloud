package com.ruoyi.system.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ruoyi.system.resolver.LoginUserHandlerResolver;

/**
 * MVC配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Autowired
    private LoginUserHandlerResolver loginUserHandlerResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(loginUserHandlerResolver);
    }
}