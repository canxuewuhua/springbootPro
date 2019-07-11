package com.example.demo.controller;

import com.example.demo.common.OnlineApi;
import com.example.demo.common.ResultDTO;
import com.example.demo.service.CityService;
import com.example.demo.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongqiang.zhu
 * @date 2019/5/3 9:55
 * 城市类相关操作
 */

@RestController
@RequestMapping("/project/city")
@Validated
@Api(value = "cityOperate", description = "城市类操作", tags = {"cityOperate"})
public class CityController {

	@Autowired
	private CityService cityService;

	/**
	 *  更改城市名称
	 * @return
	 */
	@OnlineApi
	@ApiOperation(value="更新城市名称", notes="更新城市名称")
	@RequestMapping(name = "更新城市名称", path = "/updateCityName",method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO updateCityName(){
		cityService.updateCityName();
		return ResultUtils.success();
	}
}
