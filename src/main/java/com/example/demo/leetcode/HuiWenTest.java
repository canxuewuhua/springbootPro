package com.example.demo.leetcode;

/**
 * @author yongqiang.zhu
 * @date 2019/9/1 17:45
 */
public class HuiWenTest {
	public static void main(String[] args) {
		String m = "A man, a plan, a canal: Panama";
		System.out.println(isPalindrome(m));
	}

	public static boolean isPalindrome(String s) {
		String st = s.toLowerCase();
		String str = st.replace(" ","").replaceAll("[\\pP\\p{Punct}]","");
		int len = str.length();
		for(int i = 0;i< str.length();i++){
			if(str.charAt(i)!= str.charAt(len-1))
				return false;
			len=len-1;
		}
		return true;
	}
}
