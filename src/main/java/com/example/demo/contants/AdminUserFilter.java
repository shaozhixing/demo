package com.example.demo.contants;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器
 * @author: shaozhixing
 * @date: 2019/8/30 11:37
 * @copyright: gofun
 */
@Component
public class AdminUserFilter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminUserInterceptor());
    }
}
