/*
package com.example.spring_client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/*拦截器*//*

//使用了shiro或者security基本的上用不到这个
@Configuration
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功之后，应该有用户的session，拿到
        Object loginuser = request.getSession().getAttribute("loginuser");

        //如果loginuser为空，说明没有登录
        if (loginuser == null) {
            //没有登录，而是直接进入的首页，肯定是不让进的
            request.setAttribute("msg", "没有权限，请先登录");
            //转发到首页
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        } else {
            //如果存在账号，则不需要拦截
            System.out.println("已经放行");
            return true;
        }
    }
}
*/
