package com.example.demo.dto;

import lombok.Data;

/**
 * 第三方公司 http请求 DTO
 */
@Data
public class BaseHttpRequestDTO {

    private String appid;

    private String method;

    private String version = "4.0";

    private String timestamp = String.valueOf(System.currentTimeMillis());

    private String encrypt = "1";

    private String secretKey;

    private String data;

    private String requestId;

    private String signType = "SHA256WithRSA";

    private String sign;
}
