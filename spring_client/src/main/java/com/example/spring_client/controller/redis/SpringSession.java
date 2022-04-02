package com.example.spring_client.controller.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SpringSession {
    @Value("${server.port}")
    Integer port;

    @RequestMapping("/setSession")
    public String setSession(HttpSession session){
        session.setAttribute("key","quellanAn");
        return String.valueOf(port);
    }
    @RequestMapping("/getSession")
    public String getSession(HttpSession  session){
        return session.getAttribute("key")+":"+port;
    }
}
