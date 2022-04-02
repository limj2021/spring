package com.example.spring_client2.controller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //不对密码加密
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                //管理员用户，具有
                .withUser("admin").roles("ADMIN","USER")
                .and()
                //普通用户
                .withUser("beixi").password("beixi").roles("USER");
    }

    @Override
    protected void  configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                //普通用户访问url
                .antMatchers("/user/**").hasRole("USER")
                //管理员访问url
                .antMatchers("/admin/**").hasRole("ADMIN")
                //其他途径认证方式
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("login")
                .permitAll()    //访问/login接口不需要进行身份认证，防止重定向死循环
                .and()
                .csrf().disable();    //关闭csrf
    }

}
