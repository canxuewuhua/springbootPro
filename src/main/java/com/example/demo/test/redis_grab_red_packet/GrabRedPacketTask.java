package com.example.demo.test.redis_grab_red_packet;

/**
 * @author yongqiang.zhu
 * @date 2019/10/13 0:06
 */

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * <p>抢红包线程</P>
 *
 * @author hanchao
 */
@Slf4j
@AllArgsConstructor
public class GrabRedPacketTask implements Runnable {
	/**
	 * 门闩一：以便所有线程同时开始运行
	 */
	private CountDownLatch switchLatch;
	/**
	 * 门闩二：以便所有线程都能运行完成
	 */
	private CountDownLatch countLatch;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 抢红包脚本sha
	 */
	private String scriptSha;
	/**
	 * 抢红包脚本执行所需的Key列表
	 */
	private List<String> keyList;

	/**
	 * 抢红包
	 */
	@Override
	public void run() {
		try {
			switchLatch.await();
		} catch (InterruptedException e) {
			log.error("error");
		}
		//脚本执行所需的其他参数列表，也可以在脚本中直接写死
		List<String> argList = Lists.newArrayList(String.valueOf(userId));

		Jedis jedis = null;
		try {
			//抢红包
			jedis = RedisUtil.getConnection();
			if (Objects.nonNull(jedis)) {
				//这里的脚本利用的是其他地方已经加载好的
				String result = jedis.evalsha(scriptSha, keyList, argList).toString();
				switch (Integer.parseInt(result)) {
					case 0:
						log.info("UserId:{} 已经抢到了红包，不能再抢.", userId);
						break;
					case 1:
						log.info("UserId:{} 眼疾手快，抢到了红包 !!!", userId);
						break;
					case 2:
						log.info("UserId:{} 手慢，没抢到红包.", userId);
						break;
					default:
						log.info("Illegal Result: {}", result);
						break;
				}
			}
		} finally {
			countLatch.countDown();
			RedisUtil.close(jedis);
		}
	}
}


