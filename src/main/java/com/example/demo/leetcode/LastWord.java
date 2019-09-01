package com.example.demo.leetcode;

/**
 * @author yongqiang.zhu
 * @date 2019/9/1 18:01
 */
public class LastWord {
	public static void main(String[] args) {
		String s = "Hello World";
		String str = s.trim();
		String[] arr = str.split(" ");
		String lastWord = arr[arr.length-1];
		System.out.println(lastWord.length());
	}
}
