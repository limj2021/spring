package com.example.spring_client2.dao_mapper;


import com.example.spring_client2.entity.Test;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    @Insert("INSERT INTO trading.test (username,password) VALUES (#{one},#{two})")
    public int add(@Param("one") String user, @Param("two") String pass);

    @Delete("DELETE FROM trading.test WHERE username = #{id}")
    public int delete(@Param("id") int user);

    @Update("UPDATE trading.test SET password = #{pass} WHERE username = #{user}")
    public int update(@Param("user") int user, @Param("pass") int password);

    @Select("SELECT * FROM trading.test")
    public List<Test> queAll();

    @Select("SELECT * FROM trading.test where username=#{id}")
    public Test queOne(@Param("id") String user);
}
