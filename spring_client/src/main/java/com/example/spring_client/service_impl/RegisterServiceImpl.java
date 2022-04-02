package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.RegisterMapper;
import com.example.spring_client.entity.Register;
import com.example.spring_client.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//注册者
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;

    //添加注册用户信息
    @Override
    public String registerAdd(String email, int phoneNumber, String nackName, String password) {
        int add=registerMapper.registerAdd(email,phoneNumber,nackName,password);
        if (add == 0) {
            return "用户添加失败";
        } else {
            return "用户添加成功";
        }
    }

    @Override
    public String registerDelete(String user) {
        int dle=registerMapper.registerDelete(user);
        if (dle == 0) {
            return "用户删除失败";
        } else {
            return "用户删除成功";
        }
    }

    //查询全部
    @Override
    public List<Register> registerQueAll() {
        List<Register> list = registerMapper.registerQueAll();
        return list;
    }

    //查询用户名
    @Override
    public Register registerQueOne(String username) {
        Register que=registerMapper.registerQueOne(username);
        return que;
    }

}
