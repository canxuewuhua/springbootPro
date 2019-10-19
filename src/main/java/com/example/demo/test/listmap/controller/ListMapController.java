package com.example.demo.test.listmap.controller;

import com.example.demo.common.ApiAuth;
import com.example.demo.common.ResultDTO;
import com.example.demo.test.listmap.dto.TongdunFromMiDTO;
import com.example.demo.test.listmap.service.TongDunListMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongqiang.zhu
 * @date 2019/10/18 20:01
 */
@RestController
@RequestMapping("/v1/risk")
@ApiAuth(value = "MI_PAY_LOAN")
public class ListMapController {

	@Autowired
	private TongDunListMapService tongDunListMapService;

	@RequestMapping(name = "同盾风控数据获取", path = "/getUser",method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO getTongDunRiskRecordList(@ModelAttribute @RequestBody TongdunFromMiDTO tongdunFromMiDTO) throws Exception {
		return tongDunListMapService.getUserInfo(tongdunFromMiDTO);
	}

}
