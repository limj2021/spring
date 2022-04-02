package com.example.spring_client.util;

/**
 * @（#）:Base64Util.java
 * @description: Base64加解密工具类
 * @author:2015年5月21日
 * @version: Version 1.0
 */
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Util {

    private static Logger logger = LoggerFactory.getLogger(Base64Util.class);

    /**
     * 加密
     *
     * @param str
     *            加密明文
     * @return 加密后的密文
     */
    public static String encode(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    /**
     * 解密
     *
     * @param str
     *            解密密文
     * @return 明文
     */
    public static String decode(String str) {
        byte[] b = null;
        String result = null;
        if (str != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String cc=   encode("123456789");
        System.out.println(cc);
        decode(cc);
        System.out.println(decode(cc));
    }
}