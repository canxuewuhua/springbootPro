package com.example.demo.test.order;

/**
 * @author yongqiang.zhu
 * @date 2019/9/21 22:19
 */
public class MaoPaoSort {
	public static void main(String[] args) {
		int [] arr = {4,10,21,41,5,3,12,9};
		maopao(arr);
	}
	public static void maopao(int[] arr){
		for (int m=0;m<arr.length-1;m++){
			for (int n=0;n<arr.length-m-1;n++){
				int temp;
				if (arr[n] > arr[n+1]){
					temp = arr[n];
					arr[n] = arr[n+1];
					arr[n+1] = temp;
				}
			}
		}
		for (int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
}
