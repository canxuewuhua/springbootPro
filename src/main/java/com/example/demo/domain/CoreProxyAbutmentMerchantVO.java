package com.example.demo.domain;

import java.util.Date;

public class CoreProxyAbutmentMerchantVO extends CoreProxyAbutmentMerchantVOKey {
    private String accessAppName;

    private String secretKey;

    private String useFlag;

    private Date useBeginDate;

    private Date useEndTime;

    private String abutmentName;

    private String abutmentTel;

    private String opNo;

    private String opName;

    private String brName;

    private Date lstModTime;

    private Date regDate;

    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;

    private String ext5;

    public String getAccessAppName() {
        return accessAppName;
    }

    public void setAccessAppName(String accessAppName) {
        this.accessAppName = accessAppName == null ? null : accessAppName.trim();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey == null ? null : secretKey.trim();
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag == null ? null : useFlag.trim();
    }

    public Date getUseBeginDate() {
        return useBeginDate;
    }

    public void setUseBeginDate(Date useBeginDate) {
        this.useBeginDate = useBeginDate;
    }

    public Date getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    public String getAbutmentName() {
        return abutmentName;
    }

    public void setAbutmentName(String abutmentName) {
        this.abutmentName = abutmentName == null ? null : abutmentName.trim();
    }

    public String getAbutmentTel() {
        return abutmentTel;
    }

    public void setAbutmentTel(String abutmentTel) {
        this.abutmentTel = abutmentTel == null ? null : abutmentTel.trim();
    }

    public String getOpNo() {
        return opNo;
    }

    public void setOpNo(String opNo) {
        this.opNo = opNo == null ? null : opNo.trim();
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getBrName() {
        return brName;
    }

    public void setBrName(String brName) {
        this.brName = brName == null ? null : brName.trim();
    }

    public Date getLstModTime() {
        return lstModTime;
    }

    public void setLstModTime(Date lstModTime) {
        this.lstModTime = lstModTime;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
    }
}