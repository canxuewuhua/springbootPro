package com.example.demo.domain;

import com.example.demo.knowledge.zidingyi_interface.annotation.Encryption;

@Encryption({"customerName"})
public class ReportInoutPlanVO {
    private String custInoutPlanLedgerId;

    private String customerName;

    private String customerCode;

    public String getCustInoutPlanLedgerId() {
        return custInoutPlanLedgerId;
    }

    public void setCustInoutPlanLedgerId(String custInoutPlanLedgerId) {
        this.custInoutPlanLedgerId = custInoutPlanLedgerId == null ? null : custInoutPlanLedgerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }
}