package com.example.spring_client2.controller.exception;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

//定义为配置类
@Configuration
public class SimpleMappingException {
    //注册一个对象，由spring ioc管理
    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //异常信息，error页面
        mappings.put("java.lang.ArithmeticException","error");
        //设置异常与视图
        resolver.setExceptionMappings(mappings);
        return resolver;
    }


}
