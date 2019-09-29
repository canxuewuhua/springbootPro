package com.example.demo.exercise.delete_if_else.strategy;

import com.example.demo.exercise.delete_if_else.OrderDTO;

/**
 * @author yongqiang.zhu
 * 抽象处理器 AbstractHandler
 */
public abstract class AbstractHandler {
	abstract public String handle(OrderDTO orderDTO);
}
