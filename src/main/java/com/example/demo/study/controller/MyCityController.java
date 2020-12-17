package com.example.study.controller;

import com.example.demo.common.OnlineApi;
import com.example.demo.common.ResultDTO;
import com.example.demo.service.CityService;
import com.example.demo.util.ResultUtils;
import com.example.study.service.MyCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/my/city")
@Validated
@Api(value = "cityOperate", description = "城市类操作", tags = {"cityOperate"})
public class MyCityController {

    @Autowired
    private MyCityService myCityService;

    /**
     *  查询城市名称
     * @return
     */
    @OnlineApi
    @ApiOperation(value="查询城市名称", notes="查询城市名称")
    @RequestMapping(name = "查询城市名称", path = "/queryCityName",method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO queryCityName(){
        myCityService.queryCityName();
        return ResultUtils.success();
    }
}
