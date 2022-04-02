package com.example.spring_client.controller;

import com.example.spring_client.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    @RequestMapping("/test")
    public void ajaxTest(String name, HttpServletResponse response) throws IOException {
        if("admin".equals(name)){
            response.getWriter().println("true");
        }else {
            response.getWriter().println("false");
        }
    }

    @RequestMapping("/test2")
    @ResponseBody
    public List<User> ajax2(){
        List<User> list=new ArrayList<>();
        //User user1 = new User("","");
        //list.add(user1);
        return list;
    }

}
