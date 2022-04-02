package com.example.spring_client.config.security;


import com.example.spring_client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//设计为组件--数据库取信息进行身份认证
@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    //需要新建配置类注册一个指定的加密方式Bean，或者Security配置类中指定
    @Autowired
    private PasswordEncoder passwordEncoder;

    //该方法登录时自动调用
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //输出发现，这是登录界面传入的username的值
        System.out.println(username);
        //通过用户名，从数据库获取用户信息
        com.example.spring_client.entity.User userInfo=userService.userAuthorInfoO(username);
        System.out.println(userInfo);

/*        您可以从org.springframework.security.core.context.SecurityContextHolder获取凭据。
        但是，我真的不认为这是一个好主意。您将无法使用'记住我'，'运行'或'切换用户'功能，
        因为凭证不包含当前用户的密码（它们可能为空）。
        另外，如果使用除基本HTML身份验证或表单身份验证以外的其他任何内容，我认为您不会获得明文密码。
        Object a = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println(a);*/



/*        加密
        String encodedPassword = bcryptPasswordEncoder.encode(password);
        解密
        boolean flag = bcryptPasswordEncoder.matches(password, encodedPassword);*/

        //查到的数据为空
        if(userInfo==null){
//            throw new AccountStatusException("用户状态异常");
//            throw new AccountExpiredException("账户过期");
//            throw new BadCredentialsException("坏的凭据");
//            throw new CredentialsExpiredException("证书过期");
//            throw new DisabledException("账户不可用");
//            throw new LockedException("账户锁定");
//            throw new UsernameNotFoundException("用户不存在");

            throw new MyUsernameNotFoundException("用户不存在");
        }
        //得到用户角色
        //String role = userInfo.getRole();

        //角色集合--就是一个数组而已
        List<GrantedAuthority> authorities=new ArrayList<>();
/*        角色必须以"ROLE_"开头，如果数据库中没有，就在这里添加
        Security才不管你是角色，还是权限。它只比对字符串
        所以角色信息存储的时候可以没有"ROLE_"前缀，但是包装成GrantedAuthority对象的时候必须要有*/

        //authorities.add(new SimpleGrantedAuthority("ROLE_"+userInfo.getAuthority().getAuthorityName()));
        authorities.add(new SimpleGrantedAuthority(userInfo.getAuthority().getAuthorityName()));
        System.out.println(authorities);

        //用户名 加密密码(passwordEncoder.encode因为数据库是明文，所以需要这个加密) 角色集合--去service层加密
        return new User(userInfo.getUsername(),userInfo.getPassword(),authorities);
    }

    //我自己定制的登录报错信息
    public class MyUsernameNotFoundException extends AuthenticationException {

        public MyUsernameNotFoundException(String msg) {
            super(msg);
        }
        //没意义
        public MyUsernameNotFoundException(String msg, Throwable t) {
            super(msg, t);
        }
    }




}
