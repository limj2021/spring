package com.example.demo;

import com.example.demo.UserInfoService.RedisService;
import com.example.demo.entiy.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    RedisService service;

    @Test
    void redisSet(){
        User user=new User("aa@qq.com","quellan","123456","朱",System.currentTimeMillis()+"");

        boolean a = service.setUser(user);
        System.out.println(a);
    }

    @Test
    public void redisGet() throws Exception {
        User b = service.getUser("quellan");
        String c = b.getEmail();
        System.out.println(b);
        System.out.println(c);
    }

    @Value("${server.port}")
    Integer port;
    @Test
    //    单元测试方法中添加了参数，这是不允许的
    public void redisSessionSet() throws Exception {
        HttpSession session = null;
//        int port = 8084;
        session.setAttribute("key","quellanAn");
        String a = String.valueOf(port);
        System.out.println(a);
    }

    @Test
    public void redisSessionGet() throws Exception {

    }



}
