package com.example.spring_client2.controller.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

//后端异常处理
@Controller
public class ExcptionController {
    private static final Logger log = LoggerFactory.getLogger(ExcptionController.class);
    @RequestMapping("/exceptionMethod")
    public String exceptionMethod(Model model) throws Exception {
        model.addAttribute("msg","没有抛出异常");
        int num=1/0;
        log.info(String.valueOf(num));
        return "error";
    }

    //用于异常捕获的注解，用来局部处理异常
    @ExceptionHandler(value = {ArithmeticException.class})
    public String arithmeticExceptionHandle(Model model, Exception e){
        //输出错误信息
        model.addAttribute("msg","@ExceptionHandler"+e.getMessage());
        //打印信息到控制台
        log.info(e.getMessage());
        return "error";
    }



}
