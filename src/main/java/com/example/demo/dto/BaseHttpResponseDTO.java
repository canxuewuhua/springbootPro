package com.example.demo.dto;

import lombok.Data;

/**
 * 第三方公司 http返回 DTO
 */
@Data
public class BaseHttpResponseDTO {

    private String code;

    private String message;

    private String data;

    private String sign;

    private String encrypt;

    private String secretKey;

    private String responseId;

    public boolean checkSuccess() {
        return "0".equals(code);
    }
}
