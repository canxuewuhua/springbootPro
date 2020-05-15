package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author yongqiang.zhu
 * @date 2020/5/15 22:56
 */
@Slf4j
public class RandomUtil {

	/**
	 * 该方法用customerCode作为种子生成随机数，因为种子是一致的，那么生成出来的随机数也应该是一致的
	 * 这样可以保证每次用户请求的时候，结果的一致性
	 * rate为比例，是我们允许通过的流量的比例，是%前的数字，如果需要放开10%的流量，该参数应该为10
	 *
	 * 返回boolean类型，true为该用户命中在了范围内，false为该用户没有命中指定的范围内
	 *
	 * 不确定该随即数方法在不同的机器上是否有不同的表现，这会影响我们在分布式环境上的结果，需要在多台机器上测试
	 *  测试了三台机器都是一样的
	 * */
	public static boolean isInRange(String customerCode, int rate){
		long seed = Long.valueOf(customerCode);
		Random random = new Random();
		random.setSeed(seed);
		int randomValue = random.nextInt(100);
		log.info("客户编号为：{}， 设定的范围为：{}， 生成的随机数为：{}", customerCode, rate, randomValue);

		if (randomValue <= rate){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		List<String> customers = getCustomerList();
		List<String> passCustomers = new ArrayList<>();
		List<String> notPassCusomers = new ArrayList<>();
		for (String customerCode : customers){
			boolean result = isInRange(customerCode, 10);
			if (result){
				passCustomers.add(customerCode);
			}else{
				notPassCusomers.add(customerCode);
			}
		}

		System.out.println("分布在0-10之间的客户编码" + passCustomers);
		System.out.println("分布在11-99之间的客户编码" + notPassCusomers);
	}

	public static List<String> getCustomerList(){
		List<String> customers = Arrays.asList("1982114390",
				"1982114392",
				"1982114393",
				"1982114400",
				"1982114419",
				"1982114440",
				"1982114441",
				"1982114442",
				"1982114443",
				"1982114444",
				"1982114445",
				"1982114446",
				"1982114447",
				"1982114456",
				"1982114470",
				"1982114510",
				"1982114522",
				"1982114527",
				"1982114529",
				"1982114530",
				"1982114533",
				"1982114545",
				"1982114548",
				"1982114167",
				"1982114168",
				"1982114178",
				"1982114179",
				"1982114180",
				"1982114181",
				"1982114217",
				"1982114280",
				"1982114285",
				"1982114286",
				"1982114303",
				"1982114304",
				"1982114305",
				"1982114620",
				"1982114306",
				"1982114330",
				"1982114338");
		return customers;
	}
}
