package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.pool.max-active}")
    private Integer REDIS_POOL_MAX_ACTIVE;

    @Value("${spring.redis.pool.max-wait}")
    private Integer REDIS_POOL_MAX_WAIT;

    @Value("${spring.redis.pool.max-idle}")
    private Integer REDIS_POOL_MAX_IDLE;

    @Value("${spring.redis.pool.min-idle}")
    private Integer REDIS_POOL_MIN_IDLE;

    @Value("${spring.redis.timeout}")
    private Integer REDIS_POOL_TIMEOUT;

//    @Value("${keyCenter.config.path}")
//    private String KEY_CENTER_PATH;

    @Value("${account.redis.connect}")
    private String NEW_CORE_REDIS_CONNECT;

    @Value("${spring.redis.pool.testOnBorrow}")
    private boolean TEST_ON_BORROW;

    @Value("${spring.redis.pool.testWhileIdle}")
    private boolean TEST_WHILE_IDLE;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
//        KeyCenterConstants.getKeyCenterProperties(KEY_CENTER_PATH);
//        String response = KeyCenterUtil.getKeyCenterValue(NEW_CORE_REDIS_CONNECT);
//        if (StringUtils.isBlank(response)) {
//            log.error("redis初始化失败，从keycenter获取redis配置失败,配置的key为：{}",NEW_CORE_REDIS_CONNECT);
//            return null;
//        }
//        KeyCenterRedisConfigDTO keyCenterRedisConfigDTO = JSONObject.parseObject(response, KeyCenterRedisConfigDTO.class);

        factory.setHostName("192.168.199.239");
        factory.setDatabase(0);
        factory.setPassword("123456");
        factory.setPort(6379);
        factory.setTimeout(REDIS_POOL_TIMEOUT);
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(REDIS_POOL_MAX_ACTIVE);
        config.setMaxIdle(REDIS_POOL_MAX_IDLE);
        config.setMinIdle(REDIS_POOL_MIN_IDLE);
        config.setMaxWaitMillis(REDIS_POOL_MAX_WAIT);
        config.setTestWhileIdle(TEST_WHILE_IDLE);
        config.setTestOnBorrow(TEST_ON_BORROW);
        factory.setPoolConfig(config);

        log.info("redis初始化成功");
        return factory;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(){
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return stringRedisTemplate;
    }
}

