package com.example.spring_client.controller.entity;

import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.User;
import com.example.spring_client.service.AnnouncementService;
import com.example.spring_client.service.ForumService;
import com.example.spring_client.service.UserTaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/skip")
//这是实现跳转的控制类
public class SkipController {

    @Autowired
    public ForumService forumService;

    @Autowired
    public AnnouncementService announcementService;

    @Autowired
    public UserTaskService userTaskService;

//    Principal principal;

    //跳转到register
    @ApiOperation("跳转到register控制类")
    @RequestMapping("/register")
    public String skipRegister() {
        return "register";
    }

    //跳转到login
    @ApiOperation("跳转到index控制类")
    @RequestMapping("/login")
    public String skipLogin() {
        return "login";
    }

    //跳转到userManagement
    @ApiOperation("跳转到userManagement控制类")
    @RequestMapping("/userManagement")
    public String skipUserManagement(Model model) {
        String Empty = "这只是一个空白判断";
        model.addAttribute("Empty", Empty);
        return "userManagement";
    }


    //跳转到forum
    //https://blog.csdn.net/a755199443/article/details/94554320
    @ApiOperation("跳转到forum控制类")
    @RequestMapping("/forum")
    public String itemList(Principal principal, @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "4") Integer rows, Model model) {
        System.out.println("CustomerController.itemList");

//        PageResult<Forum> pageResult = forumService.selAllfindPage(page, rows);
//        model.addAttribute("forumlist", pageResult);


        PageResult<Forum> pageResult = userTaskService.selForumPage(page, rows);
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
//            String类型不能用==比较--如果比较失败，也许是空格的原因

//            switch case 语句????

//            发布者不是你，没有人领取
/*            if (!sqlPublisher.equals(user)&&"".equals(sqlUser)){
                System.out.println("它走了a"+!sqlPublisher.equals(user)+"-"+"".equals(sqlUser));
                //判断控件的显影
                forum.getUserTaskone().setState(1);
                forum.getTask().setTaskProgress("a-发布中，可领取");
            }
//            发布者是你，但是没有人领取
            else if(sqlPublisher.equals(user)&&"".equals(sqlUser)){
                System.out.println("它走了b"+sqlPublisher.equals(user)+"-"+"".equals(sqlUser));
                forum.getUserTaskone().setState(2);
//                System.out.println(sqlPublisher.equals(user)+"-"+"".equals(sqlUser));
                forum.getTask().setTaskProgress("b-我发布的，未领取");
            }
//            发布者不是你，领取者也不是你
            else if(!sqlPublisher.equals(user)&&!sqlUser.equals(user)){
                System.out.println("它走了c"+!sqlPublisher.equals(user)+"-"+!sqlUser.equals(user));
                forum.getUserTaskone().setState(3);
                forum.getTask().setTaskProgress("c-该任务已其他人被领取");
            }
//            发布者是你，领取人不是你
            else if(sqlPublisher.equals(user)&&!sqlUser.equals(user)&&!"".equals(sqlUser)&&!"005".equals(taskpublisher)){
                System.out.println("它走了d"+sqlPublisher.equals(user)+"-"+!sqlUser.equals(user)+"-"+!"".equals(sqlUser)+"-"+!"005".equals(taskpublisher));
                forum.getUserTaskone().setState(4);
                forum.getTask().setTaskProgress("d-我发布的任务，被已领取");
            }
//            发布者不是我，领取人是我
            else if(!sqlPublisher.equals(user)&&sqlUser.equals(user)&&!"005".equals(taskuser)){
                System.out.println("它走了e"+!sqlPublisher.equals(user)+"-"+sqlUser.equals(user)+"-"+!"005".equals(taskuser));
                forum.getUserTaskone().setState(5);
                forum.getTask().setTaskProgress("e-我收到的任务，执行中");
            }
//            发布人不是你，领取人是你，某个值等于005，说明领取人完成了
            else if(!sqlPublisher.equals(user)&&sqlUser.equals(user)&&"005".equals(taskuser)){
                System.out.println("它走了f"+!sqlPublisher.equals(user)+"-"+sqlUser.equals(user)+"-"+"005".equals(taskuser));
                forum.getUserTaskone().setState(6);
                forum.getTask().setTaskProgress("f-我处理的任务已完成");
            }
//            发布人是你，领取人不是你，某个值等于005，说明发布人可以删除完成的任务
            else if(sqlPublisher.equals(user)&&!sqlUser.equals(user)&&"005".equals(taskpublisher)){
                System.out.println("它走了g"+sqlPublisher.equals(user)+"-"+!sqlUser.equals(user)+"-"+"005".equals(taskpublisher));
                forum.getUserTaskone().setState(7);
                forum.getTask().setTaskProgress("g-删除任务");
            }
            else{
                System.out.println("它走了h");
                forum.getUserTaskone().setState(8);
                System.out.println(sqlPublisher.equals(user)+"-"+sqlUser.equals(user)+"-"+"005".equals(taskuser)+"-"+"005".equals(taskpublisher));
                forum.getTask().setTaskProgress("h-异常状态");
            }
            System.out.println();
            System.out.println();*/

            model.addAttribute("forumlist", pageResult);

            //最新的公告数据
            User one = announcementService.userAnnouncementone();
            model.addAttribute("announcementone", one);

            return "index";
        }



    @ApiOperation("")
    @RequestMapping("/myuser")
    public String itemListMytask(Principal principal, @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "4") Integer rows, Model model) {

        //登录者
        String user = principal.getName();
        PageResult<Forum> pageResult = userTaskService.selmyForumPage(page, 4,user);
        for (Forum forum : pageResult.getContent()) {
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
        return "task";
    }

    //跳转到forum
    //https://blog.csdn.net/a755199443/article/details/94554320
    @ApiOperation("")
    @RequestMapping("/pulisher")
    public String itemListpublishertask(Principal principal, @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "4") Integer rows, Model model) {

//        PageResult<Forum> pageResult = forumService.selAllfindPage(page, rows);
//        model.addAttribute("forumlist", pageResult);

        //登录者
        String user = principal.getName();
        PageResult<Forum> pageResult = userTaskService.selpublisherForumPage(page, 4,user);
        for (Forum forum : pageResult.getContent()) {

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
        model.addAttribute("announcementone", one);

        return "task";
    }



    public void ifusertask(String user, String sqlPublisher, String sqlUser, String taskuser, String taskpublisher, Forum forum) {
        if (!sqlPublisher.equals(user) && "".equals(sqlUser)) {
            System.out.println("它走了a" + !sqlPublisher.equals(user) + "-" + "".equals(sqlUser));
            //判断控件的显影
            forum.getUserTaskone().setState(1);
            forum.getTask().setTaskProgress("a-发布中，可领取");
        }
//            发布者是你，但是没有人领取
        else if (sqlPublisher.equals(user) && "".equals(sqlUser)) {
            System.out.println("它走了b" + sqlPublisher.equals(user) + "-" + "".equals(sqlUser));
            forum.getUserTaskone().setState(2);
//                System.out.println(sqlPublisher.equals(user)+"-"+"".equals(sqlUser));
            forum.getTask().setTaskProgress("b-我发布的，未领取");
        }
//            发布者不是你，领取者也不是你
        else if (!sqlPublisher.equals(user) && !sqlUser.equals(user)) {
            System.out.println("它走了c" + !sqlPublisher.equals(user) + "-" + !sqlUser.equals(user));
            forum.getUserTaskone().setState(3);
            forum.getTask().setTaskProgress("c-该任务已其他人被领取");
        }
//            发布者是你，领取人不是你
        else if (sqlPublisher.equals(user) && !sqlUser.equals(user) && !"".equals(sqlUser) && !"005".equals(sqlUser) && !"005".equals(taskpublisher)) {
            System.out.println("它走了d" + sqlPublisher.equals(user) + "-" + !sqlUser.equals(user) + "-" + !"".equals(sqlUser) + "-" + !"005".equals(taskpublisher));
            forum.getUserTaskone().setState(4);
            forum.getTask().setTaskProgress("d-我发布的任务，被已领取");
        }
//            发布者不是我，领取人是我
        else if (!sqlPublisher.equals(user) && sqlUser.equals(user) && !"005".equals(taskuser)) {
            System.out.println("它走了e" + !sqlPublisher.equals(user) + "-" + sqlUser.equals(user) + "-" + !"005".equals(taskuser));
            forum.getUserTaskone().setState(5);
            forum.getTask().setTaskProgress("e-我收到的任务，执行中");
        }
//            发布人不是你，领取人是你，某个值等于005，说明领取人完成了
        else if (!sqlPublisher.equals(user) && sqlUser.equals(user) && "005".equals(taskuser)) {
            System.out.println("它走了f" + !sqlPublisher.equals(user) + "-" + sqlUser.equals(user) + "-" + "005".equals(taskuser));
            forum.getUserTaskone().setState(6);
            forum.getTask().setTaskProgress("f-我处理的任务已完成");
        }
//            发布人是你，领取人不是你，某个值等于005，说明发布人可以删除完成的任务
        else if (sqlPublisher.equals(user) && !sqlUser.equals(user) && "005".equals(taskpublisher)) {
            System.out.println("它走了g" + sqlPublisher.equals(user) + "-" + !sqlUser.equals(user) + "-" + "005".equals(taskpublisher));
            forum.getUserTaskone().setState(7);
            forum.getTask().setTaskProgress("g-删除任务");
        } else {
            System.out.println("它走了h");
            forum.getUserTaskone().setState(8);
            System.out.println(
                    sqlPublisher.equals(user) + "-"
                    + sqlUser.equals(user) + "-"
                    + !"".equals(sqlUser) + "-"
                    + "005".equals(taskuser) + "-"
                    + "005".equals(taskpublisher));
            forum.getTask().setTaskProgress("h-异常状态");
        }
    }


    //https://blog.csdn.net/a755199443/article/details/94554320
    @ApiOperation("")
    @RequestMapping("/showAllCustomer")
    public String itemList1(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "4") Integer rows, Model model) {
        System.out.println("CustomerController.itemList");

        PageResult<Forum> pageResult = forumService.selAllfindPage(page, rows);
        model.addAttribute("forumlist", pageResult);
        return "two";
    }


    @ApiOperation("跳转到register控制类")
    @RequestMapping("/upload")
    public String skipupload() {
        return "/test/upload";
    }


    @ApiOperation("跳转到register控制类")
    @RequestMapping("/tanchukuang")
    public String skipuptest() {
        return "/test/tanchuk";
    }


    @ApiOperation("跳转到物品交易控制类")
    @RequestMapping("/TradeTtems")
    public String skipTradeTtems() {

        return "/TradeItems";
    }
}