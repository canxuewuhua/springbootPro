package com.example.demo.leetcode.trade;

import lombok.Data;

/**
 * @author yongqiang.zhu
 * @date 2019/9/8 21:05
 */
@Data
public class Trade {

	public String name;
	public Integer time;
	public Integer amount;
	public String city;

	public Trade(String name, Integer time, Integer amount, String city) {
		this.name = name;
		this.time = time;
		this.amount = amount;
		this.city = city;
	}
}
