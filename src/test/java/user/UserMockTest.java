package user;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.domain.EmployeeVO;
import com.example.demo.domain.UserVO;
import com.example.demo.mapper.EmployeeDAO;
import com.example.demo.mapper.UserDAO;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserNewService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yongqiang.zhu
 * 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
@WebAppConfiguration
@Transactional(rollbackFor = Throwable.class)
public class UserMockTest {

	@MockBean
	private UserDAO userDAO;

	@Autowired
	private UserNewService userNewService;

	@Before
	public void setUp() {
		// 成功时返回的bean
		//ResultDTO resultDTO = new ResultDTO();

		//Mockito.when(creditService.createCredit(Mockito.anyObject())).thenReturn(resultDTO);
	}

	@Test
	@Rollback()
	public void addEmployeeTest() {
		//List<UserVO> employeeVOList = new ArrayList<>();
		UserVO userVO = new UserVO();
		userVO.setId(10273140);
		userVO.setName("zhangsangeng");
		userVO.setAddress("北京大兴");
		userVO.setAge(28);
		//employeeVOList.add(userVO);

		//Mockito.when(userDAO.selectByExample(Mockito.anyObject())).thenReturn(employeeVOList);

		//模拟方法调用的返回值
		//使用anyObject()来匹配任意Object类型参数
		Mockito.when(userDAO.selectByPrimaryKey(Mockito.anyObject())).thenReturn(userVO);

		UserVO userVO1 = userNewService.getUserInfo(10273140);
		String userName = userVO1.getName();
		Assert.assertEquals(userName, "zhangsangeng");
}

	@After
	public void tearDown() throws Exception {

	}
}
