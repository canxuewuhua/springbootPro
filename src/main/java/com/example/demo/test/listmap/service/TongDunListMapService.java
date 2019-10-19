package com.example.demo.test.listmap.service;

import com.example.demo.common.ResultDTO;
import com.example.demo.mapper.UserDAO;
import com.example.demo.test.listmap.dto.TongDunRequestDTO;
import com.example.demo.test.listmap.dto.TongdunFromMiDTO;
import com.example.demo.util.AESUtils;
import com.example.demo.util.RSASignature;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yongqiang.zhu
 * @date 2019/10/18 20:04
 */
@Service
@Transactional
@Slf4j
public class TongDunListMapService {


	@Autowired
	private UserDAO userDAO;

	private static final Map<String, String> requestMap = new HashMap<>();

	private static final String PARTNER_PRI_KEY = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAMUZZhxb2q+SB2AlAYSir6WvnhvHk3aBkY/6CMZ/6tC8Mv2FaqtvccZNWEYJL1oqWc05FVf7yIBwgZciwzGjPoE183xSDFsGzv/sRxB+zET9Q/lh8g1NxrbBngv2BujzwLfFRDvRM+iXG9nsQX+3zn4ghazVBUlae0NzcU7bJC/dAgMBAAECgYEAjeBdj1ZTUYRVSND6icYtl5+VuTttG6Xi6Pe7r19O4NhIABQ0l5kOFgeA3lEoQ8gugjpv8bhtOH9D2U4NocJ3b3iaxkGgaD6OzumYWcvk2HcqfFs+VAON0SFotgUH9za+wMXeM6U6ns8VR0wmNCxopAsT56RR9wccGS3SjxSvAAUCQQD0shU75amxpakea1/nlRCWqsx64XcQQn8hPBQs/jKIIOgiWMJFO1p9WhNJAKYkur4Bye148waMtt3pyrkgwHSHAkEAzjRpgpIxxQqd1KwsHtxnoh4aRkgcSJDie1ubCQIiW2usxcUE44RRKVYT1vSM1ANMy6AP/2zRdp2Aqmxj+GH1ewJBAOtiP6DdvU5hYH0dpyT7tPhqlscCCmm+vdJ3m6ToZi2jEgqwPTkh7ls1AeYw1KHybYME/wZhKYTFCFW0qD9EQxMCQQCCHZx+YdW56isRqdrlVlqmd6xIsPP37kSbZoB7vLcFTPpmiR2+mx3Doac/Om0q0zJAQy4VFQtPd69a2q5yaw3FAkEAjLppZUeDR7GtQAc/e4jAHvIVybB8tXJw9A2l/iCiIyuU4Tdy/rD4ovp+mv5LxCqu1BwlY51fvgoXQLNjjlU9fQ==";

	public ResultDTO getUserList(){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		TongDunRequestDTO tongDunRequestDTO1 =new TongDunRequestDTO();
		tongDunRequestDTO1.setName("张三");
		tongDunRequestDTO1.setAge(25);
		List<Map<String, Object>> mapBasicEditionList = userDAO.selectTongDunRecord(tongDunRequestDTO1);
		for (Map<String, Object> map : mapBasicEditionList) {
			map.put("productCode", "tongdunjichu");
			map.put("productName", "同盾基础分");
		}
		log.info("mapBasicEditionList：{}",mapBasicEditionList);
		mapList.addAll(mapBasicEditionList);
		TongDunRequestDTO tongDunRequestDTO3 =new TongDunRequestDTO();
		tongDunRequestDTO3.setName("李四");
		tongDunRequestDTO3.setAge(24);
		List<Map<String, Object>> mapCreditAuditBeforeListFlag = userDAO.selectTongDunRecord(tongDunRequestDTO3);
		log.info("mapCreditAuditBeforeListFlag：{}",mapCreditAuditBeforeListFlag);
		TongDunRequestDTO tongDunRequestDTO2 =new TongDunRequestDTO();
		tongDunRequestDTO2.setName("张三");
		tongDunRequestDTO2.setAge(25);
		List<Map<String, Object>> mapCreditAuditBeforeList = userDAO.selectTongDunRecord(tongDunRequestDTO2);
		log.info("mapCreditAuditBeforeList：{}",mapCreditAuditBeforeList);
		for (Map<String, Object> map : mapCreditAuditBeforeList) {
			map.put("productCode", "tongdunshenhe");
			map.put("productName", "贷前审核");
		}
		log.info("mapCreditAuditBeforeList组装后的：{}",mapCreditAuditBeforeList);
		mapList.addAll(mapCreditAuditBeforeList);
		return ResultUtils.success();
	}

	public ResultDTO getUserInfo(TongdunFromMiDTO tongdunFromMiDTO) throws Exception {
		// 验签成功
		System.out.println("验签成功,继续后续操作");
		// 解密
		String encryptKey = tongdunFromMiDTO.getEncryptKey();
		// 使⽤被调用方的RSA私钥进⾏解密得到原始AES KEY
		byte[] key = RSASignature.rsaDecode(encryptKey, RSASignature.getPrivateKey(PARTNER_PRI_KEY));

		// 使⽤AES KEY进⾏解密，得到业务数据的原始json字符串
		String encryptData = tongdunFromMiDTO.getEncryptData();
		String decodeData = AESUtils.decrypt(encryptData, new String(key, "utf-8"));
		System.out.println("业务数据原始json串 ：" + decodeData);

		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		TongDunRequestDTO tongDunRequestDTO1 =new TongDunRequestDTO();
		tongDunRequestDTO1.setName("张三");
		tongDunRequestDTO1.setAge(25);
		List<Map<String, Object>> mapTongDunList = userDAO.selectTongDunRecord(tongDunRequestDTO1);

		List<Map<String, Object>> mapBasicEditionList = new ArrayList<>();
		List<Map<String, Object>> mapCreditAuditBeforeList = new ArrayList<>();


		for (Map<String, Object> map : mapTongDunList) {
			Map<String, Object> mapa = new HashMap<>();
			mapa.putAll(map);
			mapBasicEditionList.add(mapa);
		}
		for (Map<String, Object> map : mapBasicEditionList) {
			map.put("productCode", "tongdunjichu");
			map.put("productName", "同盾基础分");
		}

		log.info("mapTongDunList：{}",mapTongDunList);
		for (Map<String, Object> map : mapTongDunList) {
			Map<String, Object> mapb = new HashMap<>();
			mapb.putAll(map);
			mapCreditAuditBeforeList.add(mapb);
		}
		for (Map<String, Object> map : mapCreditAuditBeforeList) {
			map.put("productCode", "tongdunshenhe");
			map.put("productName", "贷前审核");
		}
		log.info("mapBasicEditionList：{}",mapBasicEditionList);
		log.info("mapCreditAuditBeforeList：{}",mapCreditAuditBeforeList);

		mapList.addAll(mapBasicEditionList);
		mapList.addAll(mapCreditAuditBeforeList);
		log.info("mapList：{}",mapList);
		return ResultUtils.success();
	}
}
