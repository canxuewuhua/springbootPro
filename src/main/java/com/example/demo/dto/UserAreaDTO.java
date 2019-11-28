package com.example.demo.dto;

import lombok.Data;

/**
 * 用户居住地 省市区信息
 */
@Data
public class UserAreaDTO {

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 区编码
     */
    private String zoneCode;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区名称
     */
    private String zoneName;
}
