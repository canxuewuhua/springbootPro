package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yongqiang.zhu
 * @date 2019/4/21 23:16
 */
@Service
@Slf4j
public class RedisTestService {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 查询运行参数set redis缓存
	 * @return
	 */
	public String getRunParamValue(){
		String redisKey = "redis_20190421_value";
		// 先查询redis缓存如果没有，则从数据库中查找然后放入redis中
		String paramValue = stringRedisTemplate.opsForValue().get(redisKey);
		if (StringUtils.isEmpty(paramValue)){
			// 缓存中没有查询到值，查询数据库然后放入redis中
			paramValue = "you are my sunshine";
			if (!StringUtils.isEmpty(paramValue)){
				stringRedisTemplate.opsForValue().set(redisKey, paramValue);
			}

			// 删除缓存，此方法不用再此处，不会设置了又去删除，只为测试
			//stringRedisTemplate.delete(redisKey);
		}
		return paramValue;
	}
}
