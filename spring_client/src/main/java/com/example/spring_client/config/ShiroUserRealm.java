/*
package com.example.spring_client.config;


import com.example.spring_client.entity.User;
import com.example.spring_client.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义类，shiro
public class ShiroUserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加一个权限--所有权限，只有加了user:add--才能授权--添加地址在ShiroConfig的授权方法中
        info.addStringPermission("user:add");//写的是死代码--还是要把权限放到数据库


        Subject subject = SecurityUtils.getSubject();//拿到当前登录的这个对象
        User currentUser = (User) subject.getPrincipal();//就是来获取你存储的principal--从下面的认证方法中
//        设置当前用户的权限--这里是表里面设置了权限--把表里的权限拿出来用
        //info.addStringPermission(currentUser.getPerms());//前提是user表里面设置了权限--字段Perms
        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;//强制转换

        //封装用户的登录数据
//        连接真实数据库--拿到名字
        User user = userService.UserQueOne(userToken.getUsername());
        if (user == null) {//说明没有这个人
            return null;//它会自动抛出异常
        }

//        假设用户名和密码是root,root
        String user="root";String password="root";
        //进行比较
        if (!userToken.getUsername().equals(user)){
            return null;//它会自动抛出异常
        }

        //放到session里面---前端取值：${session.loginUser}
        Subject currentsubject = SecurityUtils.getSubject();
        Session session = currentsubject.getSession();
        session.setAttribute("loginUser",user);


        //第一个位置是可以给授权拿到相关信息--存储的principal--给当前用户放入资源
        return new SimpleAuthenticationInfo(user,password,"");

        //密码认证；shiro加密
        //return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
*/
