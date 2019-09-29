package com.example.demo.exercise.delete_if_else;

import org.springframework.stereotype.Service;

/**
 * @author yongqiang.zhu
 * @date 2019/9/29 22:37
 */
@Service
public class OrderServiceImpl implements IOrderService {
	@Override
	public String handle(OrderDTO orderDTO) {
		String type = orderDTO.getType();
		if ("1".equals(type)){
			return "处理普通订单";
		}else if ("2".equals(type)){
			return "处理团购订单";
		}else if ("3".equals(type)){
			return "处理促销订单";
		}
		return null;
	}
}
