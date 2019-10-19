package com.example.demo.service;

import com.example.demo.common.CodeMsg;
import com.example.demo.constant.RedisConstant;
import com.example.demo.domain.CoreProxyAbutmentMerchantVO;
import com.example.demo.domain.CoreProxyAbutmentMerchantVOExample;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.CoreProxyAbutmentMerchantDAO;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.RedisUtilService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yongqiang.zhu
 * @date 2019/10/19 10:29
 */
@Slf4j
@Service
public class AbutmentMerchantService {

	@Autowired
	private CoreProxyAbutmentMerchantDAO coreProxyAbutmentMerchantDao;

	@Autowired
	private RedisUtilService redisUtil;

	/**
	 * aksk缓存时间 7天
	 */
	public static final Long MD5_SIGN_AK_EXPIRE_SECONDS = 7 * 24 * 60 * 60L;

	public CoreProxyAbutmentMerchantVO getCoreProxyAbutmentMerchant(String accessAppId, String accessKey){

		String redisKey =  "core:proxy:abutment:merchant" +":"+accessAppId+":"+accessKey;

		Object abutmentMerObj =  redisUtil.get(redisKey);
		if (abutmentMerObj != null) {
			return JsonUtil.fromJsonO(String.valueOf(abutmentMerObj),CoreProxyAbutmentMerchantVO.class) ;
		}

		log.info("从缓存中没有查询到该对接商户，准备从数据库中查询, accessAppId={},accessKey={}", accessAppId,accessKey);

		CoreProxyAbutmentMerchantVO paramAbutmentMer = new CoreProxyAbutmentMerchantVO();
		paramAbutmentMer.setAccessAppId(accessAppId);
		paramAbutmentMer.setAccessKey(accessKey);

		CoreProxyAbutmentMerchantVO abutmentMerchant = coreProxyAbutmentMerchantDao.selectByPrimaryKey(paramAbutmentMer);

		if(null == abutmentMerchant){
			return null;
		}
		redisUtil.set(redisKey, JsonUtil.toJson(abutmentMerchant), RedisConstant.DAY_TIME, RedisConstant.TIME_UNIT);

		return abutmentMerchant;
	}

	public String getSkByAk(String ak) {

		if (StringUtils.isBlank(ak)) {
			log.error("通过ak查询sk失败，ak不能为空");
			throw new ServiceException(CodeMsg.SYSTEM_ACCESS_KEY_NOT_EXIST);
		}

		String redisKey = "md5:sign:ak:" + ak;
		String sk = (String) redisUtil.get(redisKey);
		if (StringUtils.isNotBlank(sk)) {
			log.debug("从缓存中读取sk成功, ak={}, sk={}", ak, sk);
			return sk;
		}
		log.debug("从缓存中读取sk失败，准备从数据库中查询");

		CoreProxyAbutmentMerchantVOExample example = new CoreProxyAbutmentMerchantVOExample();
		CoreProxyAbutmentMerchantVOExample.Criteria criteria = example.createCriteria();
		criteria.andAccessKeyEqualTo(ak);
		List<CoreProxyAbutmentMerchantVO> list = coreProxyAbutmentMerchantDao.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			log.error("数据库中查询不到该ak={}", ak);
			throw new ServiceException(CodeMsg.SYSTEM_ACCESS_KEY_NOT_EXIST);
		}
		CoreProxyAbutmentMerchantVO vo = list.get(0);
		sk = vo.getSecretKey();
		if (StringUtils.isEmpty(sk)) {
			log.error("sk为空，ak={}", ak);
			throw new ServiceException(CodeMsg.SYSTEM_ACCESS_KEY_NOT_EXIST);
		}
		// 将sk放入redis缓存
		redisUtil.set(redisKey, sk, MD5_SIGN_AK_EXPIRE_SECONDS, TimeUnit.SECONDS);
		log.debug("从数据库中读取sk成功，ak={}, sk={}", ak, sk);
		return sk;
	}
}
