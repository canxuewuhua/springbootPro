package com.example.demo.util;


import com.example.demo.common.CodeMsg;
import com.example.demo.common.ResultDTO;

public class ResultUtils {

    public static ResultDTO success(Object data) {
        return new ResultDTO(data);
    }

    public static ResultDTO success() {
        return new ResultDTO();
    }

    public static ResultDTO fail(int code, String msg, Object data) {
        return new ResultDTO(code, msg, data);
    }

    public static ResultDTO fail(int code, String msg) {
        return fail(code, msg, null);
    }

    public static ResultDTO fail(CodeMsg codeMsg) {
        return fail(codeMsg.getErrorCode(), codeMsg.getErrorMsg(), null);
    }

    public static ResultDTO fail(CodeMsg codeMsg, Object data) {
        return fail(codeMsg.getErrorCode(), codeMsg.getErrorMsg(), data);
    }
}
