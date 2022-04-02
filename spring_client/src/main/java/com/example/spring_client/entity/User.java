package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//增加文档注释



@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类")
//使用者实体类-UserDetails-实现用户存储
public class User implements Serializable, UserDetails {
    @ApiModelProperty("userId")
    private String userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("备用字段")
    private String beiyong1;
    @ApiModelProperty("备用字段")
    private String beiyong2;
    @ApiModelProperty("备用字段")
    private String beiyong3;

    //这个好像不符合设计逻辑
    @ApiModelProperty("user连接关系表字段")
    private User_relation userRelation;

    @ApiModelProperty("用户权限表")
    private Authority authority;

    @ApiModelProperty("用户信息表")
    private UserInfo userInfo;
    @ApiModelProperty("公告表")
    private Announcement announcement;

    //一对多
    @ApiModelProperty("一对多表查询")
    private List<Announcement> announcementall;

    @ApiModelProperty("用户任务表")
    private UserTask userTask;

    @ApiModelProperty("用户任务表")
    private Task Task;


/*  得到用户的权限，如果权限表和用户表是分开的，我们需要在定义一个实体类+实现UserDetails并继承与user类，交给security的权限，放在UserDetailService进行处理
    用户的角色集，默认需要在加前缀 ROLE_
    getAuthorities获取当前角色拥有的角色信息*/


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities=new ArrayList<>();

        //给权限加上ROLE_
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.authority.getAuthorityName()));
        return authorities;
    }

    //提示用户的账号是否过期，无法验证，如果用户的账户有效（未过期），则返回true，否则返回false
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指示用户是锁定还是解锁，无法对锁定的用户进行身份验证，如果用户未被锁定，则返回ture，否则返回false
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    //指示用户的凭证（密码）是否已过期。过期的凭证阻止身份验证，如果用户的凭证有效（未过期），则返回true。如果无效（过期），否则返回false
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    //指示用户是启用还是禁用。无法对禁用的用户进行身份验证，如果启用了用户，则返回true，否则返回false
    @Override
    public boolean isEnabled() {
        return false;
    }
}
