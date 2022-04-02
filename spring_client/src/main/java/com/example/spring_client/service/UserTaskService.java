package com.example.spring_client.service;

import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.UserTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTaskService {
    public String userTaskAdd(String user,String publisher,String usertaskProgressId,String publishertaskProgressId,
                           int visible,String time,String forumid);

    //删除功能
    public String userTaskDelete(int taskId);

    //删除功能
    public String userTaskforumidDelete(String forumid);

    //修改功能
    public String userTaskUpdate(String user , String usertaskProgressId, String publishertaskProgressId,
                               int visible, String time,String forumid);

    //查询UserTask
    public List<UserTask> userTaskQueAll();

    //查询UserTask
    public List<UserTask> userTaskQueAllo();

    //三表查询
    public List<Forum> userTaskForum();

    public PageResult<Forum> selForumPage(Integer page, Integer rows) ;

    public List<Forum> myUserTaskForum(String user) ;

    public PageResult<Forum> selmyForumPage(Integer page, Integer rows,String user) ;

    public List<Forum> pulisherUserTaskForum(String user) ;

    public PageResult<Forum> selpublisherForumPage(Integer page, Integer rows,String user) ;
}
