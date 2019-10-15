package com.example.demo.controller;

import com.example.demo.common.ResultDTO;
import com.example.demo.service.GeoRequestLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/geo")
@Validated
@Api(value = "cityOperate", description = "集奥聚合操作", tags = {"cityOperate"})
public class GeoRequestLogController {

    @Autowired
    private GeoRequestLogService geoRequestLogService;

    @ApiOperation(value="批量插入集奥聚合数据", notes="批量插入集奥聚合数据")
    @RequestMapping(name = "批量插入集奥聚合数据", path = "/insertGeoRequestLog",method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO insertGeoRequestLog() throws InterruptedException {
        return geoRequestLogService.insertGeoRequestLogService();
    }
}
