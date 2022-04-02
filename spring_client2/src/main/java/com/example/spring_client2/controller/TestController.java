package com.example.spring_client2.controller;

import com.example.spring_client2.entity.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(Model model) {
        System.out.println("这是一个测试");

        Test u=new Test("张三","12345678");
        Test u1=new Test("李四","00000000");

        List<Test> list=new ArrayList<>();
        list.add(u);
        list.add(u1);

        model.addAttribute("list",list);
        return "test";
    }





}
