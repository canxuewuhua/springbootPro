package employee;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.domain.EmployeeVO;
import com.example.demo.mapper.EmployeeDAO;
import com.example.demo.service.EmployeeService;
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
public class EmployeeMockTest {

	@MockBean
	private EmployeeDAO employeeDAO;

	@Autowired
	private EmployeeService employeeService;

	@Before
	public void setUp() {
		// 成功时返回的bean
		//ResultDTO resultDTO = new ResultDTO();

		//Mockito.when(creditService.createCredit(Mockito.anyObject())).thenReturn(resultDTO);
	}

	@Test
	@Rollback()
	public void addEmployeeTest() {
		List<EmployeeVO> employeeVOList = new ArrayList<>();
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId(10273140);
		employeeVO.setName("zhangsangeng");
		employeeVO.setAddress("北京大兴");
		employeeVO.setAge(28);
		employeeVO.setSalary(new BigDecimal("5000"));
		employeeVOList.add(employeeVO);

		Mockito.when(employeeDAO.selectByExample(Mockito.anyObject())).thenReturn(employeeVOList);

		EmployeeVO emp = employeeService.getEmployeeById(10273140);
		int empId = emp.getId();
		Assert.assertEquals(empId, 10273140);
}

	@After
	public void tearDown() throws Exception {

	}
}
