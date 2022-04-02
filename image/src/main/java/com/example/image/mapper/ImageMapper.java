package com.example.image.mapper;


import com.example.image.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {
    public int addimage(@Param("image") byte[] image);

    //流以字符串的形式存入数据库
    public int addimageString(@Param("imageString") String imageString);

    //图片以base64的形式存入数据库
    public int addimagebase64(@Param("imagebase64") String imagebase64);

    public Image queimage(@Param("iid")int imageid);

    public String queimageString(@Param("iid")int imageid);

    public String queimageBase64(@Param("iid")int imageid);
}
