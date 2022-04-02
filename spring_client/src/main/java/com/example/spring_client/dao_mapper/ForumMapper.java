package com.example.spring_client.dao_mapper;

import com.example.spring_client.entity.Forum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ForumMapper {
    public int forumAdd(@Param("publisher")String publisher, @Param("time") String time,
                        @Param("title")String title, @Param("content")String content,
                        @Param("salary")int salary, @Param("workingOneHours")String workingOneHours,
                        @Param("workingTwoHours")String workingTwoHours, @Param("address")String address,
                        @Param("phoneNumber")String phoneNumber,@Param("taskid")String taskid);

    //删除功能
    public int forumDelete(@Param("id") int id);

    //删除功能
    public int forumtaskidDelete(@Param("taskid") String taskid);

    //查询全部
    public List<Forum> forumQueAll();

    //查询一个
    public List<Forum> forumQueLike(@Param("title")String title);

    //分页查询--查询全部而已
    public List<Forum> forumQuePage();
}
