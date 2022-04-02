/*
package com.example.spring_client.controller.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//未使用，但是可行
@RestController
public class JDBCController {
    //jdbc自带,springboot已经配置好了
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库全部信息
    @GetMapping("/userList")
    public List<Map<String, Object>> userList() {
        String sql="select * from spring.test";
        List<Map<String, Object>> list_maps =jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql="insert into spring.test(username,password) values('小明','123456')";
        jdbcTemplate.update(sql);
        return "add-ok";
    }

    @GetMapping("/updateUser")
    public String updateUser(){
        String sql="update spring.test set password = ? where username = ?";
        //封装
        Object[] object=new Object[2];
        object[0]="小明";
        object[1]="1111";
        //jdbcTemplate.update(sql,object);//不管用
        jdbcTemplate.update(sql,"小明","qqqqq");
        return "update-ok";
    }

    //http://localhost:8080/updateUserTest/1231
    @GetMapping("/updateUserTest/{nackname}")
    public String updateUser(@PathVariable("nackname") String nackname){
        String sql="update spring.register set email=?,phoneNumber=?,password=? where nackname="+nackname;
        Object[] object=new Object[3];
        object[0]="小明2";
        object[1]=1111;
        object[2]="123";
        jdbcTemplate.update(sql,object);
        return "updateTest-ok";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(){
        String sql="delete from spring.test where username = ?";
        String username="小明";
        jdbcTemplate.update(sql,username);
        return "delete-ok";
    }


}
*/
