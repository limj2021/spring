package com.example.image.controller;

import com.example.image.entity.Image;
import com.example.image.service.imageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class imageController {

    @RequestMapping("/")
    public String upload() {
        return "image";
    }

    @Autowired
    imageService imageService;

    //以流,String,base64编码放入数据库

    @PostMapping(value = "/saveimg")
    @ResponseBody
    public String searchMember(MultipartFile file){
        try {
            //拿到输入流
            InputStream ins = file.getInputStream();
            byte[] buffer=new byte[1024];
            int len=0;
            //建立缓冲区输出流
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            //将输入流的数据放入缓冲区输出流
            while((len=ins.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
            bos.flush();
            //将缓冲区输出流变成字节数组-创建一个新分配的字节数组。数组的大小和当前输出流的大小，内容是当前输出流的拷贝
            byte[] data = bos.toByteArray();


//            字符串的形式放入数据库
            //变成字符串-测试-存入的值比流的形式大得多-应该不合适使用
            String data1 = bos.toString();
            imageService.addimageString(data1);

            //前端图片base64放入数据库测试

            Image image=new Image();
            //　　　　　　　image.setIid(sId);
            image.setImage(data);
            //byte[]以的形式放入数据库
            imageService.addimage(data);
            //记得关闭流
            ins.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUCCESS_TIP";
    }

    //图片路径放入数据库
    @PostMapping(value = "/downloadpathimg")
    @ResponseBody
    public String imagepathMysql(){

        return "下载成功";
    }


    //数据库文件下载到本地(流)
    @PostMapping(value = "/downloadstramimg")
    @ResponseBody
    public String imagedownload(){
        Image a = imageService.queimagestream(1);
        System.out.println(a);
        return "下载成功";
    }


    @RequestMapping("/getimage")
    @ResponseBody
//    @ApiOperation("获取头像")
    public ResponseEntity<byte[]> getByName(String username){
        Image a = imageService.queimagestream(2);
        byte[] imageContent = a.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/home")
    public ModelAndView home(@RequestParam("name") String name ) throws IOException {
        ModelAndView view = new ModelAndView("index");
        view.addObject("image_name", name);
        return view;
    }
    @RequestMapping(value = "/image/{image_name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name) throws Exception{

        byte[] imageContent ;
        String path = "your image path with image_name";
        imageContent = fileToByte(new File(path));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }
    public static byte[] fileToByte(File img) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }


    //数据库图片String形式下载到本地
    @PostMapping(value = "/downloadstringimg")
    @ResponseBody
    public String imagedownloadString(){
        imageService.addimageString("1");


        return "下载成功";
    }


    //数据库base64图片下载到本地
    @PostMapping(value = "/downloadbase64img")
    @ResponseBody
    public String imagedownloadbase64(){



        return "下载成功";
    }


    //数据库文件输出到前端(流的形式)
    @PostMapping(value = "/ouputqianimg")
    @ResponseBody
    public String imageqianstream(){



        return "下载成功";
    }

    //数据库文件输出到前端(base64)
    @PostMapping(value = "/upputbase64img")
    @ResponseBody
    public String imageqianbase64(){



        return "下载成功";
    }

    //数据库文件输出到前端(String)
    @PostMapping(value = "/ouputstringimg")
    @ResponseBody
    public String imageqianstring(){



        return "下载成功";
    }

}


