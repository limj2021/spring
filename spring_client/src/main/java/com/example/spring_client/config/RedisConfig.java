package com.example.spring_client.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/*Redis 是一个高性能的key-value数据库-远程字典服务
应该不算数据库，算缓存
是非关系型数据库-严格来说是一种数据结构化存储方法的集合*/

//固定模板
@Configuration
public class RedisConfig {

/*  编辑自己的RedisTemplate序列化---因为redis需要自己字段序列化，所以配置redis文件
    假如不使用自定义redisTemplate---在cmd查看数据的时候，会产生乱码，或者不知名错误*/

    @Bean("redisTemplate")
    //抑制所有类型的警告
    @SuppressWarnings("all")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {

        //建立一个对象--<String,Object>为了开发方便
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //建立一个连接
        template.setConnectionFactory(redisConnectionFactory);

        //下面是序列化的手段
        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //用json对象序列化--对象形式--把所有对象变成一个json对象
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //配置具体的序列化方式


        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hash的value的序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }





}
