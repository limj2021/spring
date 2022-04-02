package com.example.spring_client.service;

import com.example.spring_client.entity.Register;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegisterService {
    public String registerAdd(String email,int phoneNumber, String nackName, String password);


    //删除功能
    public String registerDelete(String user);

    //查询功能
    public List<Register> registerQueAll();

    public Register registerQueOne(String username);
}
