package com.example.demo.test.test_demo;

import java.util.Date;
import java.util.Random;

/**
 * @author yongqiang.zhu
 * @date 2020/5/18 23:53
 */
public class DateSplitTest {
	public static void main(String[] args) {

		// 获取年份 2020
		System.out.println(String.format("%tY", new Date()));
		// 获取月份 05
		System.out.println(String.format("%tm", new Date()));
		// 获取年月份横格字符串 2020-05-19
		System.out.println(String.format("%tF", new Date()));
		// 时分秒字符串 00:05:00
		System.out.println(String.format("%tT", new Date()));
		// 00:05
		System.out.println(String.format("%tR", new Date()));
		// 05/19/20
		System.out.println(String.format("%tD", new Date()));
		// 星期二 五月 19 00:05:00 CST 2020
		System.out.println(String.format("%tc", new Date()));
		// 获取日 19
		System.out.println(String.format("%td", new Date()));

		// 第一种：生成6位短信验证码
		String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
		System.out.println(verifyCode);

		// 第二种：生成6位短信验证码
		int randomNum = new Random().nextInt(1000000);
		String randomCode = String.format("%06d", randomNum);
		System.out.println(randomCode);

		//第三种：生成6位随机数（不会是5位或者7位，仅只有6位）
		System.out.println((int)((Math.random()*9+1)*100000));
		// 同理生成5位随机数
		System.out.println((int)((Math.random()*9+1)*10000));
		// 同理生成4位随机数
		System.out.println((int)((Math.random()*9+1)*1000));

	}
}
