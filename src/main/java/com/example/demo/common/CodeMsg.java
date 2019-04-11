
package com.example.demo.common;

public enum CodeMsg {
    /**
     * 标准格式为******，6位（状态码+模块码+顺序码）
     * 成功  1*
     * 失败  2*
     */
    // 全局错误码
    SYSTEM_REQUEST_SUCCESS(100000, "系统请求成功"),
    SYSTEM_REQUEST_FAIL(200000, "系统请求失败");



    private int errorCode;
    private String errorMsg;

    CodeMsg(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

}
