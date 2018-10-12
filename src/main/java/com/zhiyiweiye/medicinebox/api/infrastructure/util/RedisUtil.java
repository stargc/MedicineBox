package com.zhiyiweiye.medicinebox.api.infrastructure.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setValueByTimer(String key, String value, long timeout, TimeUnit unit) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, timeout, unit);
    }

    public void setValueByTimer(String key, String value, long timeout) {
        this.setValueByTimer(key, value, timeout, TimeUnit.SECONDS);
    }

    public String getValue(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key).toString();
    }

    public void setValue(String key, String value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public int countByKeys(String key) {
        Set<String> keys = redisTemplate.keys(key + "*");
        return keys.size();
    }

    public void delByKeys(String key){
        Set<String> keys = redisTemplate.keys(key + "*");
        redisTemplate.delete(keys);
    }
}
