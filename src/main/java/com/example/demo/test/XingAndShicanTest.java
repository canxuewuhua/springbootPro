package com.example.demo.test;

/**
 * @author yongqiang.zhu
 * @date 2020/2/21 10:36
 * 形参：用来接收调用该方法时传递的参数。只有在被调用的时候才分配内存空间，一旦调用结束，就释放内存空间。因此仅仅在方法内有效。
 * 实参：传递给被调用方法的值，预先创建并赋予确定值。
 * 传值调用：传值调用中传递的参数为基本数据类型，参数视为形参。
 *
 * 这是一个很好的例子，分析值传递和引用传递的区别
 */
public class XingAndShicanTest {

	public static void main(String[] args) {
		/**
		 * 第一个例子 swap
		 * 这里的m与n为实参 值传递 实参不会因形参的改变而改变
		 */
		int m = 5;
		int n = 7;
		System.out.println("m:"+m+";n="+n);
		swap(5,7);
		System.out.println("m:"+m+";n="+n);

		/**
		 * *{以上结果进行分析}
		 * 因为基本数据类型是属于传值调用，当我们要实现交换时，方法内可以完成交换，只是实参进行了交换，方法结束，栈空间中就会销毁swap()方法内的局部变量，
		 * 所以当调用swap()时，最终值还是实参的初始化值，并没有被修改。值改变作用到了形参，而不会到实参
		 */
		System.out.println("------------------------------------");


		/**
		 * 参数传递：
		 * 形参：方法声明时，方法小括号内的参数
		 * 实参: 调用方法时，实际传入的参数值
		 *
		 * java中传递值得机制:
		 * 形参是基本数据类型的: 将实参的值传递给实参的基本数据类型的变量
		 * 形参是引用数据类型的: 将实参的引用类型的值(即在堆空间中生成的首地址的值)传递给形参的引用类型的变量
		 *
		 */

		DT dt = new DT();
		System.out.println("dt.i:"+dt.i+" dt.j:"+dt.j);

		/**
		 * 调用
		 */
		swap(dt);
		System.out.println("dt.i:"+dt.i+" dt.j:"+dt.j);


	}


	/**
	 * 1、这里边的a,b就是形参，temp是一个局部变量，方法结束，在栈空间中就会被销毁
	 * @param a
	 * @param b
	 */
	public static void swap(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
		System.out.println("a:" + a + " b:" + b);
	}


	/**
	 * 传引用调用：传引用调用中，如果传递的参数是引用数据类型，参数视为实参。
	 * 在调用的过程中，将实参的地址传递给了形参，形参上的改变都发生在实参上。
	 */
	public static void swap(DT dt) {
		int temp = dt.i;
		dt.i = dt.j;
		dt.j = temp;
		System.out.println("i:" + dt.i + " j:" +dt.j);
	}


}
class DT{
	int i = 10;
	int j = 8;
}
