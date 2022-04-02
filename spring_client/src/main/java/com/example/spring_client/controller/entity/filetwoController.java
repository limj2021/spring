package com.example.spring_client.controller.entity;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

//上传MultipartFile
@Slf4j
@RequestMapping("filetwo")
@Controller
public class filetwoController {

    @Value("${file.upload.dir}")
    private String uploadFilePath;

    //单文件上传--没有进行重复文字判断
    @RequestMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {    //注意参数
        try {
            if (file.isEmpty()) {
                System.out.println("文件为空");
                return "/test/upload";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);//写日志
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);//写日志
            // 设置文件存储路径         *************************************************
//            String filePath = "./D/test";
            String filePath = "C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\";

//            getAbsolutePath获取绝对路径
            String path = filePath + fileName;
            File dest = new File(new File(path).getAbsolutePath());// dist为文件，有多级目录的文件
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {//因此这里使用.getParentFile()，目的就是取文件前面目录的路径
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            System.out.println("上传成功");
            return "/test/upload";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("上传失败");
        return "/test/upload";
    }

    // 多个文件一起上传
    @PostMapping("/batch")
    public String handleFileUpload(HttpServletRequest request) {   //注意参数
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "./FILE/KING/";            // 文件路径
            File dest = new File(filePath);
            // 检测是否存在目录
            if (!dest.exists()) {
                dest.mkdirs();// 新建文件夹
            }
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> " + e.getMessage();
                }
            } else {
                return "第 " + i + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }

    //文件下载

    @ResponseBody
    //顶级大坑，下载一定要加@ResponseBody-不然，会出问题

    @GetMapping(value = "/download")
    public String downloadFile(HttpServletResponse response)throws Exception {
        System.out.println("fileName");
        String fileName = "公告.txt";// 文件名
        System.out.println(fileName);
        String filePath = "C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\";
        if (fileName != null) {
//            C:\Users\lmj\OneDrive\桌面\springEmail\111.txt
            //设置文件路径
            File file = new File(filePath+File.separator+fileName);
            System.out.println(file);
//            //File file = new File(realPath , fileName);
            if (file.exists()) {

                try {
                    //为了重置jsp代码编译后产生。就是有jsp生成html文件的时候，html文件内部会出现很多空白行。下载后的文件内的空白行也是这样产生的-不然下载失败

                    response.reset();
                    //设置相关格式
                    response.setContentType("application/force-download");// 设置强制下载不打开
                    response.setContentLength((int) file.length());
                    response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名


                    //创建输入对象
                    FileInputStream fis = new FileInputStream(file);



                    OutputStream os = response.getOutputStream();

                    byte[] buffer = new byte[1024];
//                    int len = 0;
//                    while ((len = fis.read(buffer))!=-1){
//                        os.write(buffer,0,len);
//                    }
//                    fis.close();

                    BufferedInputStream bis = new BufferedInputStream(fis);
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }


                    System.out.println("下载成功");
                    return "/test/upload";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { // 做关闭操作

//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            }
        }
        System.out.println("下载失败");
        return "/test/upload";
    }



}
