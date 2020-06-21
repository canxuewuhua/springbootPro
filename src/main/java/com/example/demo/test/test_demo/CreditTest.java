package com.example.demo.test.test_demo;

import lombok.Data;

/**
 * @author yongqiang.zhu
 * @date 2020/6/12 22:39
 */
public class CreditTest {
	public static void main(String[] args) {
		CreditDTO creditDTO = new CreditDTO();
		creditDTO.setCreditStatus("YES");
		if (!(creditDTO == null || "NO".equals(creditDTO.getCreditStatus()))){
			System.out.println("输入"+creditDTO.getCreditStatus());
		}

		System.out.println("输出最终结果");
	}
}
@Data
class CreditDTO{
	private String creditStatus;
	private String creditTime;
}
