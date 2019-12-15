package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Service
@Slf4j
public class RedisUtilService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static String redisStaticPrefix = "pro_redis";

    /**
     * REDIS锁时间，单位秒
     */
    public static  final  int REDIS_LOCK_SECONDS = 30 * 60;

    //=============================common============================
    public String getRedisKey(String key){
        return redisStaticPrefix + key;
    }
    /**
     * 指定缓存失效时间
     *
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        if (time <= 0) {
            log.error("指定缓存失效时间参数不正确，key={}, time={}", key, time);
            return false;
        }
        try {
            return redisTemplate.expire(key, time, timeUnit);
        } catch (Exception e) {
            log.error("指定缓存失效时间异常，e={}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("判断key是否存在异常,key={},e={}", key, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入，没有设置过期时间
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("缓存失败，key={},value={}, e={}", key, value, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        if (time <= 0) {
            log.error("放入缓存失效时间不正确,key={},value={}, time={}", key, value, time);
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value, time, timeUnit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 只有当key不存在时才能缓存成功并返回true，使用SETNX实现分布式锁
     *
     * @param key      键
     * @param value    值
     * @param time     失效时长
     * @param timeUnit 失效时间单位
     * @return
     */
    public boolean setIfAbsent(String key, Object value, long time, TimeUnit timeUnit) {
        boolean result = false;
        try {
            result = redisTemplate.opsForValue().setIfAbsent(key, value);
            redisTemplate.expire(key, time, timeUnit);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * 递增
     *
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            log.error("递增因子必须大于0，key={}, delta={}", key, delta);
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            log.error("递减因子必须大于0，key={}, delta={}", key, delta);
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("缓存HashMap异常，key={}, map={}, e={}", key, map, e.getMessage(), e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time, TimeUnit timeUnit) {
        if (time <= 0) {
            log.error("缓存HashMap过期时间不正确，key={}, time={}", key, time);
            return false;
        }
        try {
            redisTemplate.opsForHash().putAll(key, map);
            expire(key, time, timeUnit);
            return true;
        } catch (Exception e) {
            log.error("缓存HashMap异常，key={},e={}", key, e.getMessage(), e);
            return false;
        }
    }


    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("获取set缓存异常，key={}, e={}", key, e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("判断是否缓存中是否存在set异常，key={}, e={}", key, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, long time, TimeUnit timeUnit, Object... values) {
        if (time <= 0) {
            log.error("缓存set过期时间不正确，key={},time={}", key, time);
        }
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            expire(key, time, timeUnit);
            return count;
        } catch (Exception e) {
            log.error("缓存set异常，key={}, e={}", key, e.getMessage(), e);
            return 0;
        }
    }


    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error("移除set缓存异常，key={}, e={}", key, e.getMessage(), e);
            return 0;
        }
    }
    //===============================list=================================


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time, TimeUnit timeUnit) {
        if (time <= 0) {
            log.error("缓存list过期时间不正确，key={}, time={}", key, time);
        }
        try {
            redisTemplate.opsForList().rightPush(key, value);
            expire(key, time, timeUnit);
            return true;
        } catch (Exception e) {
            log.error("缓存list异常，key={}, e={}", key, e.getMessage(), e);
            return false;
        }
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time, TimeUnit timeUnit) {
        if (time <= 0) {
            log.error("缓存list过期时间不正确，key={}, time={}", key, time);
            return false;
        }
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            expire(key, time, timeUnit);
            return true;
        } catch (Exception e) {
            log.error("缓存list异常，key={}, e={}", key, e.getMessage(), e);
            return false;
        }
    }

    /**
     * redis自增并且格式化，前面补零
     * @param key
     * @param length
     * @return
     */
    public String incrAndFormat(String key, int length){

        long incr = redisTemplate.opsForValue().increment(key, 1);
        return String.format("%0" + length + "d", incr);
    }

    /**
     * 只有key不存在时才缓存成功，否则缓存失败
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setNX(final String key, final String value,final long time,final TimeUnit timeUnit) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key,value);
        if (success) {
            return expire(key, time,timeUnit);
        }else{
            return  false;
        }
    }

    public Object lPop(final String key,long time) {
        return redisTemplate.opsForList().leftPop(key, time, TimeUnit.SECONDS);
    }

    public Long rPush(final String key, final Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 加锁
     * @param key
     * @return
     */
    public boolean getLock(String key) {
        boolean result = setNX(key, "1", REDIS_LOCK_SECONDS,TimeUnit.SECONDS);
        //log.info("{}请求锁结果{}", key, result);
        return result;
    }

    /**
     * 释放锁
     *
     * @param key
     */
    public void releaseLock(String key) {
        del(key);
        //log.info("{}释放锁成功", key);
    }
}
