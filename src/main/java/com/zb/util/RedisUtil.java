package com.zb.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    //没有有效期的添加
    public void set(String key,Object value){
        ValueOperations<String, Object> vos = redisTemplate.opsForValue();
        vos.set(key,value);
    }

    //带有效期的添加
    public void set(String key,Object value,long time){
        ValueOperations<String, Object> vos = redisTemplate.opsForValue();
        vos.set(key,value,time, TimeUnit.SECONDS);
    }

    public Object get(String key){
        /*ValueOperations<String, Object> vos = redisTemplate.opsForValue();
        vos.get(key);*/
        return redisTemplate.opsForValue().get(key);
    }

    public boolean exists2(String key){
        return this.get(key)!=null? true:false;
    }


    public boolean exists(String key){
        return   redisTemplate.hasKey(key);
    }

}
