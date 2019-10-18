package com.example.demo.test.listmap.controller;

import com.example.demo.common.ResultDTO;
import com.example.demo.test.listmap.service.TongDunListMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongqiang.zhu
 * @date 2019/10/18 20:01
 */
@RestController
@RequestMapping("/risk")
public class ListMapController {

	@Autowired
	private TongDunListMapService tongDunListMapService;

	@RequestMapping(name = "同盾风控数据获取", path = "/getUser",method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO getTongDunRiskRecordList(){
		return tongDunListMapService.getUserList();
	}

}
