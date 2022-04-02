/*
package com.example.spring_client.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //身份验证、授权、密码和会话管理
    //自定义类---注册到spring--托管--注册进去的名字为userRealmShiro--固定--第一步
    @Bean(name="userRealmShiro")
    public ShiroUserRealm userRealm(){
        return new ShiroUserRealm();
    }
    //DefaultWebSecurityManager--固定--第二步
    //@Qualifier获取方法名
    @Bean(name="SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealmShiro") ShiroUserRealm userRealm) {
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //ShiroFiterFactoryBean--固定--第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFiterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置用户管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滋器
//        anon：无需认证就可以访问
//        authc：必须认证了才能让问
//        user：必须别有记在我功能才能用
//        perms：加有对某个资源的权限才能访问
//        role：拥有某个角色权限才能访问
//        拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        //授权--如果没有授权，要跳转未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");

        //前端--权限判断<div shiro:hasPermission="user:add"></div>


        //拦截所有人
        filterMap.put("/skip/*", "authc");
        filterMap.put("user/update", "authc");

        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录页面--登录页--方法--不登陆会一直跳转到以下页面
        bean.setLoginUrl("/");

//        未授权页面--已经设置了未授权页面的跳转功能--/noauth在ShiroController中
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }


    //整合ShiroDialect:整合shiro和thymeleaf--可以在thymeleaf里面用shiro标签
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }


}
*/
