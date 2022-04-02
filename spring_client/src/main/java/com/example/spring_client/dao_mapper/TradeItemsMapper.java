package com.example.spring_client.dao_mapper;

import com.example.spring_client.entity.TradeItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TradeItemsMapper {
    public int addTradeItems(@Param("buyer")String buyer, @Param("seller") String seller,
                             @Param("time")String time, @Param("GRNWEPICTURE")byte[] wupingimage,
                                 @Param("QRCode")byte[] erweimaimage,@Param("money")int money,
                             @Param("title")String title);

    public TradeItems queTreadItemstiid(@Param("tiid")int tiid);

    public List<TradeItems> queTreadItems();

    public int queimageDelete(@Param("tiid")int tiid);

    public int queimageUpdate(@Param("buyer")String buyer, @Param("time")String time,
                              @Param("progress")String progress, @Param("tiid")int tiid);
}
