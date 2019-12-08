package com.example.demo.exercise.lottery;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yongqiang.zhu
 * @date 2019/12/8 14:24
 *        抽奖
 */
public class Lottery {

	static String[] qq = {"Y01","Y02","Y03","Y04","Y05","Y06","Y07","Y08","Y09","Y10","Y11","Y12","Y13","Y14","Y15","Y16","Y17","Y18","Y19","Y20",};
	public static void main(String[] args) {
		luckDraw(qq);
	}

	public static int num = 3;
	public static void luckDraw(String[] str){
		Set<String> set = new HashSet<>();
		//“死循环”有两种写法：for(;;)和while(true)
		//一目了然，for (；；)指令少，不占用寄存器，而且没有判断跳转，比while (1)好
		for (; ; ){
			if (set.size() == num){
				break;
			}
			int random = new SecureRandom().nextInt(str.length);
			set.add(str[random]);
		}
		Iterator<String> iterator = set.iterator();
		System.out.println("---------------两周年抽奖活动直播----------------");
		System.out.println("开奖人：YQ");
		System.out.println("开中奖名单：");
		while (iterator.hasNext()){
			System.out.printf("中华为手机一部的是:%s\r\n", iterator.next());
			System.out.println("恭喜中奖的朋友，请和客服进行联系");
		}
	}
}
