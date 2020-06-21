package com.example.demo.test.test_demo;

import java.util.*;

/**
 * @author yongqiang.zhu
 * @date 2020/5/19 22:41
 */
public class ProtectTest {
	public static void main(String[] args) {
		String[] words = {"gin","zen","gig","msg"};
		System.out.println(uniqueMorseRepresentations(words));
	}

	/**
	 * 唯一摩尔斯密码词
	 * 给了我们一个单词数组，问我们表示这些单词的摩斯码有多少种。因为某些单词的摩斯码表示是相同的，比如gin和zen就是相同的。
	 * 最简单直接的方法就是我们求出每一个单词的摩斯码，然后将其放入一个HashSet中，利用其去重复的特性，从而实现题目的要求，最终HashSet中元素的个数即为所求
	 * @param words
	 * @return
	 */
	public static int uniqueMorseRepresentations(String[] words){
		String[] MORSE = new String[]{".-","-...","-.-.","-..",".","..-.","--.", "....","..",".---","-.-",".-..","--","-.", "---",".--.","--.-",".-.","...","-","..-", "...-",
				".--","-..-","-.--","--.."};
		Set<String> seen = new HashSet();
		for(String word: words) {
			StringBuilder code = new StringBuilder();
			for (char c: word.toCharArray()){
				code.append(MORSE[c - 'a']);
			}
			seen.add(code.toString());
		}
		return seen.size();
	}

}
