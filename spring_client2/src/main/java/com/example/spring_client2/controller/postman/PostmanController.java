package com.example.spring_client2.controller.postman;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//建立参数调用请求
@RestController
public class PostmanController {
    @PostMapping("login")
    public JsonResult login(String username,String password){
        JsonResult result=new JsonResult();
        result.setData(username);
        result.setCode("0");
        result.setMsg("操作成功");
        return result;
    }
}

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