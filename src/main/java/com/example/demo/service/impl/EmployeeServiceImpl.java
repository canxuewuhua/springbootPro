package com.example.demo.service.impl;

import com.example.demo.domain.EmployeeVO;
import com.example.demo.domain.EmployerVO;
import com.example.demo.mapper.EmployeeDAO;
import com.example.demo.mapper.EmployerDAO;
import com.example.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 * @date 2018/12/17 22:11
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private EmployerDAO employerDAO;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateEmployeeAgeById(){
		EmployeeVO employeeVO = employeeDAO.selectByPrimaryKey(1);
		log.info("更新之前的employeeVO的年龄age:{}", employeeVO.getAge());
		employeeVO.setAge(35);
		employeeDAO.updateByPrimaryKeySelective(employeeVO);
		updateEmployeeSalaryById();
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateEmployeeSalaryById(){
		int m = 10/0; //抛出ArithmeticException异常
		EmployerVO employerVO = employerDAO.selectByPrimaryKey(1);
		log.info("{}更新前的薪水是：{}", employerVO.getName(), employerVO.getSalary());
		employerVO.setSalary(new BigDecimal("12000"));
		employerDAO.updateByPrimaryKeySelective(employerVO);
	}

	public boolean testEmployeeTransaction(){
		try {
			log.info("开始执行parent方法");
			updateEmployeeAgeById();
			log.info("更新完");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
