package com.example.demo.exercise.delete_if_else.strategy;

import com.example.demo.exercise.delete_if_else.IOrderService;
import com.example.demo.exercise.delete_if_else.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongqiang.zhu
 * @date 2019/9/29 22:42
 */
@Service
public class OrderServiceV2Impl implements IOrderService {

	@Autowired
	private HandlerContext handlerContext;

	@Override
	public String handle(OrderDTO orderDTO) {
		AbstractHandler handler = handlerContext.getInstance(orderDTO.getType());
		return handler.handle(orderDTO);
	}
}
