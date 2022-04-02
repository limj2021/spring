package com.example.spring_client.entity;

import com.sun.javaws.progress.Progress;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeItems {
    /*id*/

    private int tiid;
//    买家
    private String buyer;
/*    卖家*/
    private String seller;
//    时间
    private String time;
//    物品图片
    private byte[] wupingimage;
//    付款二维码图片
    private byte[] erweimaimage;
//    定价金额
    private int money;
    //物品说明
    private String title;

    @ApiModelProperty("通过状态判断控件的显隐")
    //没有做数据映射
    private int state;
    //控件信息
    private String intformation;

    private String Progress;
}
