package com.example.demo.test.redis_grab_red_packet;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>抢红包</P>
 * 1.某个红包的子红包队列：存放着每个红包的金额。数据结构：List, Key=rp-{红包ID}, value=红包金额
 * 2.已抢到红包的用户散列：存放已经抢到红包的用户及金额。数据结构：Hash, Key=rp-gain-{红包ID}， hashKey={用户ID}, hashValue={红包金额}
 *
 * @author hanchao
 */
@Slf4j
public class RedisLuaDemo {
	/**
	 * 抢红包的lua脚本
	 * KEYS[1] = 红包队列Key
	 * KEYS[2] = 抢到红包的用户Hash Key
	 * ARGV[1] = 用户ID
	 */
	private static final String TEST_SCRIPT = "" +
			//如果用户已经抢到过红包，则返回0
			" if redis.call('hexists',KEYS[2],ARGV[1]) ~= 0 then " +
			"   return 0 " +
			" end " +
			//如果用户没抢到过,先查看红包数量是否足够
			" if redis.call('llen',KEYS[1]) ~= 0 then " +
			//如果仍剩余红包，则取出一个红包
			"   local money = redis.call('rpop',KEYS[1]) " +
			//将用户信息和红包金额保存
			"   redis.call('hset',KEYS[2],ARGV[1],money) " +
			"   return 1 " +
			" end " +
			//如果无红包了，则返回2表示红包已经抢完
			"   return 2 ";

	public static void main(String[] args) throws InterruptedException {
		//红包List Key
		String redPacketKey = "rp-list-9527";
		//抢到红包的用户hash Key
		String gainUserKey = "rp-user-map-9527";
		//红包分发总金额
		Integer sum = 0;
		//红包实际被抢总金额
		AtomicReference<Integer> total = new AtomicReference<>(0);

		//门闩一：以便所有线程同时开始运行
		CountDownLatch switchLatch = new CountDownLatch(1);
		//门闩二：以便所有线程都能运行完成
		CountDownLatch countLatch = new CountDownLatch(10);
		//线程池
		ExecutorService executorService = Executors.newCachedThreadPool();

		Jedis jedis = null;
		try {
			jedis = RedisUtil.getConnection();
			if (Objects.nonNull(jedis)) {
				//先清空
				jedis.del(redPacketKey);
				jedis.del(gainUserKey);

				//预先生成5个红包
				for (int i = 0; i < 5; i++) {
					int money = RandomUtils.nextInt(100, 150);
					sum += money;
					//存入缓存
					jedis.lpush(redPacketKey, String.valueOf(money));
				}
				List<String> redPacketList = jedis.lrange(redPacketKey, 0, jedis.llen(redPacketKey));
				log.info("共生成{}元的红包,金额分别为:{}", sum, redPacketList);

				//脚本加载之后生成的sha编码
				String scriptSha = jedis.scriptLoad(TEST_SCRIPT);

				//脚本执行所需的Key列表，也可以在脚本中直接写死
				List<String> keyList = Lists.newArrayList(redPacketKey, gainUserKey);

				log.info("=============开始抢红包=============");
				//有7个人抢红包,有些人抢了2次
				for (int i = 0; i < 10; i++) {
					//有些人手快，抢了多次
					int finalUserId = i % 7;
					//进行抢红包
					executorService.submit(new GrabRedPacketTask(switchLatch, countLatch, finalUserId, scriptSha, keyList));
				}
				switchLatch.countDown();
				executorService.shutdown();
				countLatch.await();

				//显示抢红包情况
				log.info("=============抢红包结束=============");
				Map<String, String> gainUserMap = jedis.hgetAll(gainUserKey);
				gainUserMap.forEach((userId, money) -> {
					log.info("UserId:{},money:{}", userId, money);
					Integer now = total.get();
					total.compareAndSet(now, now + Integer.parseInt(money));
				});
				log.info("共生成{}元的红包,实际被抢红包总额{}元。", sum, total.get());
			}
		} finally {
			//关闭redis
			RedisUtil.close(jedis);
		}
	}
}


