package com.example.spring_client2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//跨区域配置，前后端分离开发，cors跨域配置
@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        //添加cors配置信息
        CorsConfiguration config=new CorsConfiguration();
        //1允许接收的域，不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://localhost:8081");
        //2是否发送cookie信息
        config.setAllowCredentials(true);
        //3允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //4允许的头信息
        config.addAllowedHeader("*");
        //5添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource=new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config);
        //6返回新的CorsFilter
        return new CorsFilter(configSource);
    }
}
