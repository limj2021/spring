package com.example.spring_client.controller.redis;

import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.Task;
import com.example.spring_client.entity.Test;
import com.example.spring_client.entity.UserTask;
import com.example.spring_client.service.ForumService;
import com.example.spring_client.service.RedisService;
import com.example.spring_client.service.TestService;
import com.example.spring_client.util.MyUtil;
import com.example.spring_client.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


public class RedisController {

    public static final Logger log = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    TestService testService;

    @Autowired
    ForumService forumService;

    @Autowired
    private RedisUtil redisUtils;
    @RequestMapping(value = "/hello/{id}")
    public String hello(@PathVariable(value = "id") String id){
        //查询缓存中是否存在
        boolean hasKey = redisUtils.hasKey(id);
        List<Test> a=new ArrayList<>();

        if(hasKey){
            //获取缓存
            Object object =  redisUtils.get(id);
            log.info("从缓存获取的数据"+ object);
            System.out.println(a);
        }else{
            //从数据库中获取信息
            log.info("从数据库中获取数据");
            a = testService.queAll();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
//            redisUtils.set(id,a,10L, TimeUnit.MINUTES);
            log.info("数据插入缓存" + a);
        }
        return "index";
    }





    @RequestMapping("/setUser")
    public String setUser(){
        UserTask a=new UserTask("1","","","","",1,"","",1);
        List<UserTask> b=new ArrayList();
        b.add(a);

        String format_date=MyUtil.currentTime();
        Forum user=new Forum(11,"quellan",format_date,"朱","asda",4000,
                "dfsdf","asdsad","asdsad","1232131312","",new UserTask(), b,
                new Task());
        redisService.setUser(user);
        return "添加成功";

    }

    @Autowired
    private RedisService redisService;
    @RequestMapping("/getUser")
    public Forum getUser(){
        String name="quellan";
        //查询缓存中是否存在
        boolean hasKey = redisUtils.hasKey(name);
        if(hasKey){
            return redisService.getUser(name);
        }else{
            //从数据库中获取信息
            log.info("从数据库中获取数据");
            List<Forum> a = forumService.forumQueAll();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
//            redisUtils.set(name,a,10L, TimeUnit.MINUTES);
            log.info("数据插入缓存" + a);
            return (Forum) a;
//            如果数据库也没有，那就没有了
        }

    }

}
