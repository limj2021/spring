package com.example.demo.UserInfoService;

import com.example.demo.entiy.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    public boolean setUser(User user){
        ValueOperations ops=redisTemplate.opsForValue();
        ops.set(user.getNickname(),user);
        log.info("{}",user.toString());
        return true;
    }
    public User getUser(String name){
        ValueOperations ops=redisTemplate.opsForValue();
        return (User) ops.get(name);
    }
}
