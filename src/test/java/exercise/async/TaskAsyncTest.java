package exercise.async;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.exercise.async.TaskDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Future;

/**
 * @author yongqiang.zhu
 * @date 2019/11/7 22:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
@WebAppConfiguration
@Transactional(rollbackFor = Throwable.class)
@Slf4j
public class TaskAsyncTest {

	@Autowired
	private TaskDemo taskDemo;

	@Before
	public void setUp() {

	}

	@Rollback
	@Test
	public void testTaskDemo() throws Exception {
		long start = System.currentTimeMillis();
		Future<String> task1 =taskDemo.doTaskOne();
		Future<String> task2 =taskDemo.doTaskTwo();
		Future<String> task3 =taskDemo.doTaskThree();
		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		log.info("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}

	@After
	public void tearDown() {
	}
}
