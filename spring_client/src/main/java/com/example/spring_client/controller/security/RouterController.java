package com.example.spring_client.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//路由器
@Controller
public class RouterController {

    @RequestMapping({"/level1/{id}"})
    public String level1(@PathVariable("id") int id){return "views/security/level1/level"+id;}

    @RequestMapping({"/level2/{id}"})
    public String level2(@PathVariable("id") int id){return "views/security/level2/level"+id;}

    @RequestMapping({"/level3/{id}"})
    public String level3(@PathVariable("id") int id){return "views/security/level3/level"+id;}
}
