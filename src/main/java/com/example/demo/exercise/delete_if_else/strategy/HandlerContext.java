package com.example.demo.exercise.delete_if_else.strategy;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author yongqiang.zhu
 * @date 2019/9/29 23:44
 */
@SuppressWarnings("unchecked")
public class HandlerContext {

	private Map<String, Class> handlerMap;

	public HandlerContext(Map<String, Class> handlerMap) {
		this.handlerMap = handlerMap;
	}

	public AbstractHandler getInstance(String type) {
		Class clazz = handlerMap.get(type);
		if (clazz == null) {
			throw new IllegalArgumentException("not found handler for type: " + type);
		}
		return (AbstractHandler) BeanTool.getBean(clazz);
	}

}
