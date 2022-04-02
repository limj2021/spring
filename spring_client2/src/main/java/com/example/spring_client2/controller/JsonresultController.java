package com.example.spring_client2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jsonresult")
public class JsonresultController {


}

/*

class JsonResult<T>{
    private T data;
    private String code;
    private String msg;
    public JsonResult(){
        //System.out.println("这是一");
        this.code="0";
        this.msg="操作成功";
    }
    public JsonResult(String code, String msg){
        //System.out.println("这是二");
        this.code=code;
        this.msg=msg;
    }
    public JsonResult(T data){
        //System.out.println("这是三");
        this.data=data;
        this.code="0";
        this.msg="操作成功";
    }
    public JsonResult(T data, String msg){
        //System.out.println("这是四");
        this.data=data;
        this.code="0";
        this.msg=msg;
    }

}
*/