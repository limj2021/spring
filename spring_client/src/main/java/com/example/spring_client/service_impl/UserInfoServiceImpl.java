package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.UserInfoMapper;
import com.example.spring_client.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public String userInfoAdd(String uInfoId,String email, String nackName, String phoneNumber) {
        int add=userInfoMapper.userInfoAdd(uInfoId,email,nackName,phoneNumber);
        if (add == 0) {
            return "添加失败";
        } else {
            return "添加成功";
        }
    }

    @Override
    public String userInfoDelete(String uInfoId) {
        int del = userInfoMapper.userInfoDelete(uInfoId);
        if (del == 0) {
            return "删除失败";
        } else {
            return "删除成功";
        }
    }

    @Override
    public String userUpdate(String email, String phoneNumber, String nackName, String username) {
        int upd=userInfoMapper.userUpdate(email,phoneNumber,nackName,username);
        if (upd == 0) {
            return "修改失败";
        } else {
            return "修改成功";
        }
    }
}
