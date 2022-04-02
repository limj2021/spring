package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户联系中间实体类")
public class User_relation {

    @ApiModelProperty("中间表id")
    private int urId;
    @ApiModelProperty("连接user表字段")
    private String userId;
    @ApiModelProperty("连接authority表字段")
    private String authorityId;
    @ApiModelProperty("连接user")
    private String uInfoId;

    @ApiModelProperty("用户权限表")
    private Authority authority;

    @ApiModelProperty("用户信息表")
    private UserInfo userInfo;

}
