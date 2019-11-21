package com.example.demo.exercise.amount;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 * @date 2019/5/26 19:37
 */
public class AmountTest {

	public static final int SYSTEM_HALF_UP = BigDecimal.ROUND_HALF_UP;
	public static void main(String[] args) {

//		BigDecimal appendAmount = new BigDecimal("500000");
//		BigDecimal totalAmount = new BigDecimal("1000000");
//		BigDecimal monthLoanAmount = new BigDecimal("199");
//		BigDecimal monthReturnCapital = new BigDecimal("4");
//		BigDecimal freezeQuota = new BigDecimal("20");
//		BigDecimal availableAmount = new BigDecimal("999405");
//		BigDecimal monthQuota = availableAmount.add(monthLoanAmount).add(freezeQuota).subtract(monthReturnCapital).subtract(appendAmount);
//		System.out.println(monthQuota);
		preserveDecimal();
	}

	/**
	 *  四舍五入保留三位
	 */
	public static void preserveDecimal(){
		// 四舍五入保留三位
//		BigDecimal mistakeNum = new BigDecimal("200000");
//		BigDecimal mistakeRate = mistakeNum.divide(new BigDecimal("6"), 3, SYSTEM_HALF_UP);

		BigDecimal mistakeRate = new BigDecimal("0.122228");
		BigDecimal m2 = mistakeRate.multiply(new BigDecimal("100"));
		System.out.println(m2);
		BigDecimal m3= m2.setScale(3, SYSTEM_HALF_UP);
		System.out.println(m3);
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
