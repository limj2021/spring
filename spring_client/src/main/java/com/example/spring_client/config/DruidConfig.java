/*
package com.example.spring_client.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//数据源配置
@Configuration
public class DruidConfig {
    //绑定yaml中，数据源的配置文件，访问以下路径资源
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控---
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //固定，设置搭配监控地址
        //只要访问这个路径，/druid它就会进入后台监控页面
        ServletRegistrationBean<StatViewServlet> Bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台进入验证
        HashMap<String, String> initProperties = new HashMap<>();
        //输入以下账号密码，可以进入后台
        //---监控登录页面---http://localhost:8080/druid/login.html
        //账号
        initProperties.put("loginUsername","admin");
        //密码
        initProperties.put("loginPassword","123456");
        //允许谁能访问
        initProperties.put("allow","");
        //禁止kuang访问192.168.11.123
        initProperties.put("kuang","192.168.11.123");
        //设置初始参数
        Bean.setInitParameters(initProperties);
        return Bean;

    }

    //filter---过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        //设置一个过滤器
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParameters=new HashMap<>();
        //过滤文件---以下内容不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParameters);
        return bean;
    }


}
*/
