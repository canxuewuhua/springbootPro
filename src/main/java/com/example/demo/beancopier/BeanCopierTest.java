package com.example.demo.beancopier;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;

/**
 *  大DTO可以往小DTO进行复制，小的不能往大的复制
 */
public class BeanCopierTest {
	public static void main(String[] args) {

//        print1();
//        print2();
	}

	/**
	 * 小DTO 转化为大DTO
	 */
	public  static void print1(){
		// 父类
		ParnerDTO parnerDTO = new ParnerDTO();
		// 子类 属性更全
		PartnerV2DTO partnerV2DTO = new PartnerV2DTO();
		parnerDTO.setName("zhagnsan");
		parnerDTO.setAge("28");
		parnerDTO.setBirthday(new Date());

		BeanCopier.create(ParnerDTO.class, PartnerV2DTO.class, false).copy(parnerDTO, partnerV2DTO, null);

		//BeanUtils.copyProperties(parnerDTO,partnerV2DTO);
		System.out.println(partnerV2DTO);
		//继承了但是没有权限访问
		System.out.println(partnerV2DTO.getName());

		System.out.println("-------------------");
	}

	/**
	 * 大DTO 转化为小DTO
	 */
	public  static void print2(){
		PartnerV2DTO partnerV2DTO = new PartnerV2DTO();
		partnerV2DTO.setName("zhagnsan");
		partnerV2DTO.setAge("28");
		partnerV2DTO.setGrade("二年级");
		partnerV2DTO.setBirthday(new Date());

		ParnerDTO parnerDTO = new ParnerDTO();

		BeanCopier.create(PartnerV2DTO.class, ParnerDTO.class, false).copy(partnerV2DTO, parnerDTO, null);

		//BeanUtils.copyProperties(partnerV2DTO, parnerDTO);

		// 提供类型转换功能 发现两个JavaBean的同名属性为不同类型时，在支持的数据类型范围内进行转换
		//PropertyUtils.copyProperties(parnerDTO, partnerV2DTO);
		System.out.println(partnerV2DTO);
		System.out.println(parnerDTO);

	}
}
