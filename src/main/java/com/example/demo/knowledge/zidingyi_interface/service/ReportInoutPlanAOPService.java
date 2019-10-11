package com.example.demo.knowledge.zidingyi_interface.service;

import com.example.demo.common.ResultDTO;
import com.example.demo.domain.ReportInoutPlanVO;
import com.example.demo.domain.ReportInoutPlanVOExample;
import com.example.demo.mapper.ReportInoutPlanDAO;
import com.example.demo.util.ResultUtils;
import com.example.demo.util.SerialNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yongqiang.zhu
 * @date 2019/10/11 23:24
 */
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
public class ReportInoutPlanAOPService {

	@Autowired
	private ReportInoutPlanDAO reportInoutPlanDAO;

	public ResultDTO insertReportInoutPlan(){
		log.info("生成数据开始..");
		ReportInoutPlanVO reportInoutPlanVO = new ReportInoutPlanVO();
		reportInoutPlanVO.setCustInoutPlanLedgerId(SerialNumberUtil.getPrimaryId());
		reportInoutPlanVO.setCustomerCode("1027341854");
		reportInoutPlanVO.setCustomerName("朱永强");
		reportInoutPlanDAO.insert(reportInoutPlanVO);
		log.info("数据插入完成..");
		return ResultUtils.success();
	}

	public ResultDTO selectReportInoutPlan(){
		log.info("查询数据开始..");
		ReportInoutPlanVOExample reportInoutPlanVOExample = new ReportInoutPlanVOExample();
		List<ReportInoutPlanVO> reportInoutPlanVOList = reportInoutPlanDAO.selectByExample(reportInoutPlanVOExample);
		log.info("数据查询完成..");
		return ResultUtils.success(reportInoutPlanVOList);
	}
}
