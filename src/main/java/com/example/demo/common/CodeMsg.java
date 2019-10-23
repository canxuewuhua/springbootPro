
package com.example.demo.common;

public enum CodeMsg {
    /**
     * 标准格式为******，6位（状态码+模块码+顺序码）
     * 成功  1*
     * 失败  2*
     */
    // 全局错误码
    SYSTEM_REQUEST_SUCCESS(100000, "系统请求成功"),
    SYSTEM_REQUEST_FAIL(200000, "系统请求失败"),
    PARAMETER_ABSENT(200001, "缺少请求参数"),
    VERIFY_SIGN_FAILED(200002, "签名验证失败"),
    NOT_FIND_APP_ID(200003, "没有找到接入申请号"),
    ABUTMENT_MERCHANT_NOT_USE(200004, "接入申请号未使用"),
    ABUTMENT_MERCHANT_USE_TIME_ERROR(200005, "接入申请号使用时效错误"),
    SYSTEM_ACCESS_KEY_NOT_EXIST(200006, "ak不存在"),

    OVERDUE_BATCH_RUN_LOCKING(201001, "逾期跑批锁定中"),

    QUERY_PARTNER_SERVICE_FAILURE(300001, "商户标准为空，获取业务层数据失败"),
    SYSTEM_VERIFY_SIGN_FAIL(300002, "签名验证失败"),
    DATE_FORMAT_ERROR(300003, "日期格式错误."),

    // 对账文件格式
    ACCOUNT_OUTER_FILE_RETURN_ROWS_NULL(400001, "对账外部文件返回行数为零"),
    ACCOUNT_OUTER_FILE_SUFFIXNAME_UNKOWN(400002, "对账外部文件未知的后缀名"),;



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
