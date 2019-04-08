package com.example.demo.test.reflex;

import java.lang.reflect.Field;

/**
 * 测试类
 * Java反射就是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；并且能改变它的属性。
 */
public class ReflectionTest {
	public static void main(String[] args) throws Exception {
		print();
	}

	public static void print() throws Exception {
		Class c1 = Class.forName("com.example.demo.test.reflex.Son");
		//获取父类私有属性值
		System.out.println(getFieldValue(c1.newInstance(),"privateField"));
	}

	public static Field getDeclaredField(Object obj, String fieldName) throws NoSuchFieldException {
		Field field = null;
		Class c = obj.getClass();
		for(; c != Object.class ; c = c.getSuperclass()){
			try {
				field = c.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			}catch (Exception e){
				//这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				//如果这里的异常打印或者往外抛，则就不会执行c = c.getSuperclass(),最后就不会进入到父类中了
			}
		}
		return null;
	}

	public static Object getFieldValue(Object object,String fieldName) throws Exception{
		Field field = getDeclaredField(object,fieldName);
		return field.get(object);
	}
}
