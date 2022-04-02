package com.example.spring_client.controller.entity;

import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.User;
import com.example.spring_client.service.ForumService;
import com.example.spring_client.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
//登录控制页
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    public ForumService forumService;

    //废弃测试页
    //给实体类增加控制
    @ApiOperation("注册用户测试控制表")
    @RequestMapping("/test")
    public String test(){
        return "register";
    }

    //暂时拿数据验证
    @ApiOperation("用户登录控制类")
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      Model model, HttpSession session) {
        System.out.println("获得值" + "测试用户=" + username + "+密码=" + password );
        //具体的业务,判断是否为空
        if (!ObjectUtils.isEmpty(username) && !ObjectUtils.isEmpty(password)) {
            //当数据库查询到了两条，会报错-----重点容易报错
            User user = userService.UserQueOne(username);

            System.out.println(user);
            //判断用户是否为空
            if (user == null) {
                model.addAttribute("error", "该用户不存在");
                return "login";
            } else {
                //该用户,拿到数据库用户密码
                String pass = user.getPassword();
//                判断密码是否填写正确
                if (pass.equals(password)) {
                    //将登录的用户，以会话形式保存下来
                    session.setAttribute("loginUser", username);
                    //获得查询用户全部信息
//                    List<User> all = userService.UserQueAll();
                    //传递给下一页面的数据
//                    model.addAttribute("lists",all);
                    List<Forum> all=forumService.forumQueAll();
                    model.addAttribute("forumlist",all);
                    return "index";
                } else {
                    model.addAttribute("error", "密码错误");
                    return "login";
                }
            }
//            判断没有填写的值
        } else if (!ObjectUtils.isEmpty(username) && ObjectUtils.isEmpty(password)) {
            model.addAttribute("error", "密码没有填写");
            return "login";
//            判断没有填写的值
        } else if (ObjectUtils.isEmpty(username) && !ObjectUtils.isEmpty(password)) {
            model.addAttribute("error", "用户没有填写");
            return "login";
//            判断未知错误
        } else {
            model.addAttribute("error", "发生其他异常错误");
            return "login";
        }
    }


    //注销
    @ApiOperation("用户注销控制类")
    @RequestMapping("/loginout")

    public String loginout(HttpSession session) {
        //移除session会话，会返回首页重新登录
        session.removeAttribute("loginuser");
        //重定向其实是为了登陆成功后，如果重复刷新页面会重复提交数据，而以重定向的方法可以避免重复提交登录的表单数据
        return "redirect:/index.html";
    }

}
