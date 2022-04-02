package com.example.spring_client.service;

import com.example.spring_client.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public String UserAdd(String userId,String user, String password);

    public String UserDelete(String user);

    public String useridDelete( String userId);

    public String userUpUpdate(String userId);

    public String userDownUpdate(String userId);

    public String UserUpdate(String user, String password);

    public List<User> UserQueAll();

    public User UserQueOne(String user);

    public List<User> userAuthor();

    public List<User> userAuthorThree();

    public User userAuthorOne(String user);

    public List<User> userInfo();

    public List<User> userInfoThree();

    public User userInfoOne(String user);

    public List<User> userAuthorInfo();

    public User userAuthorInfoO(String user);

//    public int addUser(User user);
}
