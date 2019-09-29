package com.example.demo.exercise.delete_if_else;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 * @date 2019/9/29 22:30
 */
@Data
public class OrderDTO {

	private String code;
	private BigDecimal price;
	/**
	 *  订单类型
	 *  1、普通订单
	 *  2、团购订单
	 *  3、促销订单
	 */
	private String type;
}
