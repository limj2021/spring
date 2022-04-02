package com.example.spring_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableAsync    //开启异步功能
@EnableScheduling  //开启定时功能
//在启动类中加上注解,设置过期时间1分钟-redis-session
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60)

//exclude= {DataSourceAutoConfiguration.class}启动redis必须用的文件
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringClientApplication.class, args);
    }

}
