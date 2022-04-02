package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.UserRelationMapper;
import com.example.spring_client.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRelationServiceImpl implements UserRelationService {
    @Autowired
    private UserRelationMapper userRelationMapper;

    @Override
    public String userRelationAdd(String userId, String authorityId, String uInfoId) {
        int add=userRelationMapper.userRelationAdd( userId,authorityId,uInfoId);
        if (add == 0) {
            return "添加失败";
        } else {
            return "添加成功";
        }
    }

    @Override
    public String userRelationDelete(String uInfoId) {
        int del=userRelationMapper.userRelationDelete(uInfoId);
        if (del == 0) {
            return "删除失败";
        } else {
            return "删除成功";
        }
    }

    @Override
    public String userRelationUpdate( String userId, String authorityId, String uInfoId) {
        int upd=userRelationMapper.userRelationUpdate(userId,authorityId,uInfoId);
        if (upd == 0) {
            return "修改失败";
        } else {
            return "修改成功";
        }
    }
}
