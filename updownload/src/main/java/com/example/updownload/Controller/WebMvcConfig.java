package com.example.updownload.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展springmvc,springmvc将全面接管
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //添加视图解析器,视图解析器就是解析html文件的
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //视图跳转
        registry.addViewController("uploadone").setViewName("/test/uploadone");
    }
}