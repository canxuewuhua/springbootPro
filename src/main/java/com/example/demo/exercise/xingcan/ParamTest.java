package com.example.demo.exercise.xingcan;

import com.example.demo.dto.ParamDTO;

import java.math.BigDecimal;

/**
 * @author yongqiang.zhu
 * 形参传入是基本类型的话，是不会改变值的
 *         是对象的话，是会改变的                        
 */

public class ParamTest {
	public static void main(String[] args) {
		printOne();
	}


	public static void printOne(){
//		ParamDTO paramDTO = new ParamDTO();
//		paramDTO.setParamId("10001");
//		paramDTO.setTransAmount(new BigDecimal("205"));
//		paramDTO.setLeaseTime(1);
		int m = 9;
		changeValue(m);
		System.out.println(m);
	}

	public static void changeParam(ParamDTO paramDTO){
		paramDTO.setTransAmount(new BigDecimal("208"));
	}
	public static void changeValue(int m){
		m = 3;
	}
}
