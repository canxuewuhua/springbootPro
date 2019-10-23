package com.example.demo.controller;

import com.example.demo.common.OnlineApi;
import com.example.demo.common.ResultDTO;
import com.example.demo.test.verifyFile.VerifyAccountOuterFileFormatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/management/verify/file")
@Validated
@Api(value = "校验外部文件模块", description = "校验外部文件模块", tags = {"verifyOuterFile"})
public class VerifyFileFormatController {

    @Autowired
    private VerifyAccountOuterFileFormatService verifyAccountOuterFileFormatService;
    /**
     *  校验外部文件内容信息
     */
    @OnlineApi
    @ApiOperation(value="校验外部文件内容信息", notes="校验外部文件内容信息")
    @RequestMapping(name = "校验外部文件内容信息", path = "/verifyOuterFileInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO verifyOuterFileInfo(@RequestParam("file") MultipartFile file) throws Exception {
        return verifyAccountOuterFileFormatService.getOuterAccountFileInfo(file);
    }
}
