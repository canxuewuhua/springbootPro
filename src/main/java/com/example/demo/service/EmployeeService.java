package com.example.demo.service;

import com.example.demo.domain.EmployeeVO;
/**
 * @author yongqiang.zhu
 * @date 2018/12/17 21:21
 */
public interface EmployeeService {

	public void updateEmployeeAgeById();

	public void updateEmployeeSalaryById();

	public boolean testEmployeeTransaction();

	public EmployeeVO getEmployeeById(int id);
}