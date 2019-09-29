package com.example.demo.exercise.delete_if_else;

/**
 * @author yongqiang.zhu
 * @date 2019/9/29 22:35
 */
public interface IOrderService {

	/**
	 * 根据订单的不同类型作出不同的处理
	 */
	String handle(OrderDTO orderDTO);
}
