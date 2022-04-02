package com.example.spring_client2.service;


import com.example.spring_client2.dao_mapper.UserMapper;
import com.example.spring_client2.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<Test> findAll(){
        return userMapper.findAll();
    }

    public int addUser(Test user){
        return userMapper.addUser(user);
    }
}


