package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class GeoRequestLogVOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GeoRequestLogVOExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRequestIdIsNull() {
            addCriterion("request_id is null");
            return (Criteria) this;
        }

        public Criteria andRequestIdIsNotNull() {
            addCriterion("request_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequestIdEqualTo(String value) {
            addCriterion("request_id =", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotEqualTo(String value) {
            addCriterion("request_id <>", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThan(String value) {
            addCriterion("request_id >", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThanOrEqualTo(String value) {
            addCriterion("request_id >=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThan(String value) {
            addCriterion("request_id <", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThanOrEqualTo(String value) {
            addCriterion("request_id <=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLike(String value) {
            addCriterion("request_id like", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotLike(String value) {
            addCriterion("request_id not like", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdIn(List<String> values) {
            addCriterion("request_id in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotIn(List<String> values) {
            addCriterion("request_id not in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdBetween(String value1, String value2) {
            addCriterion("request_id between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotBetween(String value1, String value2) {
            addCriterion("request_id not between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeIsNull() {
            addCriterion("inner_if_type is null");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeIsNotNull() {
            addCriterion("inner_if_type is not null");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeEqualTo(String value) {
            addCriterion("inner_if_type =", value, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeNotEqualTo(String value) {
            addCriterion("inner_if_type <>", value, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeGreaterThan(String value) {
            addCriterion("inner_if_type >", value, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeGreaterThanOrEqualTo(String value) {
            addCriterion("inner_if_type >=", value, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeLessThan(String value) {
            addCriterion("inner_if_type <", value, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeLessThanOrEqualTo(String value) {
            addCriterion("inner_if_type <=", value, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeLike(String value) {
            addCriterion("inner_if_type like", value, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeNotLike(String value) {
            addCriterion("inner_if_type not like", value, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeIn(List<String> values) {
            addCriterion("inner_if_type in", values, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeNotIn(List<String> values) {
            addCriterion("inner_if_type not in", values, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeBetween(String value1, String value2) {
            addCriterion("inner_if_type between", value1, value2, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andInnerIfTypeNotBetween(String value1, String value2) {
            addCriterion("inner_if_type not between", value1, value2, "innerIfType");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(Integer value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(Integer value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(Integer value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(Integer value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(Integer value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<Integer> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<Integer> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(Integer value1, Integer value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andActionIsNull() {
            addCriterion("action is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("action is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("action =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("action <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("action >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("action >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("action <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("action <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLike(String value) {
            addCriterion("action like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("action not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("action in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("action not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("action between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("action not between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionCountIsNull() {
            addCriterion("action_count is null");
            return (Criteria) this;
        }

        public Criteria andActionCountIsNotNull() {
            addCriterion("action_count is not null");
            return (Criteria) this;
        }

        public Criteria andActionCountEqualTo(Integer value) {
            addCriterion("action_count =", value, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountNotEqualTo(Integer value) {
            addCriterion("action_count <>", value, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountGreaterThan(Integer value) {
            addCriterion("action_count >", value, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("action_count >=", value, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountLessThan(Integer value) {
            addCriterion("action_count <", value, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountLessThanOrEqualTo(Integer value) {
            addCriterion("action_count <=", value, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountIn(List<Integer> values) {
            addCriterion("action_count in", values, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountNotIn(List<Integer> values) {
            addCriterion("action_count not in", values, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountBetween(Integer value1, Integer value2) {
            addCriterion("action_count between", value1, value2, "actionCount");
            return (Criteria) this;
        }

        public Criteria andActionCountNotBetween(Integer value1, Integer value2) {
            addCriterion("action_count not between", value1, value2, "actionCount");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorIsNull() {
            addCriterion("phone_operator is null");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorIsNotNull() {
            addCriterion("phone_operator is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorEqualTo(String value) {
            addCriterion("phone_operator =", value, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorNotEqualTo(String value) {
            addCriterion("phone_operator <>", value, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorGreaterThan(String value) {
            addCriterion("phone_operator >", value, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("phone_operator >=", value, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorLessThan(String value) {
            addCriterion("phone_operator <", value, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorLessThanOrEqualTo(String value) {
            addCriterion("phone_operator <=", value, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorLike(String value) {
            addCriterion("phone_operator like", value, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorNotLike(String value) {
            addCriterion("phone_operator not like", value, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorIn(List<String> values) {
            addCriterion("phone_operator in", values, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorNotIn(List<String> values) {
            addCriterion("phone_operator not in", values, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorBetween(String value1, String value2) {
            addCriterion("phone_operator between", value1, value2, "phoneOperator");
            return (Criteria) this;
        }

        public Criteria andPhoneOperatorNotBetween(String value1, String value2) {
            addCriterion("phone_operator not between", value1, value2, "phoneOperator");
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