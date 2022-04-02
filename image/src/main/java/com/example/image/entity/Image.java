package com.example.image.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image{
    private int Iid;
    private byte[] image;
    private String imageString;
    private String imagebase64;
}
