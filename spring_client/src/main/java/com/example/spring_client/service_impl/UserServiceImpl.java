package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.UserMapper;
import com.example.spring_client.entity.User;
import com.example.spring_client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
//使用者服务层

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //加密
    @Autowired
    private PasswordEncoder passwordEncoder;

/*    @Override
    public String addUser(User user){
        //获取值，加密，然后在赋值
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (add == 0) {
            return "用户添加失败";
        } else {
            return "用户添加成功";
        }
    }*/


    //添加用户

    @Override
    public String UserAdd(String userId,String user, String password) {
        int add=userMapper.userAdd(userId,user,password);
        if (add == 0) {
            return "用户添加失败";
        } else {
            return "用户添加成功";
        }
    }

    //删除用户
    @Override
    public String UserDelete(String user) {
        int dle=userMapper.userDelete(user);
        if (dle == 0) {
            return "用户删除失败";
        } else {
            return "用户删除成功";
        }
    }

    @Override
    public String useridDelete(String userId) {
        int dle=userMapper.useridDelete(userId);
        if (dle == 0) {
            return "用户删除失败";
        } else {
            return "用户删除成功";
        }
    }

    @Override
    public String userUpUpdate(String userId) {
        int dle=userMapper.userUpUpdate(userId);
        if (dle == 0) {
            return "用户修改失败";
        } else {
            return "用户修改成功";
        }
    }

    @Override
    public String userDownUpdate(String userId) {
        int dle=userMapper.userDownUpdate(userId);
        if (dle == 0) {
            return "用户修改失败";
        } else {
            return "用户修改成功";
        }
    }

    //修改用户
    @Override
    public String UserUpdate(String user, String password) {
        int upd=userMapper.userUpdate(user,password);
        if (upd == 0) {
            return "用户删除失败";
        } else {
            return "用户删除成功";
        }
    }

    //查询全部
    @Override
    public List<User> UserQueAll() {
        List<User> all=userMapper.userQueAll();
        return all;
    }

    //查询一个用户
    @Override
    public User UserQueOne(String user) {
        User que=userMapper.userQueOne(user);
        return que;
    }

    @Override
    public List<User> userAuthor() {
        List<User> all=userMapper.userAuthor();
        return all;
    }

    @Override
    public List<User> userAuthorThree() {
        List<User> all=userMapper.userAuthorThree();
        return all;
    }

    @Override
    public User userAuthorOne(String user) {
        User all=userMapper.userAuthorOne(user);
        return all;
    }

    @Override
    public List<User> userInfo() {
        List<User> all=userMapper.userInfo();
        return all;
    }

    @Override
    public List<User> userInfoThree() {
        List<User> all=userMapper.userInfoThree();
        return all;
    }

    @Override
    public User userInfoOne(String user) {
        User all=userMapper.userInfoOne(user);
        return all;
    }

    @Override
    public List<User> userAuthorInfo() {
        List<User> all=userMapper.userAuthorInfo();
        return all;
    }

    @Override
    public User userAuthorInfoO(String user) {
        User all=userMapper.userAuthorInfoO(user);
        return all;
    }

}
