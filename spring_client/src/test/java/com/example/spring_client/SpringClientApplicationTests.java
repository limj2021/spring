package com.example.spring_client;

import com.example.spring_client.dao_mapper.TestMapper;
import com.example.spring_client.dao_mapper.TradeItemsMapper;
import com.example.spring_client.dao_mapper.UserInfoMapper;
import com.example.spring_client.entity.*;
import com.example.spring_client.service.*;

import com.example.spring_client.service_impl.TestServiceImpl;
import com.example.spring_client.util.RedisUtil;
import com.example.spring_client.util.MyUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.BMPattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import sun.security.provider.MD5;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringClientApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("这是一个测试程序");
    }



    //test
    @Autowired
    public TestServiceImpl testservice;
    @Test//test表简单增加功能
    void add() {
        testservice.add("11", "22");
    }
    @Test//test表简单删除功能
    void delete() {
        testservice.delete("11");
    }
    @Test//test表简单修改功能
    void updeta() {
        testservice.update("222", "333");
    }
    @Test//test表简单查询功能
    void qurall() {
        System.out.println(testservice.queAll());
    }
    @Test//test表查询单个数据
    void qurone() {
        com.example.spring_client.entity.Test a = testservice.queOne("222");
        if (a == null) {
            System.out.println("没有查询到");
        } else {
            String b = a.getPassword();
            System.out.println("密码" + b);
            System.out.println("查询到了" + a);
        }
        //异常抛出
        try {

        }catch (NullPointerException e){
            System.out.println("出现空指针异常");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //user表测试
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test//user表增加
    void useradd(){
        //获取8位id值
        String id=MyUtil.getUUID(8);
        System.out.println(userService.UserAdd(id,"111",passwordEncoder.encode("222")));
    }

    @Test//user表删除
    void userdelete(){
        System.out.println(userService.UserDelete("111"));
    }
    @Test//user表修改
    void userupdate(){
        System.out.println(userService.UserUpdate("111","333"));
    }
    @Test//user表查询一个
    void userqueone(){
        System.out.println(userService.UserQueOne("111"));
    }
    @Test//user查询全部
    void userqueall(){
        System.out.println(userService.UserQueAll());
    }
    @Test
    void userAuthorAll(){
        List<User> all = userService.userAuthor();
        for (int i=0;i<all.size();i++){
            User a=all.get(i);
            System.out.println(a);
        }
    }

    @Test
    void userAuthorThree(){
        List<User> all = userService.userAuthorThree();
        for (int i=0;i<all.size();i++){
            User a=all.get(i);
            System.out.println(a);
        }
    }

    @Test
    void userAuthorOne() {
//        User all=userService.userAuthorOne("111");
        User all=userService.userAuthorOne("阿大撒");
        System.out.println(all);
    }

    @Test
    void userInfo(){
        List<User> all = userService.userInfo();
        for (int i=0;i<all.size();i++) {
            User a = all.get(i);
            System.out.println(a);
        }
    }

    @Test
    void userInfoThree(){
        List<User> all = userService.userInfoThree();
        for (int i=0;i<all.size();i++) {
            User a = all.get(i);
            System.out.println(a);
        }
    }

    @Test
    void userInfoOne() {
//        User all=userService.userInfoOne("111");
        User all=userService.userInfoOne("阿大撒");
        System.out.println(all);
    }

    @Test
    void userAuthorInfo() {
        List<User> all=userService.userAuthorInfo();
        System.out.println(all);
    }

    @Test
    void userAuthorInfoO() {
        User all=userService.userAuthorInfoO("阿大撒");
        System.out.println(all);
        System.out.println(all.getAuthority().getAuthorityName());
    }

    //公告表测试
    @Autowired
    AnnouncementService announcementService;

    @Test
    void announcementadd(){
        String time = MyUtil.currentTime();
        String add = announcementService.announcementAdd("45784dc6",time,"最新","测试");
    }

    @Test
    void announcementdle(){
        announcementService.announcementDelete(3);
    }

    @Test
    void announcementqueone(){
        User one = announcementService.userAnnouncementone();
        System.out.println(one);
    }

    //一对多查询是真的复杂
    @Test
    void announcementqueall(){
        List<User> all = announcementService.userAnnouncement();
        for (int i=0;i<all.size();i++) {
            User a = all.get(i);
//            System.out.println(a);
//            System.out.println(a.getAnnouncementall());
            List<Announcement> b = a.getAnnouncementall();

            for (int j=0;j<b.size();j++) {
                Announcement c = b.get(j);
//                System.out.println(b);
                String name = a.getUsername();
                String userid = a.getUserId();
                int a1 = c.getAid();
                String a2 = c.getTime();
                String a3 = c.getTitle();
                String a4 = c.getContent();
                System.out.print("姓名为"+name);
                System.out.print("userid"+userid);
                System.out.print("aid"+a1);
                System.out.print("发布时间"+a2);
                System.out.print("标题"+a3);
                System.out.print("内容"+a4);
                System.out.println();
            }

        }
    }

//    任务表测试usertask

    @Autowired
    UserTaskService userTaskService;

    @Test
    public void userTaskAdd() {
       String time=MyUtil.currentTime();
        String taskid = "user" + MyUtil.getUUID(6);
       String add=userTaskService.userTaskAdd("111","222","001","002",1,time,taskid);

}

    @Test
    public void userTaskDelete() {
        userTaskService.userTaskDelete(6);
    }

    @Test
    public void userTaskUpdate() {
        String time=MyUtil.currentTime();
        String taskid = "user" + MyUtil.getUUID(6);
        userTaskService.userTaskUpdate("b","012","104",0,time,"usereb1487");
    }

    @Test
    public void userTaskQueAll() {
        List<UserTask> a = userTaskService.userTaskQueAll();
        System.out.println(a);
    }

    @Test
    public void userTaskQueAllo() {
        List<UserTask> a = userTaskService.userTaskQueAllo();
        System.out.println(a);
    }

    @Test
    public void MyuserTaskQueAllo() {
        List<Forum> all = userTaskService.myUserTaskForum("1");
        for (int i=0;i<all.size();i++) {
            Forum a = all.get(i);
            System.out.println(a);
        }
    }

    @Test
    public void PublisheruserTaskQueAllo() {
        List<Forum> all = userTaskService.pulisherUserTaskForum("111");
        for (int i=0;i<all.size();i++) {
            Forum a = all.get(i);
            System.out.println(a);
        }
    }

    @Test
    public void userTaskForum() {
        List<Forum> all = userTaskService.userTaskForum();
        PageResult<Forum> pageResult = userTaskService.selForumPage(1, 4);
        for (int i=0;i<all.size();i++) {
            Forum a = all.get(i);
            System.out.println(a);
        }
        System.out.println(all);
    }

    @Test
    public void userTaskForumPage() {
        PageResult<Forum> a = userTaskService.selForumPage(1, 4);
        List<Forum> all = a.getContent();
        for (int i=0;i<all.size();i++) {
            Forum b = all.get(i);
            System.out.println(b);
        }
        System.out.println(all);
    }


    //register表测试
    @Autowired
    RegisterService registerService;

    @Test
    void registeradd(){
        System.out.println(registerService.registerAdd("1213",1231,"asdsad1","asdasd"));
    }
    @Test//user表查询一个
    void registerqueone(){
        System.out.println(registerService.registerQueOne("1122"));
    }
    @Test//user查询全部
    void registerqueall(){
        System.out.println(registerService.registerQueAll());
    }


    //UserInfo表测试
    @Autowired
    UserInfoService userInfoService;

    @Test
    void userinfoadd(){
        String a=userInfoService.userInfoAdd("aasd,asd,fsd,as","awas","12321","s");
        System.out.println(a);
    }

    @Test
    void userinfodel(){
        String a=userInfoService.userInfoDelete("24749299");
        System.out.println(a);
    }

    @Test
    void userinfoupd(){
        String a=userInfoService.userUpdate("@qq.cpom","13123123","小雯","aasd,asd,fsd,as");
        System.out.println(a);
    }

    //Userrelation表测试
    @Autowired
    UserRelationService userRelationService;

    @Test
    void userRelationAdd() {
        String add=userRelationService.userRelationAdd( "2","3","4");
        System.out.println(add);
    }

    @Test
    void userRelationDelete() {
        String del=userRelationService.userRelationDelete("2");
        System.out.println(del);
    }

    @Test
    void userRelationUpdate() {
        String upd=userRelationService.userRelationUpdate( "232","authorityId","5");
        System.out.println(upd);
    }




    //forum表测试
    @Autowired
    ForumService forumService;

    @Test//forum表输入时间
    void forumadd(){
        //获取当前日期
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);

        Date date = new Date();
        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format_date = matter.format(date);
        String taskid = "user_" + MyUtil.getUUID(6);
        forumService.forumAdd("1",format_date,"1","1",1,"1","1","1","1",taskid);
    }

    @Test//forum表查询全部
    void forumall(){
        List<Forum> all = forumService.forumQueAll();
        for (int i=0;i<all.size();i++){
            Forum a=all.get(i);
            System.out.println(a);
        }
    }

    @Test//forum模糊查询
    void forumlike(){
        List<Forum> all = forumService.forumQueLike("1");
        for (int i=0;i<all.size();i++){
            Forum a=all.get(i);
            System.out.println(a);
        }
    }

    @Test//forum删除
    void forumDelete(){
        String delete = forumService.forumDelete(30);
    }

    @Test
    void selectpage(){
        PageRequest a=new PageRequest();
        a.setPageNum(1);
        a.setPageSize(7);
        //页数，每页条数
        PageResult b = forumService.findPage(a);

        int c = b.getPageNum();//当前页码
        int c1 = b.getPageSize();//每页数量
        long c2 = b.getTotalSize();//记录总数
        int c3 = b.getTotalPages();//页码总数
        List<?> c4 = b.getContent();//数据模型
        //List<Forum> c4 = (List<Forum>) b.getContent();//数据模型
        System.out.println("当前页码"+c);
        System.out.println("每页数量"+c1);
        System.out.println("记录总数"+c2);
        System.out.println("页码总数"+c3);
        System.out.println("数据模型");
        for (int i=0;i<c4.size();i++) {
            Object d = c4.get(i);
            System.out.println(d);
        }

        System.out.println("数据模型1"+c4);

        PageResult f = forumService.findPage(a);
        System.out.println("数据"+f);
    }

    @Test
    void selectPage2() throws Exception{
        //页数，每页条数
        PageResult<Forum> pageResult = forumService.selAllfindPage(1, 3);
        //System.out.println(pageResult);
    }




    //数据源自动注册,yml里面的数据库里信息等等
    @Autowired
    DataSource dataSource;

    @Test
    void jdbc() throws SQLException {
        //JDBCController
        //查看默认数据源
        System.out.println(dataSource.getClass());

        //获得数据库连接
        Connection connection=dataSource.getConnection();
        System.out.println(connection);
        //关闭
        connection.close();
    }

    @Test
    void jdbc1(){
        Object[] object=new Object[2];
        object[0]="小明";
        object[1]="123";
        System.out.println(object);
    }


    //简单邮件发送
    @Autowired
    //Could not autowire. No beans of 'JavaMailSender' type found.，原因是邮箱配置参数找不到，
            // 解决：将邮箱配置参数迁移至 resource中 的配置文件中
    JavaMailSenderImpl mailSender;
    @Test
    void easyMail() {
        //一个简单的邮件
        SimpleMailMessage mimeMessage = new SimpleMailMessage();
        //主题
        mimeMessage.setSubject("你好呀");
        //内容
        mimeMessage.setText("今天天气真好");
        //发送给自己
/*        mimeMessage.setTo("2292279906@qq.com");
        //接收邮件
        mimeMessage.setFrom("2292279906@qq.com");*/

        //发送给自己
        mimeMessage.setTo("15119951776@163.com");
        //接收邮件
        mimeMessage.setFrom("15119951776@163.com");

        mailSender.send(mimeMessage);
    }

//Boolean html,String subject,String text
    @Test
    void completxMail() throws MessagingException {
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
/*        helper.setTo("2292279906@qq.com");
        //接收邮件
        helper.setFrom("2292279906@qq.com");*/

        //发送给自己
        helper.setTo("15119951776@163.com");
        //接收邮件
        helper.setFrom("15119951776@163.com");
        //发送
        mailSender.send(mimeMessage);
    }

    @Autowired
    @Qualifier("redisTemplate")
    //RedisConfig里面拿到了方法redisTemplate
    private RedisTemplate redisTemplate;

    @Test
    //实现redis的拿值取值---要开启redis数据库
    public void redistest() throws JsonProcessingException {
        //真实对象是json来传递对象


        //RedisUser redisUser=new RedisUser("小明",3);
        //将对象序列化--没有序列化，会报一个传参错误
        //现在自定义了redisTemplate去序列化
        //String jsonUser=new ObjectMapper().writeValueAsString(redisUser);
        //数据注入-set
        RedisUser jsonUser=new RedisUser("小明",3);
        redisTemplate.opsForValue().set("user",jsonUser);

        //拿到上方jsonUser的值
        Object a = redisTemplate.opsForValue().get("user");
        System.out.println(a);
    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redis_util(){
        redisUtil.set("name","小明");
        System.out.println(redisUtil.get("name"));
    }


    RedisUtil redisUtils = null;
    @Test
    public void RedisHello () {
        String id = null;
        //查询缓存中是否存在
        boolean hasKey = redisUtils.hasKey(id);
        List<User> a = new ArrayList<>();

        if (hasKey) {
            //获取缓存
            Object object = redisUtils.get(id);
            System.out.println("从缓存获取的数据" + object);
            System.out.println(a);
        } else {
            //从数据库中获取信息
            System.out.println("从数据库中获取数据");
            a = userService.UserQueAll();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
//            redisUtils.set(id, a, 10L, TimeUnit.MINUTES);//没有写方法，所以会注入失败
            System.out.println("数据插入缓存" + a);
        }

    }

    public String hello(){
        String id="2";
        //查询缓存中是否存在
        Boolean hasKey = redisUtils.hasKey(id);
        String str;
        if(hasKey){
            //获取缓存
            Object object =  redisUtils.get(id);
            System.out.println("从缓存获取的数据"+ object);
            str = object.toString();
        }else{
            //从数据库中获取信息
            System.out.println("从数据库中获取数据");
            str = "随便存入数据库的一个字段";
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.setEx(id,str,10L,TimeUnit.MINUTES);
            System.out.println("数据插入缓存" + str);
        }
        return str;
    }

/*    @Override
    public List searchProduct() {

        List list = new ArrayList<>();
        if (redisUtil.hasKey("productList")) {
            System.out.println("从redis中获取数据.");
            list = redisList.get("productList", 0, -1);

        }
        else {
            list = productDao.searchProduct();
            List<User> list = userService.UserQueAll();
            System.out.println("从数据库中获取数据.");
            System.out.println("将数据存入redis...");
            redisList.set("productList", list);
            System.out.println("成功存入redis.");
        }
        return list;
    }*/

    @Test
    public void redisConnect() throws Exception {
        //应该是开启redis
        RedisConnection connection=redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        connection.flushAll();
    }

    //文件删除
    @Test
    public void filedelete() throws Exception {
        String file = "D:\\test";
        String filename="411LONY7~2ZPLA_1KG${Q[8.png";
        File dfile = new File(file+File.separator+filename);
        if(dfile.exists()) {
            dfile.delete();
            System.out.println("删除成功");
        }else{
            System.out.println("没有删除成功");
        }
    }


    @Autowired
    TradeItemsMapper tradeItemsMapper;
    @Autowired
    TradeItemsMapper tradeItemsService;

    @Test
    public void tradeitems(){


        List<TradeItems> queall = tradeItemsMapper.queTreadItems();
        System.out.println(queall);
        List<TradeItems> all = tradeItemsService.queTreadItems();
        for (int i=0;i<all.size();i++){
            TradeItems a=all.get(i);
            System.out.println(a);
        }
        System.out.println(all);

    }

    @Test
    public void tradeitemsupdate(){
        String abuyer="1";
        String time=MyUtil.currentTime();
        String aprogress="001";
        tradeItemsService.queimageUpdate(abuyer,time,aprogress,12);

    }


}
