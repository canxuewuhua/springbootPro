package com.example.demo.controller;

import com.example.demo.service.annotation.MyFirstAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongqiang.zhu
 * @date 2019/4/8 0:34
 */
@Slf4j
@RestController
@RequestMapping("/boot")
public class SpringBootAnnotationController {

	@MyFirstAnnotation("吃饭")
	@RequestMapping(name = "第一个自定义注解", path = "/say", method = RequestMethod.GET)
	public String sayHello() {
		System.out.println("可以吃饭了");
		return "hello spring boot";
	}
}
