package com.example.demo.exercise.deduct_stock;

import com.example.demo.util.RedisUtilService;
import com.example.demo.util.SerialNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yongqiang.zhu
 * @date 2019/12/8 15:11
 */
@RestController
@Slf4j
public class DeductStockController {

	@Autowired
	private RedisUtilService redisUtilService;

	@RequestMapping(name = "GET请求", path = "/deduct", method = RequestMethod.GET)
	@ResponseBody
	public String deductStock(){
		//Is the byte array a result of corresponding serialization for DefaultDeserializer?; nested exception is java.io.EOFException] with root cause
		//java.io.EOFException: null
		//解决：这个异常是因为序列化和反序列化异常导致的，通常是因为你在redis服务器上手动序列化存储了一个值，
		// 然后在代理中进行反序列化获取时报错了。最好就是存取都在代码中完成。

		String clientId = SerialNumberUtil.getPrimaryId();
		try{
			//boolean relationIdLock = redisUtilService.getLock("product001");
			//（第一版本）下面是第一个成功的版本，该版本是生成一个UUID，在解锁的时候，判断这个线程是否执行完，执行完才会解锁
			// 此处要保持锁的唯一性，才能保持和网关和核心的锁一致，也不至于第一个线程还没执行完，第二个线程就进来了，并且第一个线程将
			// 第二个线程的锁给清除了的情况
			Boolean relationIdLock = redisUtilService.setNX("product001",clientId,10, TimeUnit.SECONDS);
			if (relationIdLock){
				Object object = redisUtilService.get("stock");
				int stock = Integer.parseInt(object.toString());
				if (stock >0){
					int realStock = stock - 1;
					redisUtilService.set("stock", realStock);
					System.out.println("扣减成功，剩余库存："+ realStock+"");
				}else{
					System.out.println("扣减失败，库存不足");
				}
			}else{
				log.error("目前redis锁正在锁着这个product001，出现并发问题，请排查！");
				return "error";
			}
		}finally {
			//（第一版本）,该处使用get
			// 本线程的锁被另外一个线程给清除了，为防止被清除，在删除锁的时候判断一下
			if (clientId.equals(redisUtilService.get("product001"))){
				// 释放锁
				redisUtilService.releaseLock("product001");
			}
		}
		return "End";
	}

	@RequestMapping(name = "GET请求", path = "/set", method = RequestMethod.GET)
	@ResponseBody
	public void setRedisValue(){
		redisUtilService.set("stock","50");
	}

	@RequestMapping(name = "GET请求", path = "/lock", method = RequestMethod.GET)
	@ResponseBody
	public void setProductRedisValue(){
		redisUtilService.set("product001","产品001");
	}
}
