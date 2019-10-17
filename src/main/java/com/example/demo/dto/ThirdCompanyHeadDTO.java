package com.example.demo.dto;

import lombok.Data;

@Data
public class ThirdCompanyHeadDTO {

    // 版本
    private String version;
    // 版本
    private String versionCode;
    // 渠道
    private String channel;
    // 平台 iOS Android web
    private String platform;
    // 设备型号
    private String deviceBrand;
    // 设备ID
    private String deviceID;
    // 设备名称
    private String deviceName;
    // APP名称
    private String appName;
    // HDID
    private String hdID;
    // IP
    private String ip;
    // 设备信息
    private String deviceinfo;
}
