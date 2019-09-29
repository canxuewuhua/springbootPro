package com.example.demo.exercise.delete_if_else.controller;

import com.example.demo.exercise.delete_if_else.OrderDTO;
import com.example.demo.exercise.delete_if_else.strategy.OrderServiceV2Impl;
import com.example.demo.util.SerialNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 * @date 2019/9/30 0:03
 */

@RestController
@RequestMapping("/order")
public class StrategyController {

	@Autowired
	private OrderServiceV2Impl orderServiceV2;
	@RequestMapping("/promote")
	public String sayOrder()  {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setCode("001");
		orderDTO.setPrice(new BigDecimal("5000.0"));
		orderDTO.setType("3");
		return orderServiceV2.handle(orderDTO);
	}
}
