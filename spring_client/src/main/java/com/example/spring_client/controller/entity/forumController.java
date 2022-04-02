package com.example.spring_client.controller.entity;

import com.example.spring_client.dao_mapper.AnnouncementMapper;
import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageRequest;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.User;
import com.example.spring_client.service.ForumService;
import com.example.spring_client.service.UserTaskService;
import com.example.spring_client.util.MyUtil;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("forum")
public class forumController {
    @Autowired
    public ForumService forumService;

    @Autowired
    public UserTaskService userTaskService;

    @Autowired
    public AnnouncementMapper announcementService;

    @ApiOperation("用户增加控制类类")
    @RequestMapping("add")
    public String forumadd(Principal principal,
                           @RequestParam("title")String title, @RequestParam("content")String content,
                             @RequestParam("salary")int salary, @RequestParam("workingOneHours")String workingOneHours,
                             @RequestParam("workingTwoHours")String workingTwoHours, @RequestParam("address")String address,
                             @RequestParam("phoneNumber")String phoneNumber, Model model){
//        https://www.cnblogs.com/cat520/p/13059855.html
        //获取当前用户名(作为发布人的值)
        System.out.println(principal.getName());
        String publisher=principal.getName();

        //format_date是获取当前时间
        String format_date=MyUtil.currentTime();

        //建立一个与usertask表唯一的id
        String taskid = "user" + MyUtil.getUUID(6);
        //写usertask表的相关数据
        String add=userTaskService.userTaskAdd("",publisher,"001","001",1,format_date,taskid);
        System.out.println("运行了"+add);
        //如果其中一个值为空，则增加失败
        forumService.forumAdd(publisher,format_date,title,content,salary,workingOneHours,workingTwoHours,address,phoneNumber,taskid);



        //最新的公告数据

        User one = announcementService.userAnnouncementone();
        model.addAttribute("announcementone",one);

//        List<Forum> all=forumService.forumQueAll();
//        model.addAttribute("forumlist",all);
//        PageResult<Forum> pageResult = forumService.selAllfindPage(1, 4);
//        model.addAttribute("forumlist", pageResult);


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
        return "index";
    }

    @ApiOperation("用户查询全部控制类")
    @RequestMapping("all")
    public String forumAll(Model model){
        List<Forum> all=forumService.forumQueAll();
        model.addAttribute("forumlist",all);
        return "index";
    }

    @ApiOperation("论坛模糊查询控制类")
    @RequestMapping("like")
    public String forumlike(String title,Model model){
        List<Forum> like=forumService.forumQueLike(title);
        model.addAttribute("froumlike",like);
        return "index";
    }

    @ApiOperation("论坛查询全部控制类")
    @RequestMapping("forumAll")
    public String uforumAll(Model model){
        List<Forum> all=forumService.forumQueAll();
        model.addAttribute("forumlist",all);
        return "userManagement";
    }

    @ApiOperation("论坛删除控制类")
    @RequestMapping("forumDelete")
    public String forumDelete(int id,Model model){
        String delete=forumService.forumDelete(id);
        List<Forum> all=forumService.forumQueAll();
        model.addAttribute("forumlist",all);
        return "userManagement";
    }

    //https://www.cnblogs.com/xifengxiaoma/p/11027551.html
    @ApiOperation("")
    @RequestMapping("/findPage")
    public Object findPage(@RequestParam(defaultValue="1")Integer request,Model model){
        PageRequest pageQuery=new PageRequest();
        pageQuery.setPageNum(request);
        pageQuery.setPageSize(2);

        PageResult page = forumService.findPage(pageQuery);
//        List<?> list = page.getContent();
        //取数据
        List<Forum> list = (List<Forum>) page.getContent();
        System.out.println(list);
        model.addAttribute("forumlist",list);
        model.addAttribute("Pages",page);
        return "one";
    }

    //https://blog.csdn.net/a755199443/article/details/94554320
    @ApiOperation("")
    @RequestMapping("/showAllCustomer")
    public String itemList(@RequestParam(defaultValue="1")Integer page,
                           @RequestParam(defaultValue="4")Integer rows, Model model){
        System.out.println("CustomerController.itemList");

        PageResult<Forum> pageResult = forumService.selAllfindPage(page, rows);
        model.addAttribute("forumlist", pageResult);
        return "two";
    }



    @ApiOperation("")
    @RequestMapping("/updateUserTask")
    public String itemListone(Principal principal,@RequestParam("taskid")String taskid, Model model){
        //登录者
        String user = principal.getName();
        user.replace(" ", "");
        System.out.println("这是登录人" + user);
        String time=MyUtil.currentTime();

        userTaskService.userTaskUpdate(user,"004","001",
                0,time,taskid);
        PageResult<Forum> pageResult = userTaskService.selForumPage(1, 4);
        for (Forum forum : pageResult.getContent()) {

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
        return "index";
    }

    @ApiOperation("")
    @RequestMapping("/updateUserTasktwo")
    public String itemListtwo(Principal principal,@RequestParam("taskid")String taskid, Model model){
        //登录者
        String user = principal.getName();
        user.replace(" ", "");
        System.out.println("这是登录人" + user);
        String time=MyUtil.currentTime();

        userTaskService.userTaskUpdate(user,"005","005",
                0,time,taskid);
        PageResult<Forum> pageResult = userTaskService.selmyForumPage(1, 4,user);
        for (Forum forum : pageResult.getContent()) {

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
        model.addAttribute("mytasklist", pageResult);
        return "task";
    }

    @ApiOperation("")
    @RequestMapping("/updateUserTaskdelete")
    public String itemListthreedelete(Principal principal,@RequestParam("taskid")String taskid, Model model){
        //登录者
        String user = principal.getName();
        user.replace(" ", "");
        System.out.println("这是登录人" + user);

        //删除
        forumService.forumtaskidDelete(taskid);
        userTaskService.userTaskforumidDelete(taskid);


        PageResult<Forum> pageResult = userTaskService.selpublisherForumPage(1, 4,user);
        for (Forum forum : pageResult.getContent()) {

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
        model.addAttribute("publlisherlist", pageResult);
        return "task";
    }

    @ApiOperation("")
    @RequestMapping("/myusertaskall")
    public String myusertaskall( @RequestParam(defaultValue = "1") Integer page,Principal principal, Model model){
        //登录者
        String user = principal.getName();
        user.replace(" ", "");
        System.out.println("这是登录人" + user);

        PageResult<Forum> pageResult = userTaskService.selmyForumPage(page, 4,user);
        for (Forum forum : pageResult.getContent()) {

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
        model.addAttribute("mytasklist", pageResult);
        return "task";
    }



    @ApiOperation("")
    @RequestMapping("/publisherusertaskall")
    public String publisherusertaskall(@RequestParam(defaultValue = "1") Integer page,Principal principal, Model model){
        //登录者
        String user = principal.getName();
        user.replace(" ", "");
        System.out.println("这是登录人" + user);

        PageResult<Forum> pageResult = userTaskService.selpublisherForumPage(page, 4,user);
        for (Forum forum : pageResult.getContent()) {

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
        model.addAttribute("publlisherlist", pageResult);
        return "task";
    }


}
