/*
package com.example.spring_client.controller.entity;

import com.example.spring_client.entity.Test;
import com.example.spring_client.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService service;

    //测试死数据
    @ApiOperation("测试数据控制类")
    @RequestMapping("/test")
    public String test(Model model) {
        System.out.println("这是一个测试");
        Test u=new Test("张三","12345678");
        Test u1=new Test("李四","00000000");
        List<Test> list=new ArrayList<>();
        list.add(u);
        list.add(u1);
        model.addAttribute("list",list);
        //赋值给页面
        return "test/test";
    }
    //增加
    @RequestMapping("/add")
    public String add(@RequestParam("username") String username,
                         @RequestParam("password") String password, Model model) {
        System.out.println("获得值" + "测试用户=" + username + "+密码=" + password );
        String add = service.add(username,password);
        System.out.println(add);
        List<Test> tour = service.queAll();
        System.out.println(tour);
        model.addAttribute("lists", tour);
        //赋值给页面
        return "main";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(@RequestParam("username") String username,
                         @RequestParam("password") String password, Model model) {
        System.out.println("获得值" + "测试用户=" + username + "+密码=" + password );
        String dle = service.delete(username);
        System.out.println(dle);
        List<Test> tour = service.queAll();
        System.out.println(tour);
        model.addAttribute("lists", tour);
        //赋值给页面
        return "main";
    }

    //修改
    @RequestMapping("/update")
    public String update(@RequestParam("username") String username,
                         @RequestParam("password") String password, Model model) {
        System.out.println("获得值" + "测试用户=" + username + "+密码=" + password );
        String upd = service.update(username,password);
        System.out.println(upd);
        List<Test> tour = service.queAll();
        System.out.println(tour);
        model.addAttribute("lists", tour);
        //赋值给页面
        return "main";
    }


    //查询
    @RequestMapping("/que")
    public String query(@RequestParam("username") String username, Model model) {
        System.out.println("获得值" + "测试用户=" + username);
        Test que = service.queOne(username);
        System.out.println(que);
        List<Test> tour = service.queAll();
        System.out.println(tour);
        model.addAttribute("lists", tour);
        //赋值给页面
        return "main";
    }

    //查询全部
    @RequestMapping("/add")
    public String update(Model model) {
        List<Test> tour = service.queAll();
        System.out.println(tour);
        model.addAttribute("lists", tour);
        //赋值给页面
        return "main";
    }



}



*/
