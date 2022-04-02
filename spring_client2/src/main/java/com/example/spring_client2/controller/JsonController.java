package com.example.spring_client2.controller;


import com.example.spring_client2.entity.Test;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//封装返回数据结构

@RestController
@RequestMapping("/json")
class JsonController{

    @RequestMapping("/test")
    public List<Test> test() {
        System.out.println("以json方式返回页面");

        Test u = new Test("张三", "12345678");
        Test u1 = new Test("李四", "00000000");

        List<Test> list = new ArrayList<>();
        list.add(u);
        list.add(u1);
        return list;
    }

    @RequestMapping("/user")
    public JsonResult<Test> getTest(){
        Test u=new Test("网剧","123");

        return new JsonResult<>(u);
    }

    @RequestMapping("/list")
    public JsonResult<List<Test>>getTestList(){
        List<Test> userList=new ArrayList<>();
        Test u=new Test("我大四","123");
        Test u1=new Test("鬼地方","123");
        userList.add(u);
        userList.add(u1);
        return new JsonResult<>(userList,"获取用户列表成功");
    }

    @RequestMapping("/map")
    public JsonResult<Map<String,Object>>getTestMap(){
        Map<String,Object> map=new HashMap<>();
        Test u=new Test("回复的","123");
        map.put("组织",u);
        map.put("科幻剧","三三四四");
        map.put("理科","飞洒地方");
        map.put("同城","阿斯顿撒");
        return new JsonResult<>(map);
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