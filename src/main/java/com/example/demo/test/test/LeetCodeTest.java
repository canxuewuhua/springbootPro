package com.example.demo.test.test;

import java.util.*;

/**
 * @author yongqiang.zhu
 * @date 2019/10/20 12:29
 */
public class LeetCodeTest {

	public static void main(String[] args) {
		int[] nums1 = {1,2,2,1};
		int[] nums2 = {2,2};
		int[] m = intersection2(nums1,nums2);
		for (int i = 0; i < m.length; i++) {
			System.out.println(m[i]);
		}
	}

	/**
	 * 找交集衍生知识点
	 * 第一行输出 1 2 3 4 5
	 * 第二行输出 1 2 3 4 5 0 0 0 0 0
	 */
	public void testArrayCopy(){
		int[] arr1 = {1, 2, 3, 4, 5};
		int[] arr2 = Arrays.copyOf(arr1, 5);
		int[] arr3 = Arrays.copyOf(arr1, 10);
		for(int i = 0; i < arr2.length; i++)
			System.out.print(arr2[i] + " ");
		System.out.println();
		for(int i = 0; i < arr3.length; i++){
			System.out.print(arr3[i] + " ");
		}
	}

	/**
	 *  领扣 找两个数组的交集
	 */
	public static int[] intersection2(int[] nums1, int[] nums2) {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		for (int i = 0; i < nums1.length; i++) {
			list1.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			list2.add(nums2[i]);
		}
		if (list1.size()>=list2.size()){
			int[] output = new int[list1.size()];
			int idx = 0;
			for (Integer s : list2){
				if (list1.contains(s)){
					output[idx++] = s;
				}
			}
			return Arrays.copyOf(output, idx);
		}else{
			int[] output = new int[list2.size()];
			int idx = 0;
			for (Integer s : list1){
				if (list2.contains(s)){
					output[idx++] = s;
				}
			}
			return Arrays.copyOf(output, idx);
		}
	}

	/**
	 *  领扣 找两个数组的交集
	 */
	public static int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		for (int i = 0; i < nums1.length; i++) {
			set1.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			set2.add(nums2[i]);
		}

		if (set1.size()>=set2.size()){
			int[] output = new int[set1.size()];
			int idx = 0;
			for (Integer s : set2){
				if (set1.contains(s)){
					output[idx++] = s;
				}
			}
			return Arrays.copyOf(output, idx);
		}else{
			int[] output = new int[set2.size()];
			int idx = 0;
			for (Integer s : set1){
				if (set2.contains(s)){
					output[idx++] = s;
				}
			}
			return Arrays.copyOf(output, idx);
		}
	}

	/**
	 *  领扣 买股票最佳时机
	 */
	public static int copy(int[] prices){
		int minprice = Integer.MAX_VALUE;
		int maxprofit = 0;
		for(int i=0;i<prices.length;i++){
			if(prices[i] < minprice){
				minprice = prices[i];
			}
			else if(prices[i] - minprice > maxprofit){
				maxprofit = prices[i] - minprice;
			}
		}
		return maxprofit;
	}
}
