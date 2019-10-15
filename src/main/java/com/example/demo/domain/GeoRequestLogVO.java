package com.example.demo.domain;

public class GeoRequestLogVO {
    private Long id;

    private String requestId;

    private String innerIfType;

    private String userId;

    private Integer code;

    private Long createTime;

    private String action;

    private Integer actionCount;

    private String phoneOperator;

    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getInnerIfType() {
        return innerIfType;
    }

    public void setInnerIfType(String innerIfType) {
        this.innerIfType = innerIfType == null ? null : innerIfType.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Integer getActionCount() {
        return actionCount;
    }

    public void setActionCount(Integer actionCount) {
        this.actionCount = actionCount;
    }

    public String getPhoneOperator() {
        return phoneOperator;
    }

    public void setPhoneOperator(String phoneOperator) {
        this.phoneOperator = phoneOperator == null ? null : phoneOperator.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}