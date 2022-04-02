package com.example.spring_client.service;

import com.example.spring_client.entity.Forum;
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
    public boolean setUser(Forum user){
        ValueOperations ops=redisTemplate.opsForValue();
        ops.set(user.getId(),user);
        log.info("{}",user.toString());
        return true;
    }
    public Forum getUser(String name){
        ValueOperations ops=redisTemplate.opsForValue();
        return (Forum) ops.get(name);
    }
}
