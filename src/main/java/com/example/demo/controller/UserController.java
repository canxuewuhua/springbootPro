package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.Customer;
import com.example.demo.domain.UserVO;
import com.example.demo.dto.PsersonRequestDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.DateUtil;
import com.example.demo.vo.BaseReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHUYONGQIANG on 2018/5/26.
 */
@Slf4j
@RestController
@RequestMapping("/project")
public class UserController {
    @Value("${user.namevalue}}")
    String namevalue;
    @Value("${user.id}}")
    String id;

    @Autowired
    private UserService userService;

    private static final String USER_ID = "joys-user-id";

    @RequestMapping("/say")
    public String sayHello(){
        Date date = new Date();
        String str = "Hello--World--20180726:{"+date+"}";
        return str;
    }

    @RequestMapping(name = "批量扣款", path = "/sayRepayment", method = RequestMethod.GET)
    public String sayRepayment(){
        Date date = new Date();
        String str = "Kingsoft:{"+date+"}";
        return str;
    }

    @RequestMapping("/getUser")
    public String getUser(){
        String str = namevalue+id;
        return str;
    }

    @RequestMapping("/getUserMessageFromDB")
    public String getUserMessageFromDB(int id){
        Customer customer = userService.getCustomerMessageById(id);
        return JSON.toJSON(customer).toString();
    }

    @RequestMapping("/updateUser")
    public void updateUser(){
       userService.updateUser();
    }

    /**
     * HttpServletRequest req, HttpServletResponse resp, Date date
     */

    @RequestMapping(name = "POST请求", path = "/getPersoByName", method = RequestMethod.POST)
    public BaseReturn getPersoByName(@RequestBody String  name){
        BaseReturn baseReturn = new BaseReturn();
        baseReturn.setCode(20101);
        baseReturn.setMsg("SUCCESS");
        Customer customer = new Customer();
        customer.setId(1001);
        customer.setName("张三丰");
        customer.setAddress("河北石家庄");
        customer.setCount(1);
        baseReturn.setData(customer);
        log.info("getPersoByName方法：打印入参name：{}，建金中心", name);
        return baseReturn;
    }
    @RequestMapping(name = "POST请求", path = "/getPersonByDate", method = RequestMethod.POST)
    public BaseReturn getPersonByDate(@RequestBody Date date){
        BaseReturn baseReturn = new BaseReturn();
        baseReturn.setCode(20101);
        baseReturn.setMsg("SUCCESS");
        Customer customer = new Customer();
        customer.setId(1001);
        customer.setName("张三丰");
        customer.setAddress("河北石家庄");
        customer.setCount(1);
        baseReturn.setData(customer);
        baseReturn.setUserFlag("UserId");
        Date curDate = new Date();
        log.info("getPersonByDate方法：打印传入的date时间：{}，建金中心", date);
        log.info("打印当前系统的时间：{}", curDate);
        return baseReturn;
    }
    @RequestMapping(name = "POST请求", path = "/getPersonByDateFormat", method = RequestMethod.POST)
    public BaseReturn getPersonByDateFormat(@RequestBody String date){
        Date dateFormat = DateUtil.parseDate(date, DateUtil.FORMAT_PATTERN_TIME);
        BaseReturn baseReturn = new BaseReturn();
        baseReturn.setCode(20101);
        baseReturn.setMsg("SUCCESS");
        Customer customer = new Customer();
        customer.setId(1001);
        customer.setName("李志");
        customer.setAddress("河北石家庄");
        customer.setCount(1);
        baseReturn.setData(customer);
        baseReturn.setUserFlag("UserId");
        log.info("getPersonByDate方法：打印当前时间：{}，建金大厦", dateFormat);
        return baseReturn;
    }

    @RequestMapping(name = "POST请求", path = "/getPerson", method = RequestMethod.POST)
    public BaseReturn getPerson(HttpServletRequest req, HttpServletResponse resp){
        String userId = (String) req.getSession().getAttribute(USER_ID);
        log.info("req.getSession().getAttribute(USER_ID)--userid:{}", userId);
        if (StringUtils.isEmpty(userId)) {
            userId=req.getHeader(USER_ID);
        }
        log.info("req.getHeader(USER_ID--userid:{}", userId);
        BaseReturn baseReturn = new BaseReturn();
        baseReturn.setCode(20101);
        baseReturn.setMsg("SUCCESS");
        Customer customer = new Customer();
        customer.setId(1001);
        customer.setName("张三丰");
        customer.setAddress("河北石家庄");
        customer.setCount(1);
        baseReturn.setData(customer);
        baseReturn.setUserFlag("UserId");
        log.info("Name:{},Address:{},UserId:{}", customer.getName(),customer.getAddress(),userId);
        return baseReturn;
    }

    @RequestMapping(name = "POST请求", path = "/getUserList", method = RequestMethod.GET)
    public BaseReturn getUserList(){
        BaseReturn baseReturn = new BaseReturn();
        List<UserVO> userVOS = userService.getUserList();
        baseReturn.setData(userVOS);
        baseReturn.setCode(10000);
        baseReturn.setMsg("查询成功");
        return baseReturn;
    }

    @RequestMapping(name = "POST请求", path = "/getListMapUser", method = RequestMethod.GET)
    public BaseReturn getListMapUser(){
        BaseReturn baseReturn = new BaseReturn();
        List<Map<String,Object>> userVOS = userService.getListMapUser();
        baseReturn.setData(userVOS);
        baseReturn.setCode(10000);
        baseReturn.setMsg("查询成功");
        return baseReturn;
    }

    @RequestMapping(name = "POST请求", path = "/getPersonByDateAndName", method = RequestMethod.POST)
    public BaseReturn getPersonByDateAndName(@RequestBody(required = false) PsersonRequestDTO psersonRequestDTO){
        Date date = psersonRequestDTO.getDate();
        String name = psersonRequestDTO.getName();
        BaseReturn baseReturn = new BaseReturn();
        baseReturn.setCode(20101);
        baseReturn.setMsg("SUCCESS");
        Customer customer = new Customer();
        customer.setId(1001);
        customer.setName("张三丰");
        customer.setAddress("河北石家庄");
        customer.setCount(1);
        baseReturn.setData(customer);
        baseReturn.setUserFlag("UserId");
        Date curDate = new Date();
        log.info("getPersonByDateAndName方法：打印传入的date时间：{}，建金中心，姓名：{}", date, name);
        log.info("打印当前系统的时间：{}", curDate);
        return baseReturn;
    }
}
