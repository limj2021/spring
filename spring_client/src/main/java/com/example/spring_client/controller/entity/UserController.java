package com.example.spring_client.controller.entity;

import com.example.spring_client.entity.User;
import com.example.spring_client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    //用户暂时不使用，转移到LoginController功能

    //增加
/*
    @RequestMapping("/add")
    public String add(@RequestParam("username") String username,
                      @RequestParam("password") String password, Model model) {
        System.out.println("获得值" + "测试用户=" + username + "+密码=" + password );
        String add = userService.UserAdd(username,password);
        System.out.println(add);
        List<User> user = userService.UserQueAll();
        System.out.println(user);
        model.addAttribute("lists", user);
        //赋值给页面
        return "main";
    }
*/

    //删除
/*    @RequestMapping("/delete")
    public String delete(@RequestParam("username") String username,
                         @RequestParam("password") String password, Model model) {
        System.out.println("获得值" + "测试用户=" + username + "+密码=" + password );
        String dle = userService.UserDelete(username);
        System.out.println(dle);
        List<User> user = userService.UserQueAll();
        System.out.println(user);
        model.addAttribute("lists", user);
        //赋值给页面
        return "main";
    }*/

    //修改
/*    @RequestMapping("/update")
    public String update(@RequestParam("username") String username,
                         @RequestParam("password") String password, Model model) {
        System.out.println("获得值" + "测试用户=" + username + "+密码=" + password );
        String upd = userService.UserUpdate(username,password);
        System.out.println(upd);
        List<User> user = userService.UserQueAll();
        System.out.println(user);
        model.addAttribute("lists", user);
        //赋值给页面
        return "main";
    }


    //查询
    @RequestMapping("/all")
    public String query(@RequestParam("username") String username, Model model) {
        System.out.println("获得值" + "测试用户=" + username);
        User que = userService.UserQueOne(username);
        System.out.println(que);
        List<User> user = userService.UserQueAll();
        System.out.println(user);
        model.addAttribute("lists", user);
        //赋值给页面
        return "main";
    }

    //查询全部
    @RequestMapping("/que")
    public String update(Model model) {
        List<User> user = userService.UserQueAll();
        System.out.println(user);
        model.addAttribute("lists", user);
        //赋值给页面
        return "main";
    }*/




}
