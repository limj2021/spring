package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("论坛类")
public class Forum implements Serializable {
    @ApiModelProperty("编号")
    private int id;
    @ApiModelProperty("发布人")
    private String publisher;
    //Calendar定义时间类，Date过时了
    //insert into spring.forum (time) values("2012-12-12")
    //输出的格式有"2012-12-12"，"2012/01/01"，"20120101"，20120101
    @ApiModelProperty("时间")
    private String time;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("薪水")
    private int salary;
    @ApiModelProperty("早上工作时间")
    private String workingOneHours;
    @ApiModelProperty("下午下班时间")
    private String workingTwoHours;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("连接usertask的唯一id")
    private String taskid;

//    一对一使用
    @ApiModelProperty("用户任务表")
    private UserTask userTaskone;

//    一对多-一个发布人可以发布多条信息
    @ApiModelProperty("用户任务表")
    private List<UserTask> userTask;

//    一条信息对应一个任务状态
    @ApiModelProperty("用户任务表")
    private Task Task;



}
