package com.example.spring_client2.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//配置第三方数据源
@Configuration
public class DuridConfig {

    //配置一个拦截登录
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean reg = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String, String> initParams=new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginUsername","123456");
        initParams.put("allow","");
        reg.setInitParameters(initParams);
        return reg;
    }

    //配置一个web监控filter，哪些请求会被监控，哪些会被排除
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        Map<String, String> initParams=new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}



//自定义druid数据源进行绑定
class Config{
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }
}