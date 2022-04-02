package com.example.spring_client.controller.entity;

import com.example.spring_client.entity.PageRequest;
import com.example.spring_client.entity.Register;
import com.example.spring_client.entity.User;
import com.example.spring_client.service.RegisterService;
import com.example.spring_client.service.UserInfoService;
import com.example.spring_client.service.UserRelationService;
import com.example.spring_client.service.UserService;
import com.example.spring_client.util.MyUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class RegisterController {
//    提供注册表接口-已废弃
    @Autowired
    private RegisterService registerService;
    //提供用户表接口
    @Autowired
    private UserService userService;
    //提供加密
    @Autowired
    private PasswordEncoder passwordEncoder;
    //提供关系表接口
    @Autowired
    private UserRelationService userRelationService;
    //提供用户信息表接口
    @Autowired
    private UserInfoService userInfoService;

    //注册，user表,user_relation表,userinfo表写入信息
    @ApiOperation("注册用户类")
    @RequestMapping("/register")
    public String register(@RequestParam("registerEmail")String email, @RequestParam("registerPhone")String phoneNumber,
                           @RequestParam("registerNackName")String nackname,@RequestParam("registerUserName")String username,
                           @RequestParam("registerPassword") String password, @RequestParam("registerAgainPassword")String passwordAgain,
                           Model model, HttpSession session){
        //判断有没有该用户
        User registerOne= userService.UserQueOne(username);

        //registerOne.equals(null)-----错误用法
        //("null".equals(registerOne)-----错误用法
        //(null.equals(registerOne)-----错误用法
        if (null==registerOne){
            if (password.equals(passwordAgain)){
                //register表写入信息
                //registerService.registerAdd(email,phoneNumber,username,password);

                //加密密码
                String encodepassword=passwordEncoder.encode(password);


                //获取8位字符id值
                String userid= MyUtil.getUUID(8);

                //002默认是用户
                String authorityid="002";
                //uinfoid默认是8位数字
                String uinfoid=String.valueOf(MyUtil.getNum(8));


                //大坑，注意了，int类型不能接收11位电话号码，因为最大就是10位，11位溢出了-现在用string
                //int iphoneNumber = Integer.parseInt(phoneNumber);
                //强制转换
                //Integer iphoneNumber=Integer.valueOf(phoneNumber).intValue();

                //用户信息表写入信息
                userInfoService.userInfoAdd(uinfoid,email,phoneNumber,nackname);

                //user表写入信息
                userService.UserAdd(userid,username,encodepassword);
                //userRelationService里面的urid是自增字段
                userRelationService.userRelationAdd(userid,authorityid,uinfoid);
                //因为input控件参数类型是String-传过来，然后转换int
                return "login";
            }else {
                model.addAttribute("error","两次输入的密码不一致");
                return "register";
            }
        }else {
            model.addAttribute("error","该用户名已存在，请重新输入");
            return "register";
        }
    }

    //注册用户-权限，放入数据管理

    @ApiOperation("查询注册用户-权限控制类")
    @RequestMapping("/userAuthorAll")
    public String userAuthorAll(Model model){
        List<User> all=userService.userAuthor();
        model.addAttribute("userAuthorlist",all);
        return "userManagement";
    }
    //@GetMapping是一个组合注解相当与是@RequestMapping(method=Request.GET)的缩写;
    //@PostMapping是一个组合注解相当与是@RequestMapping(method=Request.POST)的缩写;
    //删除

    @ApiOperation("用户删除控制类")
    @RequestMapping("/userAuthorDelete")
    public String userAuthoDelete(String user,Model model){
        //String a=registerService.registerDelete(user);
        System.out.println("22222");
        User one=userService.userAuthorInfoO(user);
        System.out.println(one);
        System.out.println(one.getUserInfo().getUInfoId());

        userInfoService.userInfoDelete(one.getUserInfo().getUInfoId());
        userRelationService.userRelationDelete(one.getUserId());
        userService.UserDelete(user);

        //查询全部
        List<User> all=userService.userAuthor();
        model.addAttribute("userAuthorlist",all);
        return "userManagement";
    }


    @ApiOperation("用户删除控制类")
    @RequestMapping("/userupdownUpdate")
    public String userAuthoUpdate( @RequestParam("userid")String userid,@RequestParam("id")String id,Model model){
        String a="001";
        String b="002";

        if (a.equals(id)){
            //降权限
            userService.userDownUpdate(userid);
            System.out.println("她执行了001变002");
        }else if (b.equals(id)){
            //升级权限
            userService.userUpUpdate(userid);
            System.out.println("她执行了002变001");
        }else{
            System.out.println("她执行了什么");
        }
        //String a=registerService.registerDelete(user);

        //查询全部
        List<User> all=userService.userAuthor();
        model.addAttribute("userAuthorlist",all);
        return "userManagement";
    }

    //注册用户-信息，放入数据管理

    @ApiOperation("查询注册用户-信息控制类")
    @RequestMapping("/userInfolist")
    public String userInoAll(Model model){
        List<User> all=userService.userInfo();
        model.addAttribute("userInfolist",all);
        return "userManagement";
    }

    @ApiOperation("用户删除控制类")
    @RequestMapping("/userInfoDelete")
    public String userInfoDelete(String user,Model model){
        User one=userService.userAuthorInfoO(user);
        System.out.println(one);
        //userRelationService.userRelationDelete(one.getUserId());
        //userInfoService.userInfoDelete(one.getUserInfo().getUInfoId());
        //userService.UserDelete(user);
        //查询全部
        List<User> all=userService.userInfo();
        model.addAttribute("userInfolist",all);
        return "userManagement";
    }


}
