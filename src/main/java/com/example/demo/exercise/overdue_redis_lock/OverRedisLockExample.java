package com.example.demo.exercise.overdue_redis_lock;

import com.example.demo.common.CodeMsg;
import com.example.demo.common.ResultDTO;
import com.example.demo.common.enumeration.EnumRedisKey;
import com.example.demo.util.RedisAtomicClient;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 *
 * 逾期跑批时加的redis锁，可供参考
 */
@Slf4j
public class OverRedisLockExample {
	@Autowired
	private RedisAtomicClient redisAtomicClient;

	public ResultDTO createOrUpdateOverdueByBatch(Date batchDate){
		// 获取redis key
		String redisKey = EnumRedisKey.OVERDUE_BATCH_IS_LOCK;
		RedisAtomicClient.RedisLockInner lock = redisAtomicClient.getLock(redisKey, 30 * 60);
		if (lock != null) {
			try {
				log.info("逾期跑批redis加锁成功,跑批时间{}，当前时间{}", batchDate, new Date());
				// 执行 逾期跑批方法
				//return createOrUpdateOverdueByBatchForRedis(batchDate);
				return ResultUtils.success();
			} finally {
				lock.unlock();
				log.info("逾期跑批redis解锁成功,跑批时间{}，当前时间{}", batchDate, new Date());
			}
		} else {
			log.error("{}发起逾期跑批失败，存在并发的逾期跑批，跑批时间{}", "", "");
			return ResultUtils.fail(CodeMsg.OVERDUE_BATCH_RUN_LOCKING);
		}
	}
}
