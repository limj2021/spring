package com.example.spring_client2.dao_mapper;

import com.example.spring_client2.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Test> findAll();
    int addUser(Test user);
}
