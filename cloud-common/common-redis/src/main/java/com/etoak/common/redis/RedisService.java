package com.etoak.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RedisService {

    @Autowired
    StringRedisTemplate template;

    /**
     *
     * @param key 键
     * @param value 值
     * @param time 过期时间
     * @param unit 时间单位
     */
    public void setex(String key, String value, long time, TimeUnit unit) {
        template.opsForValue().set(key, value, time, unit);
    }

    public String get(String key) {
        return template.opsForValue().get(key);
    }
    /**
     * hset
     */
    public void hset(String key, String field, String value) {
        template.opsForHash().put(key, field, value);
    }
    /**
     * hgetall
     */
    public Map<String ,String> hgetall(String key) {
        return template.opsForHash().entries(key)
                .entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(),
                        entry -> entry.getValue().toString()));

    }
    /**
     * hdel
     */
    public void hdel(String key, String ...fields) {
        template.opsForHash().delete(key, fields);
    }

}
