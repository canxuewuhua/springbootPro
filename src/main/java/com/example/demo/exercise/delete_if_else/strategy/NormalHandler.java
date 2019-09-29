package com.example.demo.exercise.delete_if_else.strategy;

import com.example.demo.exercise.delete_if_else.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * @author yongqiang.zhu
 * @date 2019/9/29 22:54
 */
@Component
@HandlerType("1")
public class NormalHandler extends AbstractHandler {
	@Override
	public String handle(OrderDTO orderDTO) {
		return "处理普通订单";
	}
}
