package com.example.demo.service;

import com.example.demo.domain.EmployeeVO;
import com.example.demo.domain.EmployerVO;
import com.example.demo.mapper.EmployeeDAO;
import com.example.demo.mapper.EmployerDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 * @date 2018/12/17 21:21
 */
public interface EmployeeService {

	public void updateEmployeeAgeById();

	public void updateEmployeeSalaryById();

	public boolean testEmployeeTransaction();
}
