package com.example.demo.exercise.amount;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 * @date 2019/5/26 19:37
 */
public class AmountTest {

	public static final int SYSTEM_HALF_UP = BigDecimal.ROUND_HALF_UP;
	public static void main(String[] args) {

	}

	/**
	 *  四舍五入保留三位
	 */
	private void preserveDecimal(){
		// 四舍五入保留三位
		BigDecimal mistakeNum = new BigDecimal("200000");
		BigDecimal mistakeRate = mistakeNum.divide(new BigDecimal("6"), 3, SYSTEM_HALF_UP);
		System.out.println(mistakeRate);
	}

	/**
	 * 将字符串翻转，需要将字符串转化为StringBuffer后
	 */
	private void reverseStr(){
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
