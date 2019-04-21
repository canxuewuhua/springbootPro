package com.example.demo.controller;

import com.example.demo.service.RedisTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yongqiang.zhu
 * @date 2019/4/21 23:24
 */
@Slf4j
@RestController
@RequestMapping("/reids")
public class RedisController {


	@Autowired
	private RedisTestService redisTestService;
	@RequestMapping("/test")
	public String showRedis(){
		String runParam = redisTestService.getRunParamValue();
		return runParam;
	}
}
