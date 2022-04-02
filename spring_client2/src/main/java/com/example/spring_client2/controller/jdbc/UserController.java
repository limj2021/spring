package com.example.spring_client2.controller.jdbc;

import com.example.spring_client2.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping("/list")
    public List mysqlTest(){
        String sql="select * from user";

        List<Test> users=jdbcTemplate.query(sql,new RowMapper<Test>(){
            @Override
            //rowmapper对象通过调用maprow()方法将数据库中的每一行数据封装成user对象，并返回
            public Test mapRow(ResultSet rs,int i)throws SQLException {
                Test test = new Test();
                test.setUsername(rs.getString("name"));
                test.setPassword(rs.getString("login.password"));
                return test;
            }
        });
        System.out.println("查询成功"+users);
        return users;
    }


}
