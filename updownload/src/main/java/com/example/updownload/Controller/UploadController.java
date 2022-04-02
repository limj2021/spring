package com.example.updownload.Controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import com.sun.deploy.net.URLEncoder;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("/")
    public String upload() {
        return "upload";
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            System.out.println("上传失败，请选择有内容的文件");
        } else {
            System.out.println("文件类型：" + file.getContentType());
        }
        try {
            //内存流，将内存作为一个临时文件保存，不会在磁盘上产生文件
            InputStream in = file.getInputStream();
            System.out.println("内容如下："+new String(readStream(in)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\lmj\\OneDrive\\桌面\\springEmail"+ File.separator + fileName;
//        原生代码
//        BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(filePath));
//        outputStream.write(file.getBytes());
//        outputStream.flush();
//        outputStream.close();
        File files = new File(filePath);
        //判断目录是否存在
        if(!files.getParentFile().exists()){
            //如果目标文件所在目录不存在，则创建父目录
            files.getParentFile().mkdirs();
            //如果创建成功
            if (files.exists()) {
                System.out.println("判断文件是否创建成功");
            }else{
                //创建失败
                return "失败，结束";
            }
        }
        try {
            file.transferTo(files);
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }


    //多文件上传
    @RequestMapping("/uploadFiles")
    @ResponseBody
    public void fileUploads(@RequestParam("file") MultipartFile files[]){
        //文件上传路径
        String uploadFilePath="C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\";
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();
            File moreFile = new File(uploadFilePath + File.separator + fileName);
            if (!moreFile.getParentFile().exists()) {
                moreFile.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(moreFile);
            } catch (Exception e) {
                System.out.println("文件上传失败");
            }
        }
//        return "多文件上传成功";
    }

    //文件上传-不用
    @RequestMapping(value = "/multiUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {
            System.out.println("当前文件是一个空文件");
        }
        //获取的是文件的完整名称，包括文件名称+文件拓展名
        String fileName = file.getOriginalFilename();
        //指定索引截取到结尾（返回指定字符的位置）-即拿到位置，然后从指定位置开始截取-拿到文件后缀名，比如.exe
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //文件上传路径
        String uploadFilePath="C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\" + File.separator + fileName;
        //两种一样的File files = new File(new File(uploadFilePath).getAbsolutePath());
        File files = new File(uploadFilePath);
        //判断目录是否存在
        if(!files.getParentFile().exists()){
            //如果目标文件所在目录不存在，则创建父目录
            files.getParentFile().mkdirs();
            //如果创建成功
            if (files.exists()) {
                System.out.println("判断文件是否创建成功");
            }else{
                //创建失败
                return "失败，结束";
            }
        }
        try {
            // 写入文件:方式1
            file.transferTo(files);
            // 写入文件:方式2-写字节码-hutool
//            FileUtil.writeBytes(file.getBytes(), files);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出了异常");
        }
        return "上传成功";
    }

    //多文件上传-不用
    @PostMapping("/multiUploads")
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {   //注意参数
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        String uploadFilePath="C:\\Users\\lmj\\OneDrive\\桌面\\springEmail";

        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);

            String fileName =file.getOriginalFilename();

            // 文件路径判断
            File dest = new File(uploadFilePath);
            // 检测是否存在目录
            if (!dest.exists()) {
                dest.mkdirs();// 新建文件夹
            }
            if (!file.isEmpty()) {
                try {
//                    File dest = new File(filePath + fileName);
//                    file.transferTo(dest);
                    byte[] bytes = file.getBytes();
                    //设置文件路径及名字
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadFilePath +File.separator+fileName)));
                    // 写入
                    stream.write(bytes);
                    stream.close();
                    LOGGER.info("第" + (i + 1) + "个文件上传成功");
                } catch (Exception e) {
                    LOGGER.error(e.toString(), e);
                    return "第 " + i+1 + " 个文件上传失败 ==> " + e.getMessage();
                }
            } else {
                return "第 " + i+1 + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }

    @ResponseBody
    @PostMapping("/deleteFile")
    public String deleteFile(HttpServletResponse response) throws JSONException {
        JSONObject result = new JSONObject();
        //文件上传路径
        String fileName = "test.png";
        String uploadFilePath="C:\\Users\\lmj\\OneDrive\\桌面\\springEmail";
        File file = new File(uploadFilePath + File.separator + fileName);
        System.out.println(file);
        // 判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            result.put("success", "文件不存在!");
            return result.toString();
        }
        try {
            if (file.isFile()) {
                file.delete();
            } else {
                // 文件夹, 需要先删除文件夹下面所有的文件, 然后删除
                for (File temp : file.listFiles()) {
                    temp.delete();
                }
                file.delete();
                System.out.println("文件删除");
            }
        } catch (Exception e) {
            result.put("error", e.getMessage());
            return result.toString();
        }
        result.put("success", "删除成功!");
        return result.toString();
    }




}


