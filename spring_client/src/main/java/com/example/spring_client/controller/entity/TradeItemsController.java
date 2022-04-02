package com.example.spring_client.controller.entity;

import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.TradeItems;
import com.example.spring_client.service.TradeItemsService;
import com.example.spring_client.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/trade")
public class TradeItemsController {

    @Autowired
    private TradeItemsService tradeItemsService;

    @RequestMapping("/add")
    public String add(Principal principal, @RequestParam("fileWuPing") MultipartFile wupingimage,
                      @RequestParam("fileErWeiMa") MultipartFile erweimaimage, @RequestParam("money")int money,
                      @RequestParam("title")String title,Model model){
        try {

            String a=wupingimage.getOriginalFilename();
            String b=erweimaimage.getOriginalFilename();
            System.out.println(a + "-"+b);

            String time = MyUtil.currentTime();
//        拿到当前用户名-卖家
            String seller=principal.getName();

            //拿到物品图片
            //拿到输入流
            InputStream inswuping = wupingimage.getInputStream();
            byte[] buffer=new byte[1024];
            int len=0;
            //建立缓冲区输出流
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            //将输入流的数据放入缓冲区输出流
            while((len=inswuping.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
            bos.flush();
            //将缓冲区输出流变成字节数组-创建一个新分配的字节数组。数组的大小和当前输出流的大小，内容是当前输出流的拷贝
            byte[] wupingimagedata = bos.toByteArray();



            //拿到二维码图片
            //拿到输入流
            InputStream inserweima = erweimaimage.getInputStream();
            //建立缓冲区输出流
            ByteArrayOutputStream bos1=new ByteArrayOutputStream();
            //将输入流的数据放入缓冲区输出流
            while((len=inserweima.read(buffer))!=-1){
                bos1.write(buffer,0,len);
            }
            bos1.flush();
            //将缓冲区输出流变成字节数组-创建一个新分配的字节数组。数组的大小和当前输出流的大小，内容是当前输出流的拷贝
            byte[] erweimaimagedata = bos1.toByteArray();

            System.out.println("判断"+ Arrays.equals(wupingimagedata,erweimaimagedata));

            tradeItemsService.addTradeItems("", seller, time,wupingimagedata,erweimaimagedata,money,title);

            //记得关闭流
            inswuping.close();
            bos.close();

            inserweima.close();
            bos1.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String user=principal.getName();
        List<TradeItems> all = tradeItemsService.queTreadItems();
        for (int i=0;i<all.size();i++){
            TradeItems a=all.get(i);
            String seller = a.getSeller();
            String buyer = a.getBuyer();
            String progress=a.getProgress();
            iftradeitems( seller, buyer, user, a,progress);
        }
        model.addAttribute("tradeItemsAll",all);
        return "/TradeItems";
    }

    @RequestMapping("/delete")
    public String deleteTradeItems(Model model, @RequestParam("tiid")int tiid){
        tradeItemsService.queimageDelete(tiid);

        List<TradeItems> all = tradeItemsService.queTreadItems();
        model.addAttribute("tradeItemsAll",all);
        return "/TradeItems";
    }


    @RequestMapping("/update")
    public String updateTradeItems(Principal principal,Model model, @RequestParam("tiid")int tiid){

        String abuyer=principal.getName();
        String time=MyUtil.currentTime();
        String aprogress="001";
        tradeItemsService.queimageUpdate(abuyer,time,aprogress,tiid);

        List<TradeItems> all = tradeItemsService.queTreadItems();
        for (int i=0;i<all.size();i++){
            TradeItems a=all.get(i);
            String seller = a.getSeller();
            String buyer = a.getBuyer();
            String progress=a.getProgress();
            iftradeitems( seller, buyer, abuyer, a,progress);
        }
        model.addAttribute("tradeItemsAll",all);
        return "/TradeItems";
    }

    @RequestMapping("/updateshop")
    public String updateshopTradeItems(Principal principal,Model model, @RequestParam("tiid")int tiid){

        String abuyer=principal.getName();
        String time=MyUtil.currentTime();
        String aprogress="005";
        tradeItemsService.queimageUpdate(abuyer,time,aprogress,tiid);

        List<TradeItems> all = tradeItemsService.queTreadItems();
        for (int i=0;i<all.size();i++){
            TradeItems a=all.get(i);
            String seller = a.getSeller();
            String buyer = a.getBuyer();
            String progress=a.getProgress();
            iftradeitems( seller, buyer, abuyer, a,progress);
        }
        model.addAttribute("tradeItemsAll",all);
        return "/TradeItems";
    }



    @RequestMapping("/all")
    public String all(Principal principal, Model model) {
        String user=principal.getName();

//        for (int i=0;i<all.size();i++){
//            TradeItems a=all.get(i);
//            byte[] b = a.getWupingimage();
//            ResponseEntity c=getByName(b);
//            a.setWupingimage(b);
//
//            System.out.println(a.getWupingimage());
//        }
        List<TradeItems> all = tradeItemsService.queTreadItems();
        for (int i=0;i<all.size();i++){
            TradeItems a=all.get(i);
            String seller = a.getSeller();
            String buyer = a.getBuyer();
            String progress=a.getProgress();
            iftradeitems( seller, buyer, user, a,progress);
        }
        model.addAttribute("tradeItemsAll",all);
        return "/TradeItems";
    }
//    @ResponseBody
//    @ApiOperation("获取头像")
//    public ResponseEntity<byte[]> getByName(byte[] data){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(data, headers, HttpStatus.OK);
//    }

    //获取物品图片
    @RequestMapping("/getwupingimage")
    @ResponseBody
//    @ApiOperation("获取头像")
    public ResponseEntity<byte[]> getByName(@RequestParam("fileimage")int tiid){
        TradeItems a = tradeItemsService.queTreadItemstiid(tiid);
        byte[] imageContent = a.getWupingimage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }


    //获取二维码图片
    @RequestMapping("/geterweimaimage")
    @ResponseBody
//    @ApiOperation("获取头像")
    public ResponseEntity<byte[]> getBytiiderweima(@RequestParam("fileimage")int tiid){
        TradeItems a = tradeItemsService.queTreadItemstiid(tiid);
        byte[] imageContent = a.getErweimaimage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }


/*    //分页
    @RequestMapping("/allpage")
    public String allsetpage(Principal principal, Model model) {
        PageResult<TradeItems> all =  tradeItemsService.selTradeItemsPage(1,4);
        model.addAttribute("tradeItemsAllpage",all);
        return "/TradeItems";
    }*/

    public void iftradeitems(String seller,String buyer,String user,TradeItems tradeItems,String progress){
        //发布人不是你，没有人领取
        if (!seller.equals(user)&&"".equals(buyer)){
            tradeItems.setState(1);
            tradeItems.setIntformation("贩卖中");
        }
        //发布者是你，但是没有人领取
        else if (seller.equals(user) && "".equals(buyer)) {
            tradeItems.setState(2);
            tradeItems.setIntformation("我发布的，还没有买家");
        }
        //发布者是你，被别人领取
        else if(seller.equals(user) && !"".equals(buyer)&& "001".equals(progress)) {
            tradeItems.setState(3);
            tradeItems.setIntformation("我的物品在交易中");
        }
        //发布者不是你，我已经领取
        else if(!seller.equals(user) && user.equals(buyer)&& !"005".equals(progress)) {
            tradeItems.setState(4);
            tradeItems.setIntformation("我的购物车中,请支付");
        }
        //发布者不是你，被别人领取
        else if(!seller.equals(user) && !user.equals(buyer)&& !"005".equals(progress)) {
            tradeItems.setState(5);
            tradeItems.setIntformation("正在被别人交易");
        }
//      发布人不是你，领取人是你，某个值等于005，说明领取人完成了
        else if (!seller.equals(user) && user.equals(buyer) && "005".equals(progress)) {
            tradeItems.setState(6);
            tradeItems.setIntformation("购买完成");
        }
//      发布人是你，领取人不是你，某个值等于005，说明发布人可以删除完成的任务
        else if (seller.equals(user) && !user.equals(buyer) && "005".equals(progress)) {
            tradeItems.setState(7);
            tradeItems.setIntformation("删除交易信息");
        }
        else {
            tradeItems.setState(8);
            tradeItems.setIntformation("异常状态");
        }
    }



}
