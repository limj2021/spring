package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//设为组件
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("redis测试类")
//序列化--正常企业项目中，传参都需要序列化
//public class RedisUser implements Serializable {
public class RedisUser {

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("年龄")
    private int age;
}
