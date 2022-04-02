/*
package com.example.spring_client.controller.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {


    @RequestMapping({"/","index"})
    public String toIndex(Model model) {
        model.addAttribute("msg","hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }



    //登录
    @RequestMapping("/aaa/login")
    public String login(String username, String password,Model model){
        //获取用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //拿到用户账号密码--
        try {
            //执行登录方法，没有异常及说明-
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {//用户不存在
            model.addAttribute("msg", "用户名错误");
            return "index";
        } catch (IncorrectCredentialsException e) {//密码错误
            model.addAttribute("msg", "密码错误");
            return "index";
        }
    }


    //跳转未授权
    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized() {
        return "未经授权，无法访问此页面";
    }

    //注销--不确定行不行
    @RequestMapping("/aaa/logout")
    public void login(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }
}
*/
