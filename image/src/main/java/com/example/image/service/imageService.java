package com.example.image.service;

import com.example.image.entity.Image;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface imageService {
    public String addimage(byte[] image);

    public String addimageString(String imageString);

    //图片以base64的形式存入数据库
    public String addimagebase64(String imagebase64);

    public Image queimagestream(int imageid);

    public String queimageString(int imageid);

    public String queimageBase64(int imageid);
}
