package com.example.demo.beancopier;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yongqiang.zhu
 * @date 2020/5/5 16:41
 */
public class BeanCopyListObjectTest {
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
		InoutPlanNotifyDTO inoutPlanNotifyDTO = new InoutPlanNotifyDTO();
		InoutPlanQueryDTO inoutPlanQueryDTO = new InoutPlanQueryDTO();
		ItemDTO itemDTO1 = new ItemDTO();
		itemDTO1.setItemName("本金");
		itemDTO1.setCorpus(new BigDecimal("500"));
		ItemDTO itemDTO2 = new ItemDTO();
		itemDTO2.setItemName("利息");
		itemDTO2.setCorpus(new BigDecimal("12"));
		List<ItemDTO> itemDTOS = new ArrayList<>();
		itemDTOS.add(itemDTO1);
		itemDTOS.add(itemDTO2);
		inoutPlanQueryDTO.setAmount(new BigDecimal("512"));
		inoutPlanQueryDTO.setRepayStatus("SUCCESS");
		inoutPlanQueryDTO.setItems(itemDTOS);
		BeanUtils.copyProperties(inoutPlanQueryDTO, inoutPlanNotifyDTO);
		inoutPlanNotifyDTO.setOrderNo("JK15998766633");
		System.out.println(inoutPlanNotifyDTO);
	}
}
