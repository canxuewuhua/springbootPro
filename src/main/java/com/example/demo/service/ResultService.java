package com.example.demo.service;

import com.example.demo.common.CodeMsg;
import com.example.demo.common.ResultDTO;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *  对返回结果进行处理成功和失败的返回处理
 */
@Service
@Slf4j
public class ResultService {

	public ResultDTO process(String message) {
		if (message.equals("SUDO")){
			return ResultUtils.success();
		}else{
			return ResultUtils.fail(CodeMsg.SYSTEM_REQUEST_FAIL);
		}
	}
}
