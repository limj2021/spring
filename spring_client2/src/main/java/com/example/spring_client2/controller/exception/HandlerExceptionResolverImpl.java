package com.example.spring_client2.controller.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class HandlerExceptionResolverImpl implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("msg","实现HandlerExceptionResolver接口处理异常");
        //判断异常类型进行跳转
        if(ex instanceof ArithmeticException){
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
}
