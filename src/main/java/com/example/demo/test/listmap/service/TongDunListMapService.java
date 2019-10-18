package com.example.demo.test.listmap.service;

import com.example.demo.common.ResultDTO;
import com.example.demo.mapper.UserDAO;
import com.example.demo.test.listmap.dto.TongDunRequestDTO;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yongqiang.zhu
 * @date 2019/10/18 20:04
 */
@Service
@Transactional
@Slf4j
public class TongDunListMapService {


	@Autowired
	private UserDAO userDAO;

	public ResultDTO getUserList(){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		TongDunRequestDTO tongDunRequestDTO1 =new TongDunRequestDTO();
		tongDunRequestDTO1.setName("张三");
		tongDunRequestDTO1.setAge(25);
		List<Map<String, Object>> mapBasicEditionList = userDAO.selectTongDunRecord(tongDunRequestDTO1);
		for (Map<String, Object> map : mapBasicEditionList) {
			map.put("productCode", "tongdunjichu");
			map.put("productName", "同盾基础分");
		}
		log.info("mapBasicEditionList：{}",mapBasicEditionList);
		mapList.addAll(mapBasicEditionList);
		TongDunRequestDTO tongDunRequestDTO3 =new TongDunRequestDTO();
		tongDunRequestDTO3.setName("李四");
		tongDunRequestDTO3.setAge(24);
		List<Map<String, Object>> mapCreditAuditBeforeListFlag = userDAO.selectTongDunRecord(tongDunRequestDTO3);
		log.info("mapCreditAuditBeforeListFlag：{}",mapCreditAuditBeforeListFlag);
		TongDunRequestDTO tongDunRequestDTO2 =new TongDunRequestDTO();
		tongDunRequestDTO2.setName("张三");
		tongDunRequestDTO2.setAge(25);
		List<Map<String, Object>> mapCreditAuditBeforeList = userDAO.selectTongDunRecord(tongDunRequestDTO2);
		log.info("mapCreditAuditBeforeList：{}",mapCreditAuditBeforeList);
		for (Map<String, Object> map : mapCreditAuditBeforeList) {
			map.put("productCode", "tongdunshenhe");
			map.put("productName", "贷前审核");
		}
		log.info("mapCreditAuditBeforeList组装后的：{}",mapCreditAuditBeforeList);
		mapList.addAll(mapCreditAuditBeforeList);
		return ResultUtils.success();
	}

	public ResultDTO getUserInfo(){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		TongDunRequestDTO tongDunRequestDTO1 =new TongDunRequestDTO();
		tongDunRequestDTO1.setName("张三");
		tongDunRequestDTO1.setAge(25);
		List<Map<String, Object>> mapTongDunList = userDAO.selectTongDunRecord(tongDunRequestDTO1);

		List<Map<String, Object>> mapBasicEditionList = new ArrayList<>();
		List<Map<String, Object>> mapCreditAuditBeforeList = new ArrayList<>();


		for (Map<String, Object> map : mapTongDunList) {
			Map<String, Object> mapa = new HashMap<>();
			mapa.putAll(map);
			mapBasicEditionList.add(mapa);
		}
		for (Map<String, Object> map : mapBasicEditionList) {
			map.put("productCode", "tongdunjichu");
			map.put("productName", "同盾基础分");
		}

		log.info("mapTongDunList：{}",mapTongDunList);
		for (Map<String, Object> map : mapTongDunList) {
			Map<String, Object> mapb = new HashMap<>();
			mapb.putAll(map);
			mapCreditAuditBeforeList.add(mapb);
		}
		for (Map<String, Object> map : mapCreditAuditBeforeList) {
			map.put("productCode", "tongdunshenhe");
			map.put("productName", "贷前审核");
		}
		log.info("mapBasicEditionList：{}",mapBasicEditionList);
		log.info("mapCreditAuditBeforeList：{}",mapCreditAuditBeforeList);

		mapList.addAll(mapBasicEditionList);
		mapList.addAll(mapCreditAuditBeforeList);
		log.info("mapList：{}",mapList);
		return ResultUtils.success();
	}
}
