package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.TradeItemsMapper;
import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.TradeItems;
import com.example.spring_client.service.TradeItemsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeItemsServiceImpl implements TradeItemsService {
    @Autowired
    TradeItemsMapper tradeItemsMapper;


    @Override
    public String addTradeItems(String buyer, String seller, String time,byte[] wupingimage,byte[] erweimaimage, int money, String title) {
        int add=tradeItemsMapper.addTradeItems(buyer, seller, time,wupingimage,erweimaimage,money,title);
        if (add == 0) {
            return "添加失败";
        } else {
            return "添加成功";
        }
    }

    @Override
    public TradeItems queTreadItemstiid(int tiid) {
        TradeItems queone = tradeItemsMapper.queTreadItemstiid(tiid);
        return queone;
    }

    @Override
    public List<TradeItems> queTreadItems() {
        List<TradeItems> queall = tradeItemsMapper.queTreadItems();
        return queall;
    }

    @Override
    public String queimageDelete(int tiid) {
        int dle = tradeItemsMapper.queimageDelete(tiid);
        if (dle == 0) {
            return "删除失败";
        } else {
            return "删除成功";
        }
    }

    @Override
    public String queimageUpdate(String buyer, String time, String progress, int tiid) {
        int upd = tradeItemsMapper.queimageUpdate(buyer,time,progress,tiid);
        if (upd == 0) {
            return "修改失败";
        } else {
            return "修改成功";
        }
    }


//    @Override
//    public PageResult<TradeItems> selTradeItemsPage(Integer page, Integer rows) {
//        Page pages = PageHelper.startPage(page, rows);
//
//        List<TradeItems> list=tradeItemsMapper.queTreadItems();
//        System.out.println(list);
////        自己定义的-目前为空，需要赋值
//        PageResult<TradeItems> result = new PageResult<TradeItems>();
//
//        //数据
//        result.setContent(list);
///*        //list还要强转
//        List<Forum> con = (List<Forum>) result.getContent();
//        result.setContent(con);*/
//
//        //当前页码
//        int ff = result.getPageNum();
//        System.out.println("ff="+ff);
//        result.setPageNum(pages.getPageNum());
//        int ff1 = result.getPageNum();
//        System.out.println("ff1="+ff1);
//        //页面数量
//        result.setPageSize(pages.getPageSize());
//        //总数据量
//        result.setTotalSize(pages.getTotal());
//        //总页数
//        result.setTotalPages(pages.getPages());
//        return result;
//    }
}
