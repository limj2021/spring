package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户任务类")
public class UserTask {
    @ApiModelProperty("任务id")
    private String taskId;
    @ApiModelProperty("接收任务的用户")
    private String user;
    @ApiModelProperty("发布任务的用户")
    private String publisher;
    @ApiModelProperty("用户接收任务的状态")
    private String usertaskProgressId;
    @ApiModelProperty("发布人查看任务的状态")
    private String publishertaskProgressId;
    @ApiModelProperty("设置是否可见")
    private int visible;
    @ApiModelProperty("任务流转时间")
    private String time;
    @ApiModelProperty("连接发布信息表forum的唯一字段")
    private String forumid;

//    @ApiModelProperty("是否我的")
//    private String sfwd;

    @ApiModelProperty("通过状态判断控件的显隐")
    private int state;
}
