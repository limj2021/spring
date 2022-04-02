package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//公告库
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("公告库")
public class Announcement {
    private String userId;
    private String time;
    private String title;
    //公告
    private String content;
    private int aid;
}
