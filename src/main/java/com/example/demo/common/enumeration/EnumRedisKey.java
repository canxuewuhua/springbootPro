package com.example.demo.common.enumeration;


import com.example.demo.config.Configuration;

public class EnumRedisKey {
    private static String redisStaticPrefix;

    private final static Configuration CONF = new Configuration("application.properties");

    static {
        redisStaticPrefix = CONF.get("account.redis.prefix");
    }

    /**
     * 逾期跑批锁
     */
    public static final String OVERDUE_BATCH_IS_LOCK = redisStaticPrefix + "overdue:batch:";
}
