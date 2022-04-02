package com.example.spring_client.service;

import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.TradeItems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeItemsService {

    public String addTradeItems(String buyer,String seller,String time,byte[] wupingimage,
                                    byte[] erweimaimage,int money,String title);

    public TradeItems queTreadItemstiid(@Param("tiid")int tiid);

    public List<TradeItems> queTreadItems();

    public String queimageDelete(int tiid);

    public String queimageUpdate(String buyer,String time,String progress,int tiid);

//    public PageResult<TradeItems> selTradeItemsPage(Integer page, Integer rows);
}
