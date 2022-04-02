package com.example.spring_client.dao_mapper;

import com.example.spring_client.entity.Test;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
//这只是一个测试的类
//进行简单查询处理时使用,不利于复杂sql的编写
public interface TestMapper {
    //增加
    @Insert("INSERT INTO spring.test (username,password) VALUES (#{one},#{two})")
    public int add(@Param("one") String user, @Param("two") String password);

    //删除,删除全部数据
    @Delete("DELETE FROM spring.test WHERE username = #{one}")
    public int delete(@Param("one") String user);

    //修改
    @Update("UPDATE spring.test SET password = #{pass} WHERE username = #{user}")
    public int update(@Param("user") String user, @Param("pass") String password);

    //查询全部
    @Select("SELECT * FROM spring.test")
    public List<Test> queAll();

    //查询一个数据
    @Select("SELECT * FROM spring.test where username=#{one}")
    public Test queOne(@Param("one") String user);
}
