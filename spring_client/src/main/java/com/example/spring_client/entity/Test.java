package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
//这只是一个测试类
@ApiModel("测试实体类")
public class Test {
    @ApiModelProperty("测试用户")
    private String username;
    @ApiModelProperty("测试密码")
    private String password;
}
