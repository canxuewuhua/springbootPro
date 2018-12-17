package com.example.demo.controller;


import com.example.demo.service.EmployeeService;
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
    private EmployeeService employeeService;

    @RequestMapping(name = "POST请求", path = "/updateEmployeeAgeById", method = RequestMethod.POST)
    public void updateEmployeeAgeById(){
        employeeService.updateEmployeeAgeById();
    }

    @RequestMapping(name = "POST请求", path = "/updateEmployeeSalaryById", method = RequestMethod.POST)
    public void updateEmployeeSalaryById(){
        employeeService.updateEmployeeSalaryById();
    }

    @RequestMapping(name = "POST请求", path = "/testEmployeeTransaction", method = RequestMethod.POST)
    public boolean testEmployeeTransaction(){
        return employeeService.testEmployeeTransaction();
    }
}
