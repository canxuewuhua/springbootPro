package com.example.demo.controller;

import com.example.demo.common.OnlineApi;
import com.example.demo.common.ResultDTO;
import com.example.demo.service.ResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 对结果返回值进行处理
 */
@RestController
@RequestMapping("/process/v1")
@Api(value = "结果处理", description = "结果处理", tags={"result process"})
public class ResultController {

	@Autowired
	private ResultService resultService;

	@OnlineApi
	@ApiOperation(value="结果处理", notes="结果处理")
	@RequestMapping(name = "结果处理", path = "/process", method = RequestMethod.POST)
	public ResultDTO process(String mess){
		return resultService.process(mess);
	}
}
