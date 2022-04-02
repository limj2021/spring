package com.example.spring_client.controller.entity;

import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.User;
import com.example.spring_client.service.AnnouncementService;
import com.example.spring_client.service.ForumService;
import com.example.spring_client.service.UserService;
import com.example.spring_client.service.UserTaskService;
import com.example.spring_client.util.MyUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/Announcement")
public class AnnouncementController{
    @Autowired
    AnnouncementService announcementService;

    @Autowired
    UserService userService;

    @Autowired
    UserTaskService userTaskService;

    @Autowired
    public ForumService forumService;

    @RequestMapping("/add")
    public String announcementadd(@RequestParam(defaultValue="1")Integer page,
                                  @RequestParam(defaultValue="4")Integer rows,
                                  Principal principal, @RequestParam("announcementTitle")String title,
                                  @RequestParam("announcementContent")String content, Model model){
//        System.out.println(title+content);
        String time = MyUtil.currentTime();
//        拿到当前用户名
        String username=principal.getName();
        User user1 = userService.UserQueOne(username);
        String userid = user1.getUserId();
        String add = announcementService.announcementAdd(userid,time,title,content);


        PageResult<Forum> pageResult = userTaskService.selForumPage(1, 4);
        for (Forum forum : pageResult.getContent()) {
            //登录者
            String user = principal.getName();
            user.replace(" ", "");
            System.out.println("这是登录人" + user);
            //发布者
            String sqlPublisher = forum.getUserTaskone().getPublisher();
            sqlPublisher.replace(" ", "");
            System.out.println("这是发布者" + sqlPublisher);
            //领取者
            String sqlUser = forum.getUserTaskone().getUser();
            sqlUser.replace(" ", "");
            System.out.println("这是领取者" + sqlUser);
            //领取者状态
            String taskuser = forum.getUserTaskone().getUsertaskProgressId();
            taskuser.replace(" ", "");
            System.out.println("这是领取者状态" + taskuser);
            //发布者状态
            String taskpublisher = forum.getUserTaskone().getPublishertaskProgressId();
            taskpublisher.replace(" ", "");
            System.out.println("这是发布者状态" + taskpublisher);

            SkipController a = new SkipController();
            a.ifusertask(user, sqlPublisher, sqlUser, taskuser, taskpublisher, forum);
        }
        model.addAttribute("forumlist", pageResult);

        //最新的公告数据
        User one = announcementService.userAnnouncementone();
        model.addAttribute("announcementone",one);
        return "index";
    }

    @RequestMapping("delete")
    public String announcementdle(@Param("aid")int aid,Principal principal,Model model){
//        String username=principal.getName();
//        User user = userService.UserQueOne(username);
//        String userid = user.getUserId();

        announcementService.announcementDelete(aid);
        List<User> all = announcementService.userAnnouncement();
        model.addAttribute("announcementlist",all);
        return "userManagement";
    }

    @RequestMapping("/queone")
    public String announcementqueone(Model model){

        User one = announcementService.userAnnouncementone();

        model.addAttribute("announcementone",one);
        return "index";
    }

    @RequestMapping("/all")
    public String announcementqueall(Model model){
        List<User> all = announcementService.userAnnouncement();
        model.addAttribute("announcementlist",all);
        return "userManagement";
    }
}
