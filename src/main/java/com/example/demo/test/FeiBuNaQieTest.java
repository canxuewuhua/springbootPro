package com.example.demo.test;

/**
 * @author yongqiang.zhu
 * 斐波那契数列求值0
 * 注：i从2开始，且 i取到最大值n 且先计算出result
 */
public class FeiBuNaQieTest {
	public static void main(String[] args) {
		System.out.println(fibonacci(3));
	}

	public static int fibonacci(int n){
		if (n<=0){
			return 0;
		}
		if (n==1 || n==2){
			return 1;
		}
		int preOne = 1;
		int preTwo = 0;
		int result = 1;
		for (int i = 2;i<= n;i++){
			result = preOne + preTwo;
			preTwo = preOne;
			preOne = result;
		}
		return result;
	}
}
