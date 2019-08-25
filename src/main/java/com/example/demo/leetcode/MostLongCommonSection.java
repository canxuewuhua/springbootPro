package com.example.demo.leetcode;

/**
 * @author yongqiang.zhu
 * @date 2019/8/25 17:35
 * 最长公共前缀
 * 使用官方解题思路：水平扫描法
 * 先将数组的第一个字符串作为prefix，遍历第二个，第三个
 */
public class MostLongCommonSection {

	public static void main(String[] args) {
		String[] strs = {"flower","flow","flight"};

		System.out.println(longestCommonPrefix(strs));
	}
	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) return "";
			}
		return prefix;
	}
}
