package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.UserTaskMapper;
import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.UserTask;
import com.example.spring_client.service.UserTaskService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTaskServiceImpl implements UserTaskService {
    @Autowired
    UserTaskMapper userTaskMapper;

    @Override
    public String userTaskAdd(String user, String publisher, String usertaskProgressId, String publishertaskProgressId, int visible, String time,String forumid) {
        int add=userTaskMapper.userTaskAdd(user,publisher,usertaskProgressId,publishertaskProgressId,visible,time,forumid);
        if (add == 0) {
            return "添加失败";
        } else {
            return "添加成功";
        }
    }

    @Override
    public String userTaskDelete(int taskId) {
        int del=userTaskMapper.userTaskDelete(taskId);
        if (del == 0) {
            return "删除失败";
        } else {
            return "删除成功";
        }
    }

    @Override
    public String userTaskforumidDelete(String forumid) {
        int del=userTaskMapper.userTaskforumidDelete(forumid);
        if (del == 0) {
            return "删除失败";
        } else {
            return "删除成功";
        }
    }


    @Override
    public String userTaskUpdate(String user, String usertaskProgressId,
                                 String publishertaskProgressId, int visible, String time,String forumid) {
        int upd=userTaskMapper.userTaskUpdate(user,usertaskProgressId,publishertaskProgressId,visible,time,forumid);
        if (upd == 0) {
            return "修改失败";
        } else {
            return "修改成功";
        }
    }

    @Override
    public List<UserTask> userTaskQueAll() {
        List<UserTask> a = userTaskMapper.userTaskQueAll();
        return a;
    }

    @Override
    public List<UserTask> userTaskQueAllo() {
        List<UserTask> a = userTaskMapper.userTaskQueAllo();
        return a;
    }

    @Override
    public List<Forum> userTaskForum() {
        List<Forum> a = userTaskMapper.userTaskForum();
        return a;
    }

    @Autowired
    UserTaskService userTaskService;

    //新sql执行分页查询
    @Override
    public PageResult<Forum> selForumPage(Integer page, Integer rows) {
        Page pages = PageHelper.startPage(page, rows);

        List<Forum> list=userTaskService.userTaskForum();
        System.out.println(list);
//        自己定义的-目前为空，需要赋值
        PageResult<Forum> result = new PageResult<Forum>();

        //数据
        result.setContent(list);
/*        //list还要强转
        List<Forum> con = (List<Forum>) result.getContent();
        result.setContent(con);*/

        //当前页码
        int ff = result.getPageNum();
        System.out.println("ff="+ff);
        result.setPageNum(pages.getPageNum());
        int ff1 = result.getPageNum();
        System.out.println("ff1="+ff1);
        //页面数量
        result.setPageSize(pages.getPageSize());
        //总数据量
        result.setTotalSize(pages.getTotal());
        //总页数
        result.setTotalPages(pages.getPages());
        return result;
    }

    @Override
    public List<Forum> myUserTaskForum(String user) {
        List<Forum> a = userTaskMapper.myUserTaskForum(user);
        return a;
    }

    @Override
    public PageResult<Forum> selmyForumPage(Integer page, Integer rows,String user) {
        Page pages = PageHelper.startPage(page, rows);

        List<Forum> list=userTaskService.myUserTaskForum(user);
        System.out.println(list);
//        自己定义的-目前为空，需要赋值
        PageResult<Forum> result = new PageResult<Forum>();

        //数据
        result.setContent(list);
/*        //list还要强转
        List<Forum> con = (List<Forum>) result.getContent();
        result.setContent(con);*/

        //当前页码
        int ff = result.getPageNum();
        System.out.println("ff="+ff);
        result.setPageNum(pages.getPageNum());
        int ff1 = result.getPageNum();
        System.out.println("ff1="+ff1);
        //页面数量
        result.setPageSize(pages.getPageSize());
        //总数据量
        result.setTotalSize(pages.getTotal());
        //总页数
        result.setTotalPages(pages.getPages());
        return result;
    }



    @Override
    public List<Forum> pulisherUserTaskForum(String user) {
        List<Forum> a = userTaskMapper.pulisherUserTaskForum(user);
        return a;
    }

    @Override
    public PageResult<Forum> selpublisherForumPage(Integer page, Integer rows,String user) {
        Page pages = PageHelper.startPage(page, rows);

        List<Forum> list=userTaskService.pulisherUserTaskForum(user);
        System.out.println(list);
//        自己定义的-目前为空，需要赋值
        PageResult<Forum> result = new PageResult<Forum>();

        //数据
        result.setContent(list);
/*        //list还要强转
        List<Forum> con = (List<Forum>) result.getContent();
        result.setContent(con);*/

        //当前页码
        int ff = result.getPageNum();
        System.out.println("ff="+ff);
        result.setPageNum(pages.getPageNum());
        int ff1 = result.getPageNum();
        System.out.println("ff1="+ff1);
        //页面数量
        result.setPageSize(pages.getPageSize());
        //总数据量
        result.setTotalSize(pages.getTotal());
        //总页数
        result.setTotalPages(pages.getPages());
        return result;
    }


}
