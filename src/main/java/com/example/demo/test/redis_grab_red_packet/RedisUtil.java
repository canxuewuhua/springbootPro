package com.example.demo.test.redis_grab_red_packet;

/**
 * @author yongqiang.zhu
 * @date 2019/10/13 0:08
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>Redis工具类</P>
 *
 * @author hanchao
 */
public class RedisUtil {
	/**
	 * Redis连接池
	 */
	private static JedisPool jedisPool;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1024);
		config.setMaxIdle(200);
		config.setMaxWaitMillis(10000);
		config.setTestOnBorrow(true);
		jedisPool = new JedisPool(config, "192.168.199.239", 6379, 10000, "123456", 0);
	}

	/**
	 * 获取Jedis实例
	 */
	public synchronized static Jedis getConnection() {
		try {
			if (jedisPool != null) {
				return jedisPool.getResource();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 */
	public static void close(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
}

