package com.example.spring_client.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类")
public class Task {
    @ApiModelProperty("任务id")
    private String taskProgressId;
    @ApiModelProperty("任务状态")
    private String taskProgress;
}
