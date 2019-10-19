package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoreProxyAbutmentMerchantVOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CoreProxyAbutmentMerchantVOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAccessAppIdIsNull() {
            addCriterion("access_app_id is null");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdIsNotNull() {
            addCriterion("access_app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdEqualTo(String value) {
            addCriterion("access_app_id =", value, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdNotEqualTo(String value) {
            addCriterion("access_app_id <>", value, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdGreaterThan(String value) {
            addCriterion("access_app_id >", value, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("access_app_id >=", value, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdLessThan(String value) {
            addCriterion("access_app_id <", value, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdLessThanOrEqualTo(String value) {
            addCriterion("access_app_id <=", value, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdLike(String value) {
            addCriterion("access_app_id like", value, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdNotLike(String value) {
            addCriterion("access_app_id not like", value, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdIn(List<String> values) {
            addCriterion("access_app_id in", values, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdNotIn(List<String> values) {
            addCriterion("access_app_id not in", values, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdBetween(String value1, String value2) {
            addCriterion("access_app_id between", value1, value2, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessAppIdNotBetween(String value1, String value2) {
            addCriterion("access_app_id not between", value1, value2, "accessAppId");
            return (Criteria) this;
        }

        public Criteria andAccessKeyIsNull() {
            addCriterion("access_key is null");
            return (Criteria) this;
        }

        public Criteria andAccessKeyIsNotNull() {
            addCriterion("access_key is not null");
            return (Criteria) this;
        }

        public Criteria andAccessKeyEqualTo(String value) {
            addCriterion("access_key =", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyNotEqualTo(String value) {
            addCriterion("access_key <>", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyGreaterThan(String value) {
            addCriterion("access_key >", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyGreaterThanOrEqualTo(String value) {
            addCriterion("access_key >=", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyLessThan(String value) {
            addCriterion("access_key <", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyLessThanOrEqualTo(String value) {
            addCriterion("access_key <=", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyLike(String value) {
            addCriterion("access_key like", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyNotLike(String value) {
            addCriterion("access_key not like", value, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyIn(List<String> values) {
            addCriterion("access_key in", values, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyNotIn(List<String> values) {
            addCriterion("access_key not in", values, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyBetween(String value1, String value2) {
            addCriterion("access_key between", value1, value2, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessKeyNotBetween(String value1, String value2) {
            addCriterion("access_key not between", value1, value2, "accessKey");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameIsNull() {
            addCriterion("access_app_name is null");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameIsNotNull() {
            addCriterion("access_app_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameEqualTo(String value) {
            addCriterion("access_app_name =", value, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameNotEqualTo(String value) {
            addCriterion("access_app_name <>", value, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameGreaterThan(String value) {
            addCriterion("access_app_name >", value, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameGreaterThanOrEqualTo(String value) {
            addCriterion("access_app_name >=", value, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameLessThan(String value) {
            addCriterion("access_app_name <", value, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameLessThanOrEqualTo(String value) {
            addCriterion("access_app_name <=", value, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameLike(String value) {
            addCriterion("access_app_name like", value, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameNotLike(String value) {
            addCriterion("access_app_name not like", value, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameIn(List<String> values) {
            addCriterion("access_app_name in", values, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameNotIn(List<String> values) {
            addCriterion("access_app_name not in", values, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameBetween(String value1, String value2) {
            addCriterion("access_app_name between", value1, value2, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andAccessAppNameNotBetween(String value1, String value2) {
            addCriterion("access_app_name not between", value1, value2, "accessAppName");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIsNull() {
            addCriterion("secret_key is null");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIsNotNull() {
            addCriterion("secret_key is not null");
            return (Criteria) this;
        }

        public Criteria andSecretKeyEqualTo(String value) {
            addCriterion("secret_key =", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotEqualTo(String value) {
            addCriterion("secret_key <>", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyGreaterThan(String value) {
            addCriterion("secret_key >", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyGreaterThanOrEqualTo(String value) {
            addCriterion("secret_key >=", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLessThan(String value) {
            addCriterion("secret_key <", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLessThanOrEqualTo(String value) {
            addCriterion("secret_key <=", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyLike(String value) {
            addCriterion("secret_key like", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotLike(String value) {
            addCriterion("secret_key not like", value, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyIn(List<String> values) {
            addCriterion("secret_key in", values, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotIn(List<String> values) {
            addCriterion("secret_key not in", values, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyBetween(String value1, String value2) {
            addCriterion("secret_key between", value1, value2, "secretKey");
            return (Criteria) this;
        }

        public Criteria andSecretKeyNotBetween(String value1, String value2) {
            addCriterion("secret_key not between", value1, value2, "secretKey");
            return (Criteria) this;
        }

        public Criteria andUseFlagIsNull() {
            addCriterion("use_flag is null");
            return (Criteria) this;
        }

        public Criteria andUseFlagIsNotNull() {
            addCriterion("use_flag is not null");
            return (Criteria) this;
        }

        public Criteria andUseFlagEqualTo(String value) {
            addCriterion("use_flag =", value, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagNotEqualTo(String value) {
            addCriterion("use_flag <>", value, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagGreaterThan(String value) {
            addCriterion("use_flag >", value, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagGreaterThanOrEqualTo(String value) {
            addCriterion("use_flag >=", value, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagLessThan(String value) {
            addCriterion("use_flag <", value, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagLessThanOrEqualTo(String value) {
            addCriterion("use_flag <=", value, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagLike(String value) {
            addCriterion("use_flag like", value, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagNotLike(String value) {
            addCriterion("use_flag not like", value, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagIn(List<String> values) {
            addCriterion("use_flag in", values, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagNotIn(List<String> values) {
            addCriterion("use_flag not in", values, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagBetween(String value1, String value2) {
            addCriterion("use_flag between", value1, value2, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseFlagNotBetween(String value1, String value2) {
            addCriterion("use_flag not between", value1, value2, "useFlag");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateIsNull() {
            addCriterion("use_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateIsNotNull() {
            addCriterion("use_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateEqualTo(Date value) {
            addCriterion("use_begin_date =", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateNotEqualTo(Date value) {
            addCriterion("use_begin_date <>", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateGreaterThan(Date value) {
            addCriterion("use_begin_date >", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("use_begin_date >=", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateLessThan(Date value) {
            addCriterion("use_begin_date <", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("use_begin_date <=", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateIn(List<Date> values) {
            addCriterion("use_begin_date in", values, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateNotIn(List<Date> values) {
            addCriterion("use_begin_date not in", values, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateBetween(Date value1, Date value2) {
            addCriterion("use_begin_date between", value1, value2, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("use_begin_date not between", value1, value2, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeIsNull() {
            addCriterion("use_end_time is null");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeIsNotNull() {
            addCriterion("use_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeEqualTo(Date value) {
            addCriterion("use_end_time =", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeNotEqualTo(Date value) {
            addCriterion("use_end_time <>", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeGreaterThan(Date value) {
            addCriterion("use_end_time >", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("use_end_time >=", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeLessThan(Date value) {
            addCriterion("use_end_time <", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("use_end_time <=", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeIn(List<Date> values) {
            addCriterion("use_end_time in", values, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeNotIn(List<Date> values) {
            addCriterion("use_end_time not in", values, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeBetween(Date value1, Date value2) {
            addCriterion("use_end_time between", value1, value2, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("use_end_time not between", value1, value2, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameIsNull() {
            addCriterion("abutment_name is null");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameIsNotNull() {
            addCriterion("abutment_name is not null");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameEqualTo(String value) {
            addCriterion("abutment_name =", value, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameNotEqualTo(String value) {
            addCriterion("abutment_name <>", value, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameGreaterThan(String value) {
            addCriterion("abutment_name >", value, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("abutment_name >=", value, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameLessThan(String value) {
            addCriterion("abutment_name <", value, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameLessThanOrEqualTo(String value) {
            addCriterion("abutment_name <=", value, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameLike(String value) {
            addCriterion("abutment_name like", value, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameNotLike(String value) {
            addCriterion("abutment_name not like", value, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameIn(List<String> values) {
            addCriterion("abutment_name in", values, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameNotIn(List<String> values) {
            addCriterion("abutment_name not in", values, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameBetween(String value1, String value2) {
            addCriterion("abutment_name between", value1, value2, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentNameNotBetween(String value1, String value2) {
            addCriterion("abutment_name not between", value1, value2, "abutmentName");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelIsNull() {
            addCriterion("abutment_tel is null");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelIsNotNull() {
            addCriterion("abutment_tel is not null");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelEqualTo(String value) {
            addCriterion("abutment_tel =", value, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelNotEqualTo(String value) {
            addCriterion("abutment_tel <>", value, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelGreaterThan(String value) {
            addCriterion("abutment_tel >", value, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelGreaterThanOrEqualTo(String value) {
            addCriterion("abutment_tel >=", value, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelLessThan(String value) {
            addCriterion("abutment_tel <", value, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelLessThanOrEqualTo(String value) {
            addCriterion("abutment_tel <=", value, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelLike(String value) {
            addCriterion("abutment_tel like", value, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelNotLike(String value) {
            addCriterion("abutment_tel not like", value, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelIn(List<String> values) {
            addCriterion("abutment_tel in", values, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelNotIn(List<String> values) {
            addCriterion("abutment_tel not in", values, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelBetween(String value1, String value2) {
            addCriterion("abutment_tel between", value1, value2, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andAbutmentTelNotBetween(String value1, String value2) {
            addCriterion("abutment_tel not between", value1, value2, "abutmentTel");
            return (Criteria) this;
        }

        public Criteria andOpNoIsNull() {
            addCriterion("op_no is null");
            return (Criteria) this;
        }

        public Criteria andOpNoIsNotNull() {
            addCriterion("op_no is not null");
            return (Criteria) this;
        }

        public Criteria andOpNoEqualTo(String value) {
            addCriterion("op_no =", value, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoNotEqualTo(String value) {
            addCriterion("op_no <>", value, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoGreaterThan(String value) {
            addCriterion("op_no >", value, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoGreaterThanOrEqualTo(String value) {
            addCriterion("op_no >=", value, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoLessThan(String value) {
            addCriterion("op_no <", value, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoLessThanOrEqualTo(String value) {
            addCriterion("op_no <=", value, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoLike(String value) {
            addCriterion("op_no like", value, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoNotLike(String value) {
            addCriterion("op_no not like", value, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoIn(List<String> values) {
            addCriterion("op_no in", values, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoNotIn(List<String> values) {
            addCriterion("op_no not in", values, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoBetween(String value1, String value2) {
            addCriterion("op_no between", value1, value2, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNoNotBetween(String value1, String value2) {
            addCriterion("op_no not between", value1, value2, "opNo");
            return (Criteria) this;
        }

        public Criteria andOpNameIsNull() {
            addCriterion("op_name is null");
            return (Criteria) this;
        }

        public Criteria andOpNameIsNotNull() {
            addCriterion("op_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpNameEqualTo(String value) {
            addCriterion("op_name =", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotEqualTo(String value) {
            addCriterion("op_name <>", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameGreaterThan(String value) {
            addCriterion("op_name >", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameGreaterThanOrEqualTo(String value) {
            addCriterion("op_name >=", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLessThan(String value) {
            addCriterion("op_name <", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLessThanOrEqualTo(String value) {
            addCriterion("op_name <=", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLike(String value) {
            addCriterion("op_name like", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotLike(String value) {
            addCriterion("op_name not like", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameIn(List<String> values) {
            addCriterion("op_name in", values, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotIn(List<String> values) {
            addCriterion("op_name not in", values, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameBetween(String value1, String value2) {
            addCriterion("op_name between", value1, value2, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotBetween(String value1, String value2) {
            addCriterion("op_name not between", value1, value2, "opName");
            return (Criteria) this;
        }

        public Criteria andBrNameIsNull() {
            addCriterion("br_name is null");
            return (Criteria) this;
        }

        public Criteria andBrNameIsNotNull() {
            addCriterion("br_name is not null");
            return (Criteria) this;
        }

        public Criteria andBrNameEqualTo(String value) {
            addCriterion("br_name =", value, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameNotEqualTo(String value) {
            addCriterion("br_name <>", value, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameGreaterThan(String value) {
            addCriterion("br_name >", value, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameGreaterThanOrEqualTo(String value) {
            addCriterion("br_name >=", value, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameLessThan(String value) {
            addCriterion("br_name <", value, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameLessThanOrEqualTo(String value) {
            addCriterion("br_name <=", value, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameLike(String value) {
            addCriterion("br_name like", value, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameNotLike(String value) {
            addCriterion("br_name not like", value, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameIn(List<String> values) {
            addCriterion("br_name in", values, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameNotIn(List<String> values) {
            addCriterion("br_name not in", values, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameBetween(String value1, String value2) {
            addCriterion("br_name between", value1, value2, "brName");
            return (Criteria) this;
        }

        public Criteria andBrNameNotBetween(String value1, String value2) {
            addCriterion("br_name not between", value1, value2, "brName");
            return (Criteria) this;
        }

        public Criteria andLstModTimeIsNull() {
            addCriterion("lst_mod_time is null");
            return (Criteria) this;
        }

        public Criteria andLstModTimeIsNotNull() {
            addCriterion("lst_mod_time is not null");
            return (Criteria) this;
        }

        public Criteria andLstModTimeEqualTo(Date value) {
            addCriterion("lst_mod_time =", value, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeNotEqualTo(Date value) {
            addCriterion("lst_mod_time <>", value, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeGreaterThan(Date value) {
            addCriterion("lst_mod_time >", value, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lst_mod_time >=", value, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeLessThan(Date value) {
            addCriterion("lst_mod_time <", value, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeLessThanOrEqualTo(Date value) {
            addCriterion("lst_mod_time <=", value, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeIn(List<Date> values) {
            addCriterion("lst_mod_time in", values, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeNotIn(List<Date> values) {
            addCriterion("lst_mod_time not in", values, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeBetween(Date value1, Date value2) {
            addCriterion("lst_mod_time between", value1, value2, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andLstModTimeNotBetween(Date value1, Date value2) {
            addCriterion("lst_mod_time not between", value1, value2, "lstModTime");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNull() {
            addCriterion("reg_date is null");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNotNull() {
            addCriterion("reg_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegDateEqualTo(Date value) {
            addCriterion("reg_date =", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotEqualTo(Date value) {
            addCriterion("reg_date <>", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThan(Date value) {
            addCriterion("reg_date >", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThanOrEqualTo(Date value) {
            addCriterion("reg_date >=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThan(Date value) {
            addCriterion("reg_date <", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThanOrEqualTo(Date value) {
            addCriterion("reg_date <=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateIn(List<Date> values) {
            addCriterion("reg_date in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotIn(List<Date> values) {
            addCriterion("reg_date not in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateBetween(Date value1, Date value2) {
            addCriterion("reg_date between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotBetween(Date value1, Date value2) {
            addCriterion("reg_date not between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt3IsNull() {
            addCriterion("ext3 is null");
            return (Criteria) this;
        }

        public Criteria andExt3IsNotNull() {
            addCriterion("ext3 is not null");
            return (Criteria) this;
        }

        public Criteria andExt3EqualTo(String value) {
            addCriterion("ext3 =", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotEqualTo(String value) {
            addCriterion("ext3 <>", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThan(String value) {
            addCriterion("ext3 >", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("ext3 >=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThan(String value) {
            addCriterion("ext3 <", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("ext3 <=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Like(String value) {
            addCriterion("ext3 like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotLike(String value) {
            addCriterion("ext3 not like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3In(List<String> values) {
            addCriterion("ext3 in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotIn(List<String> values) {
            addCriterion("ext3 not in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Between(String value1, String value2) {
            addCriterion("ext3 between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotBetween(String value1, String value2) {
            addCriterion("ext3 not between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt4IsNull() {
            addCriterion("ext4 is null");
            return (Criteria) this;
        }

        public Criteria andExt4IsNotNull() {
            addCriterion("ext4 is not null");
            return (Criteria) this;
        }

        public Criteria andExt4EqualTo(String value) {
            addCriterion("ext4 =", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotEqualTo(String value) {
            addCriterion("ext4 <>", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThan(String value) {
            addCriterion("ext4 >", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThanOrEqualTo(String value) {
            addCriterion("ext4 >=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThan(String value) {
            addCriterion("ext4 <", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThanOrEqualTo(String value) {
            addCriterion("ext4 <=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Like(String value) {
            addCriterion("ext4 like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotLike(String value) {
            addCriterion("ext4 not like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4In(List<String> values) {
            addCriterion("ext4 in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotIn(List<String> values) {
            addCriterion("ext4 not in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Between(String value1, String value2) {
            addCriterion("ext4 between", value1, value2, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotBetween(String value1, String value2) {
            addCriterion("ext4 not between", value1, value2, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt5IsNull() {
            addCriterion("ext5 is null");
            return (Criteria) this;
        }

        public Criteria andExt5IsNotNull() {
            addCriterion("ext5 is not null");
            return (Criteria) this;
        }

        public Criteria andExt5EqualTo(String value) {
            addCriterion("ext5 =", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotEqualTo(String value) {
            addCriterion("ext5 <>", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5GreaterThan(String value) {
            addCriterion("ext5 >", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5GreaterThanOrEqualTo(String value) {
            addCriterion("ext5 >=", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5LessThan(String value) {
            addCriterion("ext5 <", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5LessThanOrEqualTo(String value) {
            addCriterion("ext5 <=", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5Like(String value) {
            addCriterion("ext5 like", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotLike(String value) {
            addCriterion("ext5 not like", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5In(List<String> values) {
            addCriterion("ext5 in", values, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotIn(List<String> values) {
            addCriterion("ext5 not in", values, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5Between(String value1, String value2) {
            addCriterion("ext5 between", value1, value2, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotBetween(String value1, String value2) {
            addCriterion("ext5 not between", value1, value2, "ext5");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}