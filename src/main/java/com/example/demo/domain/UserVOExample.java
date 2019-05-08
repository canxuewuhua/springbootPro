package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class UserVOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserVOExample() {
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

        public Criteria andStIdIsNull() {
            addCriterion("st_id is null");
            return (Criteria) this;
        }

        public Criteria andStIdIsNotNull() {
            addCriterion("st_id is not null");
            return (Criteria) this;
        }

        public Criteria andStIdEqualTo(Integer value) {
            addCriterion("st_id =", value, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdNotEqualTo(Integer value) {
            addCriterion("st_id <>", value, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdGreaterThan(Integer value) {
            addCriterion("st_id >", value, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("st_id >=", value, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdLessThan(Integer value) {
            addCriterion("st_id <", value, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdLessThanOrEqualTo(Integer value) {
            addCriterion("st_id <=", value, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdIn(List<Integer> values) {
            addCriterion("st_id in", values, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdNotIn(List<Integer> values) {
            addCriterion("st_id not in", values, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdBetween(Integer value1, Integer value2) {
            addCriterion("st_id between", value1, value2, "stId");
            return (Criteria) this;
        }

        public Criteria andStIdNotBetween(Integer value1, Integer value2) {
            addCriterion("st_id not between", value1, value2, "stId");
            return (Criteria) this;
        }

        public Criteria andStNameIsNull() {
            addCriterion("st_name is null");
            return (Criteria) this;
        }

        public Criteria andStNameIsNotNull() {
            addCriterion("st_name is not null");
            return (Criteria) this;
        }

        public Criteria andStNameEqualTo(String value) {
            addCriterion("st_name =", value, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameNotEqualTo(String value) {
            addCriterion("st_name <>", value, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameGreaterThan(String value) {
            addCriterion("st_name >", value, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameGreaterThanOrEqualTo(String value) {
            addCriterion("st_name >=", value, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameLessThan(String value) {
            addCriterion("st_name <", value, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameLessThanOrEqualTo(String value) {
            addCriterion("st_name <=", value, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameLike(String value) {
            addCriterion("st_name like", value, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameNotLike(String value) {
            addCriterion("st_name not like", value, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameIn(List<String> values) {
            addCriterion("st_name in", values, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameNotIn(List<String> values) {
            addCriterion("st_name not in", values, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameBetween(String value1, String value2) {
            addCriterion("st_name between", value1, value2, "stName");
            return (Criteria) this;
        }

        public Criteria andStNameNotBetween(String value1, String value2) {
            addCriterion("st_name not between", value1, value2, "stName");
            return (Criteria) this;
        }

        public Criteria andStAddressIsNull() {
            addCriterion("st_address is null");
            return (Criteria) this;
        }

        public Criteria andStAddressIsNotNull() {
            addCriterion("st_address is not null");
            return (Criteria) this;
        }

        public Criteria andStAddressEqualTo(String value) {
            addCriterion("st_address =", value, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressNotEqualTo(String value) {
            addCriterion("st_address <>", value, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressGreaterThan(String value) {
            addCriterion("st_address >", value, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressGreaterThanOrEqualTo(String value) {
            addCriterion("st_address >=", value, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressLessThan(String value) {
            addCriterion("st_address <", value, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressLessThanOrEqualTo(String value) {
            addCriterion("st_address <=", value, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressLike(String value) {
            addCriterion("st_address like", value, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressNotLike(String value) {
            addCriterion("st_address not like", value, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressIn(List<String> values) {
            addCriterion("st_address in", values, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressNotIn(List<String> values) {
            addCriterion("st_address not in", values, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressBetween(String value1, String value2) {
            addCriterion("st_address between", value1, value2, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAddressNotBetween(String value1, String value2) {
            addCriterion("st_address not between", value1, value2, "stAddress");
            return (Criteria) this;
        }

        public Criteria andStAgeIsNull() {
            addCriterion("st_age is null");
            return (Criteria) this;
        }

        public Criteria andStAgeIsNotNull() {
            addCriterion("st_age is not null");
            return (Criteria) this;
        }

        public Criteria andStAgeEqualTo(Integer value) {
            addCriterion("st_age =", value, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeNotEqualTo(Integer value) {
            addCriterion("st_age <>", value, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeGreaterThan(Integer value) {
            addCriterion("st_age >", value, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("st_age >=", value, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeLessThan(Integer value) {
            addCriterion("st_age <", value, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeLessThanOrEqualTo(Integer value) {
            addCriterion("st_age <=", value, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeIn(List<Integer> values) {
            addCriterion("st_age in", values, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeNotIn(List<Integer> values) {
            addCriterion("st_age not in", values, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeBetween(Integer value1, Integer value2) {
            addCriterion("st_age between", value1, value2, "stAge");
            return (Criteria) this;
        }

        public Criteria andStAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("st_age not between", value1, value2, "stAge");
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