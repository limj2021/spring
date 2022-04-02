package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户权限实体类")
public class Authority {

    @ApiModelProperty("权限id")
    private String authorityId;
    @ApiModelProperty("权限名")
    private String authorityName;
    @ApiModelProperty("角色名")
    private String roleName;

}
