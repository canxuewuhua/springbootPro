package com.example.demo.domain;

import lombok.Data;

/**
 * @author yongqiang.zhu
 * @date 2019/10/11 20:28
 */
@Data
public class InoutPlanLedgerVO {

	private String customerId;

	private Integer leaseTime;

	private Integer overdueDay;
}
