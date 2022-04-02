package com.example.spring_client.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity   //springSecurity用于启用web安全注解
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
//开启方法访问权限安全认证--用于控制层
/*prePostEnabled = true会解锁@PreAuthorize和@PostAuthorize两个注解
@PreAuthorize注解（“hasAnyRole('user')"）--只要user角色可以访问--会在方法执行前验证
@PostAuthorize注解会在方法执行后验证
securedEnabled = true会解锁@Secured注解*/


//重写configure方法--自定义配置
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //将写好的组件，自动装配
    @Autowired
    private CustomerUserDetailsService CuserDetailsService;


    //指定加密方式--修改加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        //使用BCrypt加密密码
        return new BCryptPasswordEncoder();
        //不设置密码加密，已经不安全
        //return NoOpPasswordEncoder.getInstance();
    }

    //认证
    //PasswordEncoder--密码编码--即加密

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception {
        //从数据库读取用户进行身份验证--自定义编写配置类
        auth.userDetailsService(CuserDetailsService).passwordEncoder(passwordEncoder());

        //super.configure(auth);
        //password("")里面的密码，不加密，是不能用的，必须加密--passwordEncoder(new BCryptPasswordEncoder())
/*        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                //增加编码方式new BCryptPasswordEncoder().encode()
                //password(new BCryptPasswordEncoder().encode(new BCryptPasswordEncoder().encode("123456")))----二重加密----密码，不知道
                .password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("kuangshen1")
                .password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2")
                .and()
                .withUser("kuangshen2")
                .password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");*/

    }


    //允许注册用户的访问请求--授权
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        System.out.println("1231312");
        //设置权限访问
        http.authorizeRequests()
                //antMatchers("")---拦截的是请求，不是页面
                //user可以访问user下面的连接
                //.antMatchers("/user/**").hasRole("SUER")
                //admin可以访问admin下面的连接
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3")
                //post请求可以进入addUser
                //.antMatchers(HttpMethod.POST,"/addUser").permitAll()
                //login,注册所有人都可以访问--不需要身份认证
                .antMatchers("/login","/skip/register","/skip/login","/user/register","/register").permitAll()
                //其他路径都必须认证
                .anyRequest().authenticated()
                .and()
                //使用默认的登录页面-login
                .formLogin()
//                用户未登录时，访问任何资源都转跳到该路径
                .loginPage("/login")
                //登录表单form中用户名输入框input的name-验证成功跳转-验证失败
                .loginProcessingUrl("/login")
//                登录表单form中用户名输入框input的name名,password名
                .usernameParameter("username").passwordParameter("password")

                //成功跳转至首页-神级大坑，如果后面没有加true，跳转的时候会失灵
//        假如我们还没有登录认证，在浏览器输入一个不存在的url，例如localhost:8080/test，那通过此前的配置security会帮我们导向登录页面，然后当我们登录成功后你会发现跳转的路径变成了/test，而不是设置的/main。
//        使用第二个方法，并且第二参数置为true，那么就不会出现上面问题，会直接转到/main
                .defaultSuccessUrl("/skip/forum",true)
//                .defaultSuccessUrl("/skip/forum")
                // 登录失败Url
                .failureUrl("/login?error")

                //关闭csrf--- CSRF即跨站请求攻击。简单的说，是攻击者通过一些技术手段欺骗用户的浏览器去访问一个自己以前认证过的站点并运行一些操作
                // （如发邮件，发消息，甚至财产操作（如转账和购买商品））。因为浏览器之前认证过，所以被访问的站点会绝点是这是真正的用户操作而去运行。
                //post请求关闭csrf验证，不然访问报错，实际开发中开启，需要前端配合传递其他参数
                .and().csrf().disable();

        //定制登录页面
/*        http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/login").defaultSuccessUrl("/skip/forum",true);*/


        //<a th:href="{/logout}"></a>---注销链接
        //注销-返回首页
        http.logout().logoutSuccessUrl("/login");


        //开启记住我功能---本身是一个cookie---默认保存
        http.rememberMe().rememberMeParameter("remember");

        //没有权限默认会到登录页面,需要开启登录的页面---没有权限回到首页---查询一下
        //http.formLogin().loginPage("/index").loginProcessingUrl("/login");
    }

    @Override
    public void configure(WebSecurity web){
        //将项目中静态资源路径开放出来
        web.ignoring().antMatchers( "/css/**", "/img/**", "/js/**");
        //web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
    }

}
