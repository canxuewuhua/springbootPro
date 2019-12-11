package com.example.demo.exercise.deduct_stock;

import com.example.demo.util.RedisUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

		try{
			boolean relationIdLock = redisUtilService.getLock("product001");
			if (relationIdLock){
				Object object = redisUtilService.get("stock");
				int stock = Integer.parseInt(object.toString());
				if (stock >0){
					int realStock = stock - 1;
					redisUtilService.set("stock", realStock);
					System.out.println("扣减成功，剩余库存："+ realStock+"");
					redisUtilService.releaseLock("product001");
					return "success";
				}else{
					System.out.println("扣减失败，库存不足");
					return "fail";
				}
			}else{
				log.error("出现并发问题，请排查！");
				return "fail";
			}
		}catch (Exception e){
			log.error("扣减库存抛出异常：{}，请排查", e);
			return "fail";
		}finally {
			redisUtilService.releaseLock("product001");
		}
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
