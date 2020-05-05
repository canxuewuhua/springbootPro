package com.example.demo.beancopier;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yongqiang.zhu
 * @date 2020/5/5 16:42
 */
@Data
public class InoutPlanQueryDTO {

	private BigDecimal amount;
	private String repayStatus;
	private List<ItemDTO> items;
}
