package com.example.spring_client2.controller;

import com.example.spring_client2.entity.Test;
import com.example.spring_client2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<Test> findAll(){
        return userService.findAll();
    }

    @PostMapping("/add")
    public String add(Test user){
        int i=userService.addUser(user);
        if(i>0){
            return "success";
        }
        return "fail";
    }
}
