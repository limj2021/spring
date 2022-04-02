/*
package com.example.spring_client.controller.asyn;


import com.example.spring_client.service.AsynService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@EnableAsync--要在SpringBootApplication类中开启异步注解功能
@RestController
public class AsynController {
    @Autowired
    AsynService asynService;

    //异步测试
    @RequestMapping("/asyn")
    public String asyn(){
        asynService.async();
        return "ok";
    }

}
*/
