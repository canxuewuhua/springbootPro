package com.example.demo.domain;

public class CoreProxyAbutmentMerchantVOKey {
    private String accessAppId;

    private String accessKey;

    public String getAccessAppId() {
        return accessAppId;
    }

    public void setAccessAppId(String accessAppId) {
        this.accessAppId = accessAppId == null ? null : accessAppId.trim();
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey == null ? null : accessKey.trim();
    }
}