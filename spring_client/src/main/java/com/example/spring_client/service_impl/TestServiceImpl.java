package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.TestMapper;
import com.example.spring_client.entity.Test;
import com.example.spring_client.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//服务层，写重要模块
@Service
//继承接口，验证方法是否有错
public class TestServiceImpl implements TestService {
    //mapper方法调用，连接数据库数据
    @Autowired
    private TestMapper testMapper;

    @Override
    public String add(String user, String password) {
        int add = testMapper.add(user, password);
        if (add == 0) {
            return "添加失败";
        } else {
            return "添加成功";
        }
    }

    @Override
    public String delete(String user) {
        int dle = testMapper.delete(user);
        if (dle == 0) {
            return "删除失败";
        } else {
            return "删除成功";
        }
    }

    @Override
    public String update(String user, String pass) {
        int upd = testMapper.update(user, pass);
        if (upd == 0) {
            return "修改失败";
        } else {
            return "修改成功";
        }
    }

    @Override
    public List<Test> queAll() {
        return testMapper.queAll();
    }

    @Override
    public Test queOne(String user) {
        return testMapper.queOne(user);
    }
}