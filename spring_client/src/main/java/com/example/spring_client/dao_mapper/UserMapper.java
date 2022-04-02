package com.example.spring_client.dao_mapper;

import com.example.spring_client.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
//user用户查询
//与resources里面的mappers的UserMapper.xml绑定

public interface UserMapper {

    //增加--@Insert("INSERT INTO spring.user (username,password) VALUES (#{one},#{two})")

    public int userAdd(@Param("userId") String userId,@Param("username") String user, @Param("password") String password);


    //删除,删除全部数据--@Delete("DELETE FROM spring.user WHERE username = #{one}")

    public int userDelete(@Param("username") String user);

    public int useridDelete(@Param("userId") String userId);

    public int userUpUpdate(@Param("userId") String userId);

    public int userDownUpdate(@Param("userId") String userId);


    //修改--@Update("UPDATE spring.user SET password = #{two} WHERE username = #{one}")

    public int userUpdate(@Param("username") String user, @Param("login.password") String password);


    //查询全部--@Select("SELECT * FROM spring.user")

    public List<User> userQueAll();


    //查询一个数据--@Select("SELECT * FROM spring.user where username=#{one}")

    public User userQueOne(@Param("username") String user);


    //查询用户表和权限表的字段

    public List<User> userAuthor();

    //查询用户表和权限表的字段--三表查询

    public List<User> userAuthorThree();

    //查询用户表和权限表的字段--一条数据

    public User userAuthorOne(@Param("username") String user);

    //查询用户表和信息表--全部

    public List<User> userInfo();

    //查询用户表和信息表的字段全部--三表查询

    public List<User> userInfoThree();

    //用户信息查询一条

    public User userInfoOne(@Param("username") String user);

    //用户权限信息全查

    public List<User> userAuthorInfo();

    //用户权限信息全查--一条数据

    public User userAuthorInfoO(@Param("username") String user);
}
