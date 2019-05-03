package exercise;

import com.example.demo.SpringBootWildApplication;
import dto.CusLedger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yongqiang.zhu
 * @date 2019/5/3 23:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
@WebAppConfiguration
@Transactional(rollbackFor = Throwable.class)
public class CompareTest {

	@Before
	public void setUp() {
		// 成功时返回的bean
		//ResultDTO resultDTO = new ResultDTO();

		//Mockito.when(creditService.createCredit(Mockito.anyObject())).thenReturn(resultDTO);
	}

	@Test
	@Rollback()
	public void compareTest() {
		CusLedger cusLedger01 = new CusLedger();
		CusLedger cusLedger02 = new CusLedger();
		CusLedger cusLedger03 = new CusLedger();
		cusLedger01.setCusId("1001");
		cusLedger02.setCusId("1002");
		cusLedger03.setCusId("1003");
		cusLedger01.setLeaseTime(1);
		cusLedger02.setLeaseTime(2);
		cusLedger03.setLeaseTime(3);
		List<CusLedger> cusLedgerList = new ArrayList<>();
		cusLedgerList.add(cusLedger03);
		cusLedgerList.add(cusLedger01);
		cusLedgerList.add(cusLedger02);

		Collections.sort(cusLedgerList, new Comparator<CusLedger>() {
			@Override
			public int compare(CusLedger o1, CusLedger o2) {
				return o2.getLeaseTime() - o1.getLeaseTime();
			}
		});
		System.out.println(cusLedgerList);


		//Assert.assertEquals(userName, "zhangsangeng");
	}

	@After
	public void tearDown() throws Exception {

	}
}
