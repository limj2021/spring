package com.example.spring_client2.controller.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String home(){
        return "hello,spring security!";
    }
}

@RestController
class UserController {
    @RequestMapping("/user/hello")
    public String hello(){
        return "user,hello!";
    }
}

@RestController
class AdminController{
    @RequestMapping("/admin/hello")
    public String hello(){
        return "admin,hello!";
    }
}
