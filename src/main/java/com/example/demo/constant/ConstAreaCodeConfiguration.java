package com.example.demo.constant;

import com.example.demo.mapper.AreaCodeDAO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 地域编码 配置常量
 */
//@Component
@Slf4j
public class ConstAreaCodeConfiguration {

	@Autowired
	private AreaCodeDAO areaCodeDAO;

	private static List<String> directlyCityCodeList;

	/**
	 * 初始化直辖市code列表
	 */
	@PostConstruct
	public void init(){
		this.directlyCityCodeList = areaCodeDAO.queryDirectlyCityCodes();
	}

	/**
	 * 获取 直辖市code列表
	 * @return
	 */
	public static List<String> getDirectlyCityCodeList(){
		if (CollectionUtils.isEmpty(directlyCityCodeList)){
			directlyCityCodeList = Lists.newArrayList();
		}
		return directlyCityCodeList;
	}
}
