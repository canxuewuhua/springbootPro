package com.example.demo.knowledge.zidingyi_interface.controller;

import com.example.demo.common.ResultDTO;
import com.example.demo.knowledge.zidingyi_interface.service.ReportInoutPlanAOPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表还款计划使用切面aop进行加密存储，解密展示
 */
@RestController
@RequestMapping("/aop/report")
public class ReportInoutPlanAOPController {

	@Autowired
	private ReportInoutPlanAOPService reportInoutPlanAOPService;

	/**
	 * 以加密形式存储数据库
	 */
	@RequestMapping(name = "以加密形式存储数据库", path = "/insert", method = RequestMethod.GET)
	public ResultDTO insertReportInoutPlan() {
		return reportInoutPlanAOPService.insertReportInoutPlan();
	}

	/**
	 * 以解密形式查询数据库
	 */
	@RequestMapping(name = "以加密形式存储数据库", path = "/select", method = RequestMethod.GET)
	public ResultDTO selectReportInoutPlan() {
		return reportInoutPlanAOPService.selectReportInoutPlan();
	}
}
