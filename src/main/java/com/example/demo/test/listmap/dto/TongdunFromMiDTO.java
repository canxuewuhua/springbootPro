package com.example.demo.test.listmap.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yongqiang.zhu
 * @date 2019/10/20 1:03
 */
@Data
public class TongdunFromMiDTO implements Serializable {

	private String sign;

	private String encryptKey;

	private String encryptData;
}
