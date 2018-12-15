package com.example.demo.vo;

import lombok.Data;

@Data
public class BaseReturn {
    private int code;
    private String msg;
    private Object data;
    private String userFlag;
}
