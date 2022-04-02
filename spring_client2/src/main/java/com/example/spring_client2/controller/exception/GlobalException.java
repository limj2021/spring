package com.example.spring_client2.controller.exception;

import com.example.spring_client2.controller.exception.ExcptionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException {
    private static final Logger log = LoggerFactory.getLogger(ExcptionController.class);

    //ControllerAdvice和ExceptionHandler一起,来定义全局异常
    @ExceptionHandler(value = {ArithmeticException.class})
    public String arithmeticExceptionHandle(Model model, Exception e){
        //输出错误信息
        model.addAttribute("msg","@ControllerAdvice+@ExceptionHandler"+e.getMessage());
        //打印信息到控制台
        log.info(e.getMessage());
        return "error";
    }


    //定义空值异常
    @ExceptionHandler(value = {NullPointerException.class})
    public String NullPointerException(Model model, Exception e){
        //输出错误信息
        model.addAttribute("msg","@ControllerAdvice+@ExceptionHandler"+e.getMessage());
        //打印信息到控制台
        log.info(e.getMessage());
        return "error";
    }

}
