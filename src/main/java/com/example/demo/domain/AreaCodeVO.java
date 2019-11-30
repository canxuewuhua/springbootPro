package com.example.demo.domain;

public class AreaCodeVO {
    private String areaCodeId;

    private String areaCode;

    private String areaName;

    private Integer areaLevel;

    private String parentCode;

    private String yearName;

    public String getAreaCodeId() {
        return areaCodeId;
    }

    public void setAreaCodeId(String areaCodeId) {
        this.areaCodeId = areaCodeId == null ? null : areaCodeId.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName == null ? null : yearName.trim();
    }
}