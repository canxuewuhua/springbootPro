package com.example.demo.exercise.amount;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 * @date 2019/5/26 19:37
 */
public class AmountTest {
	public static void main(String[] args) {
		int m = 101;
		String s = String.valueOf(m);
		StringBuffer sb = new StringBuffer(s);
		sb.reverse();
		if (s.equals(sb.toString())){
			System.out.println("haha");
		}else{
			System.out.println("NO");
		}

	}
}
