package com.example.updownload.Controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONException;
import com.sun.deploy.net.URLEncoder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class downloadController {

/*    完整的请求
    设置响应的状态码-setStatus
    设置响应消息头-Headers
    发送响应消息体-setContentType
    getOutputStream()和getWriter()*/

    @GetMapping("/download")
    public ResponseEntity uploaded1() throws Exception{
        FileSystemResource file=new FileSystemResource("C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\test.png");
        HttpHeaders headers=new HttpHeaders();
        //文件重命名
        headers.add("Content-Disposition","attachment; filename=123.png");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    //文件下载
    @ResponseBody
    //顶级大坑，下载一定要加@ResponseBody-不然，会出问题
    @GetMapping(value = "/downloadone")
    public void downloadFile(HttpServletResponse response)throws Exception {
        String fileName = "公告.txt";// 文件名
        String filePath = "C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\";
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath+File.separator+fileName);
//            文件输入流
            FileInputStream fis = null;
//            缓存输入流
            BufferedInputStream bis = null;
//            输出流
            OutputStream os = null;
//            //File file = new File(realPath , fileName);
            if (file.exists()) {
                try {
                    //为了重置jsp代码编译后产生。就是有jsp生成html文件的时候，html文件内部会出现很多空白行。下载后的文件内的空白行也是这样产生的-不然下载失败
                    response.reset();
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/octet-stream");
                    response.setContentLength((int) file.length());
                    //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
                    //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
                    // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
                    response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
                    //创建输入对象
                    fis = new FileInputStream(file);
                    os = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    bis = new BufferedInputStream(fis);
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { // 做关闭操作
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.out.println("下载失败");
    }



    // 下载到了默认的位置
//    @ResponseBody
    @GetMapping("/downloadtwo")
    public void fileDownload(HttpServletResponse response) throws JSONException, IOException {
        String fileName1 = "公告.txt";
        String uploadFilePath = "C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\";
        File file = new File(uploadFilePath + '/' + fileName1);
        if (!file.exists()) {
            System.out.println("下载文件不存在!");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName1,"UTF-8") );
        byte[] readBytes = FileUtil.readBytes(file);
        OutputStream os = response.getOutputStream();
        os.write(readBytes);
//        return result.toString();
    }


    @RequestMapping("/downloadthree")
    public void download(HttpServletResponse response) {
        try {

            String filePath = "C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\test.png";
            // path是指想要下载的文件的路径
            File file = new File(filePath);
//            log.info(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名-toLowerCase字符串变成小写
            String suffixName = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            //变成缓存输入流
            InputStream fis = new BufferedInputStream(fileInputStream);
            //available不利于网络传输
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
