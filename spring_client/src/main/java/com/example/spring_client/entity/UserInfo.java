package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//注册页面需要的数据
@ApiModel("用户信息实体类")
public class UserInfo {
    @ApiModelProperty("uInfoId")
    private String uInfoId;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("电话号码")
    private String phoneNumber;
    @ApiModelProperty("昵称")
    private String nackName;
}