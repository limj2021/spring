package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.AnnouncementMapper;
import com.example.spring_client.entity.Announcement;
import com.example.spring_client.entity.User;
import com.example.spring_client.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    AnnouncementMapper announcementMapper;

    @Override
    public String announcementAdd(String userid, String time, String title, String content) {
        int add=announcementMapper.announcementAdd( userid,  time,  title,  content);
        if (add == 0) {
            return "用户添加失败";
        } else {
            return "用户添加成功";
        }
    }

    @Override
    public String announcementDelete(int aid) {
        int dle = announcementMapper.announcementDelete(aid);
        if (dle == 0) {
            return "用户删除失败";
        } else {
            return "用户删除成功";
        }
    }

    @Override
    public User userAnnouncementone() {
        User allone = announcementMapper.userAnnouncementone();
        return allone;
    }

    @Override
    public List<User> userAnnouncement() {
        List<User> all = announcementMapper.userAnnouncement();
        return all;
    }
}
