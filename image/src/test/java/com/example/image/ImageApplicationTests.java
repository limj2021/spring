package com.example.image;

import com.example.image.entity.Image;
import com.example.image.service.imageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageApplicationTests {
    @Autowired
    imageService imageService;

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        Image a = imageService.queimagestream(2);
        System.out.println(a);
    }



}
