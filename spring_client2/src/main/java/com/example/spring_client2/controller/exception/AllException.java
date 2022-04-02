package com.example.spring_client2.controller.exception;



import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllException {
    private static final Logger log = LoggerFactory.getLogger(AllException.class);

    //@ResponseStatus
    @ExceptionHandler(Exception.class)
    //参数似乎有异常
    public String handleUnexpectedServer(Model model, Exception e){
        model.addAttribute("msg","系统发生异常，请联系管理员");
        log.info(e.getMessage());
        return "error";
    }
}
/*
@Data
class JsonResult<T>{
    private T data;
    private String code;
    private String msg;
    public JsonResult(){
        System.out.println("这是一");
        this.code="0";
        this.msg="操作成功";
    }
    public JsonResult(String code, String msg){
        System.out.println("这是二");
        this.code=code;
        this.msg=msg;
    }
    public JsonResult(T data){
        System.out.println("这是三");
        this.data=data;
        this.code="0";
        this.msg="操作成功";
    }
    public JsonResult(T data, String msg){
        System.out.println("这是四");
        this.data=data;
        this.code="0";
        this.msg=msg;
    }

}
 */