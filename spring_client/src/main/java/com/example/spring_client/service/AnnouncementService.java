package com.example.spring_client.service;

import com.example.spring_client.entity.Announcement;
import com.example.spring_client.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementService {
    public String announcementAdd(String userid, String time,String title,String content);

    //删除功能
    public String announcementDelete(int aid);

    //查询一个
    public User userAnnouncementone ();

    //查询全部
    public List<User> userAnnouncement();

}
