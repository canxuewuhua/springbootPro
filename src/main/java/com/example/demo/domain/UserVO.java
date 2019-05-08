package com.example.demo.domain;

public class UserVO {
    private Integer stId;

    private String stName;

    private String stAddress;

    private Integer stAge;

    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName == null ? null : stName.trim();
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress == null ? null : stAddress.trim();
    }

    public Integer getStAge() {
        return stAge;
    }

    public void setStAge(Integer stAge) {
        this.stAge = stAge;
    }
}