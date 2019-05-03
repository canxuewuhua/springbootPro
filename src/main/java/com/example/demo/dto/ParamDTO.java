package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 */
@Data
public class ParamDTO {
	private String paramId;

	private BigDecimal transAmount;

	private Integer leaseTime;
}
