package com.example.demo.exercise.lambda_related;

import lombok.Data;

/**
 * @author yongqiang.zhu
 * @date 2019/9/9 22:51
 */
@Data
public class FacePlusPlusDTO {

	private String transDate;

	private Long createTime;

	private String userId;

	public FacePlusPlusDTO(String transDate, Long createTime, String userId) {
		this.transDate = transDate;
		this.createTime = createTime;
		this.userId = userId;
	}
}
