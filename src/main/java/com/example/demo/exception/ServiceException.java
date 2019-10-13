package com.example.demo.exception;

import com.example.demo.common.CodeMsg;
import lombok.Data;

@Data
public class ServiceException extends BusinessException{
    private int errorCode;

    public ServiceException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public ServiceException(CodeMsg codeMsg) {
        super(codeMsg.getErrorMsg());
        this.errorCode = codeMsg.getErrorCode();
    }
}
