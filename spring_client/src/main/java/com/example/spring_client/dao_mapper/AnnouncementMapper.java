package com.example.spring_client.dao_mapper;

import com.example.spring_client.entity.Announcement;
import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnnouncementMapper {

    public int announcementAdd(@Param("userid")String userid, @Param("time") String time,
                        @Param("title")String title, @Param("content")String content);

    //删除功能
    public int announcementDelete(@Param("aid") int aid);

    //查询一个
    public User userAnnouncementone ();

    //查询全部
    public List<User> userAnnouncement();



}
