package com.example.demo.exercise.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author yongqiang.zhu
 * @date 2019/11/7 22:28
 *
 * @Async
 * 所修饰的函数不要定义为static类型，这样异步调用不会生效
 * 参考网址：https://blog.csdn.net/weixin_39528789/article/details/80769112 （springboot定时任务的使用）
 *
 * 此类使用在启动类上添加@EnableAsync注解
 */
@Component
@Slf4j
public class TaskDemo {

	public static Random random =new Random();

	@Async
	public Future<String> doTaskOne() throws Exception {
		System.out.println("开始做任务一");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务一，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务一完成");
	}

	@Async
	public Future<String> doTaskTwo() throws Exception {
		System.out.println("开始做任务二");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务二，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务一完成");
	}

	@Async
	public Future<String> doTaskThree() throws Exception {
		System.out.println("开始做任务三");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		log.info("完成任务三，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务一完成");
	}
}
