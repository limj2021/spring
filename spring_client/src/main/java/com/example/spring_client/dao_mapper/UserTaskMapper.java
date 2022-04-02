package com.example.spring_client.dao_mapper;

import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.UserTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserTaskMapper {

    public int userTaskAdd(@Param("user")String user, @Param("publisher") String publisher,
                        @Param("usertaskProgressId")String usertaskProgressId, @Param("publishertaskProgressId")String publishertaskProgressId,
                        @Param("visible")int visible, @Param("time")String time, @Param("forumid")String forumid);

    //删除功能
    public int userTaskDelete(@Param("taskId") int taskId);


    //删除功能
    public int userTaskforumidDelete(@Param("forumid") String forumid);

    //修改功能
    public int userTaskUpdate(@Param("user") String user, @Param("usertaskProgressId") String usertaskProgressId,
                              @Param("publishertaskProgressId") String publishertaskProgressId,
                              @Param("visible") int visible,@Param("time") String time, @Param("forumid")String forumid);

    //查询UserTask
    public List<UserTask> userTaskQueAll();

    //查询UserTask
    public List<UserTask> userTaskQueAllo();

    //三表查询
    public List<Forum> userTaskForum();

    //我的
    public List<Forum> myUserTaskForum(@Param("user") String user);

    //我发布的
    public List<Forum> pulisherUserTaskForum(@Param("user") String user);
}
