package com.example.demo.controller;

import com.example.demo.domain.EmployeeVO;
import com.example.demo.mapper.EmployeeDAO;
import com.example.demo.vo.BaseReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping(name = "POST请求", path = "/updateEmployeeById", method = RequestMethod.POST)
    public BaseReturn updateEmployeeById(){
        BaseReturn baseReturn = new BaseReturn();
        baseReturn.setCode(20101);
        baseReturn.setMsg("SUCCESS");
        EmployeeVO employeeVO = employeeDAO.selectByPrimaryKey(1);
        log.info("更新之前的employeeVO:{}", employeeVO);
        employeeVO.setAge(15);
        employeeDAO.updateByPrimaryKeySelective(employeeVO);
        baseReturn.setData(employeeVO);
        return baseReturn;
    }
}
