package com.example.demo.test.sign;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.BaseHttpRequestDTO;
import com.example.demo.dto.BaseHttpResponseDTO;
import com.example.demo.util.*;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;


/**
 * @author yongqiang.zhu
 * @date 2019/10/17 21:54
 */
public class SignTest {

	private static final String MARKET_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUv5XfuxjUtRC9uRNIsJ8PkaaV+x4RBqckch3M9GrBb6frtN/+4cJ45KsyVxZpLnSv6QwIxWXvKek0XhERMgFT90NBl+9ZL9PT+bMAOLE8B/KZrHEtmsTUjqNmY3ejP1iycDuBpdMkW6dYg52XHYI7GCgbhJf+HhiiCrFl+YT7eQIDAQAB";

	private static final String MARKET_PRI_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANS/ld+7GNS1EL25E0iwnw+RppX7HhEGpyRyHcz0asFvp+u03/7hwnjkqzJXFmkudK/pDAjFZe8p6TReEREyAVP3Q0GX71kv09P5swA4sTwH8pmscS2axNSOo2Zjd6M/WLJwO4Gl0yRbp1iDnZcdgjsYKBuEl/4eGKIKsWX5hPt5AgMBAAECgYAjxtggWKlTU+Ot1ArPJYyKJLc774/d0x2Wfm5OgifVwb4eYDtu9G4swF+dxpFpPLu4vVsdunxq3MRv3wXFu4AHXQDvruK4he8whzp5OsWbC75EbSmjkfNVFQgNZ8LqI5dsV4ZCKfZhFWj2HY/gb+6mfU2eOdDJmRu96jos2ihUFQJBAP2h4juhV8IWZoJIvWD+iln0AGeGWgoHC3Prgt9raNOjtUsF2gySVSos9AzNUNLiQQ4FpUFd6Q7U4dRBqisbKNsCQQDWu//fcVmEygelkeDKNMb41D/aaeRSlZ94fPiuYrum1Vj35nnzImb+4rjdKHMUjBVbdGY/iIPQog65rLrA2AM7AkEA7crM1KNcJ/fge92+0CVViSifCHCKEkSwlCoGNXn6ehJOctw1pvu7JSOoXChS0qSV4ZOhcDufuewVHm/CH1ADaQJAd63ECBa2m8m1xVOug0Qy+Q/inqqR9G0rug181ER27Z7OvSuZICc2XCykd65JpVQFuzj+m75MKQWJS3a3hy9dbQJAJFEZpq5H9RAVvRWSPyVxAh+3UtP+9/AZ/ZntaR4DSKj9KazJvLERPUPWofsycS/MdI7kxxxqCRbSaELtmE5hOw==";

	private static final String PARTNER_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFGWYcW9qvkgdgJQGEoq+lr54bx5N2gZGP+gjGf+rQvDL9hWqrb3HGTVhGCS9aKlnNORVX+8iAcIGXIsMxoz6BNfN8UgxbBs7/7EcQfsxE/UP5YfINTca2wZ4L9gbo88C3xUQ70TPolxvZ7EF/t85+IIWs1QVJWntDc3FO2yQv3QIDAQAB";

	private static final String PARTNER_PRI_KEY = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAMUZZhxb2q+SB2AlAYSir6WvnhvHk3aBkY/6CMZ/6tC8Mv2FaqtvccZNWEYJL1oqWc05FVf7yIBwgZciwzGjPoE183xSDFsGzv/sRxB+zET9Q/lh8g1NxrbBngv2BujzwLfFRDvRM+iXG9nsQX+3zn4ghazVBUlae0NzcU7bJC/dAgMBAAECgYEAjeBdj1ZTUYRVSND6icYtl5+VuTttG6Xi6Pe7r19O4NhIABQ0l5kOFgeA3lEoQ8gugjpv8bhtOH9D2U4NocJ3b3iaxkGgaD6OzumYWcvk2HcqfFs+VAON0SFotgUH9za+wMXeM6U6ns8VR0wmNCxopAsT56RR9wccGS3SjxSvAAUCQQD0shU75amxpakea1/nlRCWqsx64XcQQn8hPBQs/jKIIOgiWMJFO1p9WhNJAKYkur4Bye148waMtt3pyrkgwHSHAkEAzjRpgpIxxQqd1KwsHtxnoh4aRkgcSJDie1ubCQIiW2usxcUE44RRKVYT1vSM1ANMy6AP/2zRdp2Aqmxj+GH1ewJBAOtiP6DdvU5hYH0dpyT7tPhqlscCCmm+vdJ3m6ToZi2jEgqwPTkh7ls1AeYw1KHybYME/wZhKYTFCFW0qD9EQxMCQQCCHZx+YdW56isRqdrlVlqmd6xIsPP37kSbZoB7vLcFTPpmiR2+mx3Doac/Om0q0zJAQy4VFQtPd69a2q5yaw3FAkEAjLppZUeDR7GtQAc/e4jAHvIVybB8tXJw9A2l/iCiIyuU4Tdy/rD4ovp+mv5LxCqu1BwlY51fvgoXQLNjjlU9fQ==";

	private static final String THIRD_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk4S0JiqMS3l2NxqNr-3p6uL4HmjAPhnlSIMD1jwr8T86rbrtr4FqRLChUuoJtpJqjh44yhcVRgqJtvfn6XSHu6drDG59sUl-eTHwA6A3_601bSVExaOylxvJkdYgUJAq7sfN6HGTKBJf4GYbykUDNRgf7ySXx4mi7fPIVwlM3aBbyseVKwLEk9893EbNFPijdGG2U72RoyCx7y0N6HYqq6huSuUrMwgcOOGyLYWbNl1AaKKnMJMP3qvx0qHULpX3LzLBvNcAHSIjdr-hH84RkFPavXWk9LiD4hx64FpZmtxsDJ-I5RAAJ5IFH01ztI-Isp6TVDbGzZ0vAKcWj0vy8wIDAQAB";

	private static final String JRR_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqwfWwZljCk1xVHADl1eB27tLvXqMc1uVPuYOTeVkk0X96ll_gDCevwB3DDploVyNisDvQ0vaCa-6rH12wfWSeuCs6LiRQ2i3Q0Iqr6HjrmWG61MGhACHfjdmu3QD03WLk-YCU5mg7aJ3Jr-4UP5rA_iGm3O6zThpmBjQBPLZJVo9FBSF-D_tiPqYIKxCqRwJScarrT5-1Kvbj0RXkfodom0VviE4h-XcHtHnJbhD1KiEFwrS520EpZhMcFkbcc3IGqPxBnGqq81a6xdSQq8bLlU5bG8Gp3amGa7wtrFo1hCCzKkAIS_8pa8Cr4Pr2pav1nXIl-pHyTRaGeaLn1QOHQIDAQAB";

	private static final String JRR_PRI_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCrB9bBmWMKTXFUcAOXV4Hbu0u9eoxzW5U-5g5N5WSTRf3qWX-AMJ6_AHcMOmWhXI2KwO9DS9oJr7qsfXbB9ZJ64KzouJFDaLdDQiqvoeOuZYbrUwaEAId-N2a7dAPTdYuT5gJTmaDtoncmv7hQ_msD-Iabc7rNOGmYGNAE8tklWj0UFIX4P-2I-pggrEKpHAlJxqutPn7Uq9uPRFeR-h2ibRW-ITiH5dwe0ecluEPUqIQXCtLnbQSlmExwWRtxzcgao_EGcaqrzVrrF1JCrxsuVTlsbwandqYZrvC2sWjWEILMqQAhL_ylrwKvg-valq_WdciX6kfJNFoZ5oufVA4dAgMBAAECggEALwQYpV_lOg_P98ZTNmd360-xooTt10h13rSfegp1e4biJmo0Aqla-mbGUzY6egFXZ61iGL3hUohcaM6Utv0rJ1xcLB2Q0VcYCgVGdsGqNWbzj_1i7Sa4GJpiQ2XBJ4BIvn-cZB73flwLJTvPD3ThNlY7qsBFueSoIoevp6yaxQl2ZFR9SRutkZjJ0UCdHtnb1NwvqppmPlfuspZfC1NcLLXaUOceliVUnLIpeX3kgGP41dgHYSucTwIgUwF2dXgxBabwCMtK9IRP3cY-U7_NWODdjBm2WoW6FyLf6oO_VSq37Q400sPBl59FYkn6QDwdcIgJ5Ahvy2Jyp-yr4uAIAQKBgQDYh6_MHSiwpy7CygymEWBNZP0uruI_qkOyLohLy46TME1XrF9NuD4snoOkoxdK3QjvHOBusNsv1hkcWoVfRO9w-7sZdNmjFklzXykPcwi3B6jPvo-FvNnMB05bpskIKRZEraT3lVdxO6Fu4n8SijoBUeJL0Ppyqi4dQc5uHFUfkQKBgQDKNO9jfuKf0cqIzhAsvg6Q8S11ny79yn1Y-P1F9BV-n1apgvWIoiDwfthnLQoJ0NpV3F644qI8j2fHqDei1yzpkSe8-OD08ZqT2dN9GVbNStkcMdy9j0W1vWPkchKGhF2jLtfmbfDPOzS5hPT7uf2uVpKoQebpTSUvgp5tUbfXzQKBgA99JUYkj9STIPUuHtmCRLFEmBfOysBjHZSRX46McRZ2ThIHconMtCXc8O3F_NSOGyV_m05PuF4joog0sXWvKLGcUDXx9d-EqJxoIjfIssbk9V5m8z1N1QaTPzevJpI-3ijrgvFy7V5cNaAI864P1TZi9R0XRdMN-V5-ZpXZ0P0BAoGAJqKaot4ZmwPQuPb2bDYcPmF2mCTEK8XG0XsxmW4btUfonRbipyqjNJBn9L7MTjfTn9gtWRIkgYG9CI8BomGOuUn818SN5v9K07kpCZwP08XbWeHFFYsMfL8lGP8avw0HfyhyJBsZid78If5feMF-0RLFjtSYtDcDReKlNqoIgHkCgYB6ErH9N10_hojkxlqkdqHVlq2ZJV4XTFSuXfvc-EtZnV65BGpDxy2M2_SsbZ4CONL89c32IKREs6R0jATKK1OobCHAAqQlLeyxYBtcCKimlkOjvoP3HLi-6gdSuvqFf0-zxz1PgH-80nybymZwQDU3JA2DJmCjuNs5JI04GNPvwg";

	private static final String THIRD_API_URL_DOMAIN = "http://yuangong.com/api";

	private static final String APPID = "jrr";

	/**
	 * 请求参数map
	 * merchantNo  商户号  平台方提供
	 * requestId   每次请求的唯⼀标示ID
	 * encryptKey  AES KEY 使用商户公钥加密
	 * encryptData 调⽤接⼝的参数，json格式，AES原串加密
	 * timestamp   请求发送时的时间戳，精确到毫秒的Unix时间戳
	 * sign         签名字符串，使用平台RSA私钥签名
	 */
	private static final Map<String, String> requestMap = new HashMap<>();

	public static void testEncrypt() throws Exception {
		// 平台调用商户接口
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("phone", "13200001111");
		paramMap.put("idNo", "513436200004299933");
		paramMap.put("name", "员工贷");
		paramMap.put("openId", "1234567890");
		paramMap.put("notifyUrl", "https://yuangong.com/");

		// 随机⽣成16位AES KEY字符串
		String aesKey = AESUtils.getKey(16);
		// 对业务参数转换为json字符串
		String paramJsonStr = JSON.toJSONString(paramMap);
		// json字符串使用AES加密并进⾏Base64编码
		String encryptData = AESUtils.encrypt(paramJsonStr, aesKey);
		// 使用商户公钥（被调用方公钥）加密AES KEY
		String encryptKey = RSASignature.rsaEncode(aesKey.getBytes("utf-8"), RSASignature.getPublicKey(PARTNER_PUB_KEY));

		requestMap.put("partnerCode", "100001");
		requestMap.put("requestId", "20190425103326");
		requestMap.put("encryptKey", encryptKey);
		requestMap.put("encryptData", encryptData);
		requestMap.put("version", "1.0");
		requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));

		// 加签：生成待签名的字符串 系统参数
		String signContent = RSASignature.getSignContent(requestMap);
		// 加签：使用平台私钥（调用方私钥）加密
		String sign = RSASignature.signByPrivateKey(signContent, MARKET_PRI_KEY);
		requestMap.put("sign", sign);

		requestMap.forEach((k, v) -> {
			System.out.println("k -> " + k + " v -> " + v);
		});

		// 发送请求
		// String response = HttpClientUtil.sendPostJson(requestMap, "url");
	}

	/**
	 * 验签-解密
	 */
	public static void testDecrypt() throws Exception {
		testEncrypt();

		// 验签：取出签名字段sign的值
		String signOrigin = requestMap.get("sign");
		// 验签：使用调用方公钥 利⽤SHA256WithRSA算法进⾏验签操作
		String signContent = RSASignature.getSignContent(requestMap);

		boolean verifyResult = RSASignature.verifySignByPublicKey(signContent, signOrigin, MARKET_PUB_KEY);
		if (!verifyResult) {
			System.out.println("验签失败, params = " + requestMap);
			return;
		}
		// 验签成功
		System.out.println("验签成功,继续后续操作");

		// 解密
		String encryptKey = requestMap.get("encryptKey");
		// 使⽤被调用方的RSA私钥进⾏解密得到原始AES KEY
		byte[] key = RSASignature.rsaDecode(encryptKey, RSASignature.getPrivateKey(PARTNER_PRI_KEY));

		// 使⽤AES KEY进⾏解密，得到业务数据的原始json字符串
		String encryptData = requestMap.get("encryptData");
		String decodeData = AESUtils.decrypt(encryptData, new String(key, "utf-8"));
		System.out.println("业务数据原始json串 ：" + decodeData);
	}

	public static void genertor() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

		keyPairGenerator.initialize(1024);

		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		System.out.println("公钥："+new BASE64Encoder().encodeBuffer(publicKey.getEncoded()));

		System.out.println("私钥："+new BASE64Encoder().encodeBuffer(privateKey.getEncoded()));
	}

	/**
	 * 加密-加签
	 */
	public static void testTHIRD() throws Exception {

		// 平台调用某个商户接口
		Map<String, String> paramMap = new HashMap<>();
		String namecertNoMd5 = Md5Utils.getMD5Encrypt("刁穴滩"+"610426201102205600");
		String phoneMd5 = Md5Utils.getMD5Encrypt("13200001111");
		paramMap.put("md5", namecertNoMd5);
		paramMap.put("phoneMd5", phoneMd5);
		paramMap.put("serialNo", SerialNumberUtil.getRequestId());
		paramMap.put("rtApplyNo", "Y");

		// 随机⽣成AES KEY字符串
		String aesKey = AESThirdCompanyUtils.randomSecretKey();
		// 对业务参数转换为json字符串
		String paramJsonStr = JSON.toJSONString(paramMap);
		// json字符串使用AES加密并进⾏Base64编码
		String encryptData = AESThirdCompanyUtils.encrypt(paramJsonStr, aesKey);
		// 使用商户公钥（被调用方公钥）加密AES KEY
		String encryptKey = RSASignature.encryptByPublicKey(aesKey, THIRD_PUB_KEY);

		BaseHttpRequestDTO baseHttpRequestDTO = new BaseHttpRequestDTO();
		baseHttpRequestDTO.setAppid(APPID);
		baseHttpRequestDTO.setMethod("vopen.credit.apply.enable");
		baseHttpRequestDTO.setSecretKey(encryptKey);
		baseHttpRequestDTO.setData(encryptData);
		baseHttpRequestDTO.setRequestId(SerialNumberUtil.getRequestId());


		// 加签：生成待签名的字符串 系统参数

		String signContent = RSASignature.getSignContentWithSignType(baseHttpRequestDTO, true);
		System.out.println("参与签名的字符串：" + signContent);
		// 加签：使用平台私钥（调用方私钥）加密
		String sign = RSASignature.signByPrivateKey(signContent, JRR_PRI_KEY);
		baseHttpRequestDTO.setSign(sign);
		Map<String, String> requestMapMS = BeanUtil.beanToMapString(baseHttpRequestDTO);

		requestMapMS.forEach((k, v) -> {
			System.out.println("k -> " + k + " v -> " + v);
		});

		// 发送请求
		String response = HttpClientUtil.sendPostJson(requestMapMS, THIRD_API_URL_DOMAIN);

		if (StringUtils.isBlank(response)){
			System.out.println("第三方公司返回值为空");
			return;
		}

		System.out.println("第三方公司返回值：" + response);

		BaseHttpResponseDTO baseHttpResponseDTO = JSON.parseObject(response, BaseHttpResponseDTO.class);

		String originSign = baseHttpResponseDTO.getSign();

		String signContent1 = RSASignature.getSignContentWithSignType(baseHttpResponseDTO, true);

		boolean checkSign = RSASignature.verifySignByPublicKey(signContent1, originSign, THIRD_PUB_KEY);
		if (!checkSign){
			System.out.println("第三方公司返回值验签失败");
			return;
		}

		String secretKey = baseHttpResponseDTO.getSecretKey();

		String aesKeyResponse = RSASignature.decryptByPrivateKey(secretKey, JRR_PRI_KEY);
		System.out.println("第三方公司返回值，AES KEY：" + aesKeyResponse);

		String data = baseHttpResponseDTO.getData();
		String decrypt = AESThirdCompanyUtils.decrypt(data, aesKeyResponse);
		System.out.println("第三方公司返回值 业务参数：" + decrypt);
	}

	public static void main(String[] args) throws Exception {
//		testDecrypt();
		genertor();
	}
}
