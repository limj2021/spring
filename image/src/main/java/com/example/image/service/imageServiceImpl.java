package com.example.image.service;

import com.example.image.entity.Image;
import com.example.image.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class imageServiceImpl implements imageService{

    @Autowired
    ImageMapper imageMapper;

    @Override
    public String addimage(byte[] image) {
        int add=imageMapper.addimage(image);
        if (add==0){
            System.out.println("增加失败");
        }else{
            System.out.println("增加成功");
        }
        return "";
    }

    @Override
    public String addimageString(String imageString) {
        int add=imageMapper.addimageString(imageString);
        if (add==0){
            System.out.println("增加失败");
        }else{
            System.out.println("增加成功");
        }
        return "";
    }

    @Override
    public String addimagebase64(String imagebase64) {
        int add=imageMapper.addimagebase64(imagebase64);
        if (add==0){
            System.out.println("增加失败");
        }else{
            System.out.println("增加成功");
        }
        return "";
    }

    @Override
    public Image queimagestream(int imageid) {
        Image que = imageMapper.queimage(imageid);
        return que;
    }

    @Override
    public String queimageString(int imageid) {
        String que = imageMapper.queimageString(imageid);
        return que;
    }

    @Override
    public String queimageBase64(int imageid) {
        String que = imageMapper.queimageBase64(imageid);

        return que;
    }
}
