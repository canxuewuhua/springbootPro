package com.example.demo.leetcode;

/**
 * @author yongqiang.zhu
 * @date 2019/9/8 17:15
 */
public class IntegerReverse {
	public static void main(String[] args) {
		System.out.println(countPrimes(10));
	}

	public static boolean isPrime(int n){
		if (n<=3) return n >1;
		for(int i =2; i<=Math.sqrt(n); i++){
			if (n % i == 0) return false;
		}
		return true;
	}
	public static boolean isPrimeNormal(int num) {
		for(int i=2; i<num; i++) {
			if(num%i == 0) {
				return false;
			}
		}

		return true;
	}
	public static int countPrimes(int n) {
		if(n==0 || n==1 || n==2) return 0;
		if(n==3) return 1;
		if(n==4) return 2;
		int count = 2;
		for(int j=4;j<n;j++){
			if(isPrimeNormal(j)){
				// 如果j是质数
				count++;
			}
		}
		return count;
	}
}
