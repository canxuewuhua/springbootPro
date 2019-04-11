package com.example.demo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    public ResultDTO() {
        this.code = CodeMsg.SYSTEM_REQUEST_SUCCESS.getErrorCode();
        this.message = "成功";
    }

    public ResultDTO(Object data) {
        this.data = data;
        this.code = CodeMsg.SYSTEM_REQUEST_SUCCESS.getErrorCode();
        this.message = "成功";
    }

    public ResultDTO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean checkSuccess() {
        return CodeMsg.SYSTEM_REQUEST_SUCCESS.getErrorCode() == code;
    }
}
