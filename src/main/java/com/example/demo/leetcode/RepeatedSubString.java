package com.example.demo.leetcode;

/**
 * @author yongqiang.zhu
 * @date 2019/8/25 18:22
 * 重复的子字符串
 * 如果全是重复的子字符串，那么使用split函数拆分后的数组长度将会是0（重点）
 */
public class RepeatedSubString {
	public static void main(String[] args) {

		String ss="abab";
		System.out.println(repeatedSubstringPattern(ss));
	}

	public static boolean repeatedSubstringPattern(String s) {
		if (s == null) {
			return false;
		}

		// 长度为1，不能拆分出子串，所以直接返回false
		if (s.length() == 1) {
			return false;
		}

		// 长度大于1，可以拆分出子串，判断字符串中是否所有字符都相同，如"aaaaaa","zzz"，一定满足按子串长度为1的拆分，所以直接返回true
		if (s.split(String.valueOf(s.charAt(0))).length == 0) {
			return true;
		}

		// 找长度最长的因子，拆分出子串
		// abab     abcabcabc   9%3
		for (int i = s.length() - 1;i >= 2;i--) {
			if (s.length() % i == 0) {
				String pattern = s.substring(0,i);
				String arr[] = s.split(pattern);
				if (arr.length == 0) {
					return true;
				}
			}
		}

		return false;
	}
}
