package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yongqiang.zhu
 * @date 2018/12/16 14:06
 */
@Data
public class RepaymentMqDTO implements Serializable {

	private String custInoutPlanLedgerId;

	private String repaymentDate;

	private String repaymentAccountType;

	private Integer leaseTime;

	private String customerId;

	private String investorMethod;
}
