package com.example.spring_client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展springmvc,springmvc将全面接管
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //添加视图解析器,视图解析器就是解析html文件的
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //视图跳转
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/forum").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/skip/index").setViewName("index");
        registry.addViewController("/skip/register").setViewName("register");
        registry.addViewController("/skip/userManagement").setViewName("userManagement");
        //registry.addViewController("/skip/forum").setViewName("index");
    }

    //拦截资源--注册到配置里--最重要的一步--没有这个，无法拦截
/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns增加全部拦截
        //excludePathPatterns不能被拦截，通行资源
        //--/user/login登录请求不能被拦截

        //还有很多没有过滤,很多页面都不能被拦截，到时候处理

        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login.html", "/", "/user/login","/skip/forum","/forum/forumAll","/user/registerAll","/skip/userManagement", "/skip/register", "/css/*", "/js/**", "/img/**");
        //首页不能被拦截
    }*/

    //将组件注册到配置里---最重要的一步
    @Bean
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }

//    @Autowired
//    private FileUploadInterceptor fileUploadInterceptor;

}