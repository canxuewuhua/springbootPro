package com.example.demo.test.search;

/**
 * @author yongqiang.zhu
 * 二分查找算法
 * 左半部  右半部 进行查找
 */
public class TwoPartSearch {
	public static void main(String[] args) {
		int Array[] = {1,3,5,8,12,18};
		System.out.println(binSearch(Array,18));
	}

	public static int binSearch(int Array[], int key){
		int low = 0,high = Array.length - 1;
		int mid;
		while (low<=high){
			mid = (low + high)/2;
			if (key == Array[mid]){
				return mid;
			}else if(key > Array[mid]){
				low = mid + 1;
			}else{
				high = mid -1;
			}
		}
		return -1;
	}

}
