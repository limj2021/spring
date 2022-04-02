package com.example.spring_client.controller.entity;


import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.entity.User;
import com.example.spring_client.service.AnnouncementService;
import com.example.spring_client.service.ForumService;
import com.example.spring_client.service.UserTaskService;
import io.lettuce.core.dynamic.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.CharacterDecoder;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.Principal;
import java.util.Base64;

//发送邮件
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    //Could not autowire. No beans of 'JavaMailSender' type found.，原因是邮箱配置参数找不到，
    // 解决：将邮箱配置参数迁移至 resource中 的配置文件中
    JavaMailSenderImpl mailSender;

    @Autowired
    public UserTaskService userTaskService;

    @Autowired
    public AnnouncementService announcementService;


    @Autowired
    public ForumService forumService;

    @RequestMapping("/usertest")
    public String emailtest(@RequestParam("emailfile") MultipartFile file,@RequestParam("sss")String sss){
        System.out.println("这是一个测试");
        System.out.println(sss);
        System.out.println("这是一个测试");
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        return "/test/upload";
    }


/*    @RequestMapping("/admin")
    public String emailAdmin(Model model)throws MessagingException{
        //一个复杂的邮件
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        //组装-新建一个邮箱-开启多文件
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
        //主题
        helper.setSubject("你好呀");
        //内容-可以解析HTML
        helper.setText("<p style='color:red'></p>",true);
        //附件-图片名称-地址-图片属性拿到地址
        helper.addAttachment("test.png",new File("C:\\Users\\lmj\\OneDrive\\桌面\\springEmail\\test.png"));
        //发送给自己
//        helper.setTo("2292279906@qq.com");
//        //接收邮件
//        helper.setFrom("2292279906@qq.com");

        //发送给自己
        helper.setTo("15119951776@163.com");
        //接收邮件
        helper.setFrom("15119951776@163.com");
        //发送
        mailSender.send(mimeMessage);

        PageResult<Forum> pageResult = forumService.selAllfindPage(1, 4);
        model.addAttribute("forumlist", pageResult);

        return "index";
    }*/

    @RequestMapping("/user")
    public void emailUser(){

    }


    @RequestMapping("/admin")
    public String emailAdmin(Principal principal, @RequestParam(value="emailfile",defaultValue = "null") MultipartFile file, String BASE64str,
                             @RequestParam("emailtitle")String emailtitle, @RequestParam("emailcontent")String emailcontent,
                             HttpServletRequest request, Model model) throws MessagingException, IOException {


        if ("".equals(emailcontent)){
            emailcontent=" ";
        }

        //上传图片给邮箱的两种办法
//        第一，拿到文件的绝对路径
//        第二，将拿到的图片放到某文件夹，然后拿到路径名，上传成功后，将路径图片删掉
//传入file文件
// Base64解码
// 替换掉多余的空格

//        //获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("这个值是"+fileName);
        //没有传递图片
        if ("".equals(fileName)){
            System.out.println("走了简单邮件路径");
            //一个简单的邮件
            SimpleMailMessage mimeMessage = new SimpleMailMessage();
            //主题
            mimeMessage.setSubject(emailtitle);
            //内容
            mimeMessage.setText(emailcontent);
/*
            //发送给自己
            mimeMessage.setTo("2292279906@qq.com");
            //接收邮件
            mimeMessage.setFrom("2292279906@qq.com");
*/

            //发送给自己
            mimeMessage.setTo("15119951776@163.com");
            //接收邮件
            mimeMessage.setFrom("15119951776@163.com");

            mailSender.send(mimeMessage);

            PageResult<Forum> pageResult = forumService.selAllfindPage(1, 4);
            model.addAttribute("forumlist", pageResult);
        }else{
            System.out.println("走了复杂邮件路径");
////        Base64String转为图片并保存
//        BufferedImage image = null;
//        byte[] imageByte = null;
//        try {
//            imageByte = DatatypeConverter.parseBase64Binary(BASE64str);
//            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
//            image = ImageIO.read(new ByteArrayInputStream(imageByte));
//            bis.close();
////            存储地址
//            String path="C:\\Users\\lmj\\OneDrive\\桌面\\springEmail";
//            File outputfile = new File(path);
//            ImageIO.write(image, "jpg", outputfile);

            //对字节数组字符串进行Base64解码并生成图片
            if(BASE64str == null) { //图像数据为空
                return "图片为空";
            }
            BASE64Decoder decoder = new BASE64Decoder();
            String replaceo = BASE64str.replace(" ", "");

            //判断太麻烦了，全部过滤一遍
            String aa = null;
            aa=replaceo.replace("data:image/jpeg;base64,", "");
            String bb = aa.replace("data:image/bmp;base64,", "");
            String cc = bb.replace("data:image/png;base64,", "");
            String dd = cc.replace("data:image/jpg;base64,", "");

//        类型判断
//        byte[] c = Base64.getDecoder().decode(replaceo);
//        String type = "";
//        if (0x424D == ((c[0] & 0xff) << 8 | (c[1] & 0xff))) {
//            type = "bmp";
//            aa=replaceo.replace("data:image/bmp;base64,", "");
//        } else if (0x8950 == ((c[0] & 0xff) << 8 | (c[1] & 0xff))) {
//            type = "png";
//            aa=replaceo.replace("data:image/png;base64,", "");
//        } else if (0xFFD8 == ((c[0] & 0xff) << 8 | (c[1] & 0xff))) {
//            type = "jpg";
//            aa=replaceo.replace("data:image/jpg;base64,", "");
//        }
            try {
                //Base64解码
                byte[] b = decoder.decodeBuffer(dd);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {//调整异常数据
                        b[i] += 256;
                    }
                }
                System.out.println(b);
                //生成jpeg图片
                String path = "D:\\test";
//            String imgFilePath = "D:\\tupian\\new.jpg";//新生成的图片
                String imgFilePath = path + File.separator + fileName;//新生成的图片
                //放到本地
                OutputStream out = new FileOutputStream(imgFilePath);
                System.out.println("文件下载成功");
                out.write(b);
                out.flush();
                out.close();
//            return "成功";

                //一个复杂的邮件
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                //组装-新建一个邮箱-开启多文件
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                //主题
                helper.setSubject(emailtitle);
                //内容-可以解析HTML
                helper.setText("<p style='color:green'>"+emailcontent+"</p>",true);
                //附件-图片名称-地址-图片属性拿到地址
                File fileemail = new File(imgFilePath);
                helper.addAttachment(fileName, fileemail);
                //发送给自己
//        helper.setTo("2292279906@qq.com");
//        //接收邮件
//        helper.setFrom("2292279906@qq.com");

                //发送给自己
                helper.setTo("15119951776@163.com");
                //接收邮件
                helper.setFrom("15119951776@163.com");
                //发送
                mailSender.send(mimeMessage);


                EmailController dle=new EmailController();
                //删除文件
                dle.deletefile(imgFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("出错了");
//            return "出错了";
                e.printStackTrace();
            }
        }


        PageResult<Forum> pageResult = userTaskService.selForumPage(1, 4);
        for (Forum forum : pageResult.getContent()) {
            //登录者
            String user = principal.getName();
            user.replace(" ", "");
            System.out.println("这是登录人" + user);
            //发布者
            String sqlPublisher = forum.getUserTaskone().getPublisher();
            sqlPublisher.replace(" ", "");
            System.out.println("这是发布者" + sqlPublisher);
            //领取者
            String sqlUser = forum.getUserTaskone().getUser();
            sqlUser.replace(" ", "");
            System.out.println("这是领取者" + sqlUser);
            //领取者状态
            String taskuser = forum.getUserTaskone().getUsertaskProgressId();
            taskuser.replace(" ", "");
            System.out.println("这是领取者状态" + taskuser);
            //发布者状态
            String taskpublisher = forum.getUserTaskone().getPublishertaskProgressId();
            taskpublisher.replace(" ", "");
            System.out.println("这是发布者状态" + taskpublisher);

            SkipController a = new SkipController();
            a.ifusertask(user, sqlPublisher, sqlUser, taskuser, taskpublisher, forum);
        }
        model.addAttribute("forumlist", pageResult);

        //最新的公告数据
        User one = announcementService.userAnnouncementone();
        model.addAttribute("announcementone", one);
        return "index";
//        return "/test/upload";
    }

    //文件删除
    public void deletefile(String str){
//        try {
            //三秒后，删除文件
//            Thread.sleep(3000);

            File dfile = new File(str);
            if(dfile.exists()) {
                dfile.delete();
                System.out.println("删除成功");
            }else{
                System.out.println("没有删除成功");
            }
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }

        System.out.println("数据正在处理");
    }


    /**
     * <p>
     * 1.base64字符串转化成图片（对字节数组字符串进行Base64解码并生成图片）
     * </p>
     * <p>
     * 2.首先要检查是否存在data:image/png;base64,(类似content-type),如果有的话， 去掉。
     * </p>
     * <p>
     * 3.通过BASE64Decoder 接口进行解码 （BASE64Decoder位于%JAVA_HOME%\jdk1.7.0_45\jre\lib\rt.jar中）
     * </p>
     * <p>
     * 4.最后通过FileOutputStream 文件流生成文件
     * </p>
     *
     * @param base64Str
     * @param userName
     * @param filePath
     * @author zhangyd-c
     * @date 2015年5月29日 下午2:49:22
     * @return String
     */
   /* public static String generateImage(String base64Str, String userName,String filePath) {
        // 去掉前面的data:image/png;base64,
        if (base64Str.indexOf("data:image/png;base64,") != -1) {
            base64Str = base64Str.replace("data:image/png;base64,", "");
        }
        BASE64Decoder decoder = new BASE64Decoder();
        // 生成jpeg图片
        FileOutputStream out = null;
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            if (EmptyUtil.isNullStr(filePath)) {
                String newFileName = userName + CommonUtil.getDateString(new Date(), "yyyyMMhhHHmmss") + ".jpg";// 生成的新的文件名
                filePath = getFilePath() + newFileName;
            }
            out = new FileOutputStream(filePath);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            return null;
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }*/

}
