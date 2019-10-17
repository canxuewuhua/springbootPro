package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class SerialNumberUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtilService redisUtilService;

    private static SerialNumberUtil serialNumberUtil;

    public static final String REDIS_ID_MIN = "100";
    public static final int REDIS_ID_MAX = 999;

    @PostConstruct
    public void init() {
        serialNumberUtil = this;
        serialNumberUtil.stringRedisTemplate = this.stringRedisTemplate;
        serialNumberUtil.redisUtilService = this.redisUtilService;
    }

    /**
     * 针对第三方请求流水ID
     */
    public static String getRequestId() {
        String key = serialNumberUtil.redisUtilService.getRedisKey("generate:id:request:");
        long redisValue = incr(key);
        StringBuffer sbRequest = new StringBuffer();
        long requestTemp = System.currentTimeMillis();
        sbRequest.append(requestTemp);
        sbRequest.append(String.valueOf(redisValue));
        return sbRequest.toString();
    }

    /**
     * 针对每个表对应的主键ID，现在是通过UUID生成的
     */
    public static String getPrimaryId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 针对每个表对应的code，现在是通过UUID生成的
     */
    public static String getCode() {
        return UUID.randomUUID().toString();
    }


    private static long incr(String key) {
        String idKey = serialNumberUtil.stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(idKey)) {
            serialNumberUtil.stringRedisTemplate.opsForValue().set(key, REDIS_ID_MIN);
        } else if (Integer.valueOf(idKey).intValue() >= REDIS_ID_MAX) {
            serialNumberUtil.stringRedisTemplate.opsForValue().set(key, REDIS_ID_MIN);
        }
        return serialNumberUtil.stringRedisTemplate.opsForValue().increment(key, 1L);
    }

}
