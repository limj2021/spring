package com.example.spring_client.dao_mapper;

import com.example.spring_client.entity.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RegisterMapper {
    //增加
    public int registerAdd(@Param("email")String email,@Param("phoneNumber")int phoneNumber,
                           @Param("username")String username, @Param("password")String password);

    //删除功能
    public int registerDelete(@Param("username") String user);
    //查询全部
    public List<Register> registerQueAll();

    //查询
    public Register registerQueOne(@Param("username")String username);
}
