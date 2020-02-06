package com.ritchey.simple.domain.powercampus;

import java.util.ArrayList;
import java.util.List;

public class PeopleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public PeopleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[");
        sb.append("Hash = ").append(hashCode());
        sb.append(",orderByClause=").append(orderByClause);
        sb.append(",distinct=").append(distinct);
        sb.append(",oredCriteria=").append(oredCriteria);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
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

        public Criteria andPeopleIdIsNull() {
            addCriterion("PEOPLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPeopleIdIsNotNull() {
            addCriterion("PEOPLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPeopleIdEqualTo(String value) {
            addCriterion("PEOPLE_ID =", value, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdNotEqualTo(String value) {
            addCriterion("PEOPLE_ID <>", value, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdGreaterThan(String value) {
            addCriterion("PEOPLE_ID >", value, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdGreaterThanOrEqualTo(String value) {
            addCriterion("PEOPLE_ID >=", value, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdLessThan(String value) {
            addCriterion("PEOPLE_ID <", value, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdLessThanOrEqualTo(String value) {
            addCriterion("PEOPLE_ID <=", value, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdLike(String value) {
            addCriterion("PEOPLE_ID like", value, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdNotLike(String value) {
            addCriterion("PEOPLE_ID not like", value, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdIn(List<String> values) {
            addCriterion("PEOPLE_ID in", values, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdNotIn(List<String> values) {
            addCriterion("PEOPLE_ID not in", values, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdBetween(String value1, String value2) {
            addCriterion("PEOPLE_ID between", value1, value2, "peopleId");
            return (Criteria) this;
        }

        public Criteria andPeopleIdNotBetween(String value1, String value2) {
            addCriterion("PEOPLE_ID not between", value1, value2, "peopleId");
            return (Criteria) this;
        }

        public Criteria andFirstNameIsNull() {
            addCriterion("FIRST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFirstNameIsNotNull() {
            addCriterion("FIRST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFirstNameEqualTo(String value) {
            addCriterion("FIRST_NAME =", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotEqualTo(String value) {
            addCriterion("FIRST_NAME <>", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameGreaterThan(String value) {
            addCriterion("FIRST_NAME >", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_NAME >=", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLessThan(String value) {
            addCriterion("FIRST_NAME <", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLessThanOrEqualTo(String value) {
            addCriterion("FIRST_NAME <=", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameLike(String value) {
            addCriterion("FIRST_NAME like", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotLike(String value) {
            addCriterion("FIRST_NAME not like", value, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameIn(List<String> values) {
            addCriterion("FIRST_NAME in", values, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotIn(List<String> values) {
            addCriterion("FIRST_NAME not in", values, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameBetween(String value1, String value2) {
            addCriterion("FIRST_NAME between", value1, value2, "firstName");
            return (Criteria) this;
        }

        public Criteria andFirstNameNotBetween(String value1, String value2) {
            addCriterion("FIRST_NAME not between", value1, value2, "firstName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameIsNull() {
            addCriterion("MIDDLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMiddleNameIsNotNull() {
            addCriterion("MIDDLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleNameEqualTo(String value) {
            addCriterion("MIDDLE_NAME =", value, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameNotEqualTo(String value) {
            addCriterion("MIDDLE_NAME <>", value, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameGreaterThan(String value) {
            addCriterion("MIDDLE_NAME >", value, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameGreaterThanOrEqualTo(String value) {
            addCriterion("MIDDLE_NAME >=", value, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameLessThan(String value) {
            addCriterion("MIDDLE_NAME <", value, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameLessThanOrEqualTo(String value) {
            addCriterion("MIDDLE_NAME <=", value, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameLike(String value) {
            addCriterion("MIDDLE_NAME like", value, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameNotLike(String value) {
            addCriterion("MIDDLE_NAME not like", value, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameIn(List<String> values) {
            addCriterion("MIDDLE_NAME in", values, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameNotIn(List<String> values) {
            addCriterion("MIDDLE_NAME not in", values, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameBetween(String value1, String value2) {
            addCriterion("MIDDLE_NAME between", value1, value2, "middleName");
            return (Criteria) this;
        }

        public Criteria andMiddleNameNotBetween(String value1, String value2) {
            addCriterion("MIDDLE_NAME not between", value1, value2, "middleName");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNull() {
            addCriterion("LAST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLastNameIsNotNull() {
            addCriterion("LAST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLastNameEqualTo(String value) {
            addCriterion("LAST_NAME =", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotEqualTo(String value) {
            addCriterion("LAST_NAME <>", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThan(String value) {
            addCriterion("LAST_NAME >", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_NAME >=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThan(String value) {
            addCriterion("LAST_NAME <", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLessThanOrEqualTo(String value) {
            addCriterion("LAST_NAME <=", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameLike(String value) {
            addCriterion("LAST_NAME like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotLike(String value) {
            addCriterion("LAST_NAME not like", value, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameIn(List<String> values) {
            addCriterion("LAST_NAME in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotIn(List<String> values) {
            addCriterion("LAST_NAME not in", values, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameBetween(String value1, String value2) {
            addCriterion("LAST_NAME between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andLastNameNotBetween(String value1, String value2) {
            addCriterion("LAST_NAME not between", value1, value2, "lastName");
            return (Criteria) this;
        }

        public Criteria andSuffixIsNull() {
            addCriterion("SUFFIX is null");
            return (Criteria) this;
        }

        public Criteria andSuffixIsNotNull() {
            addCriterion("SUFFIX is not null");
            return (Criteria) this;
        }

        public Criteria andSuffixEqualTo(String value) {
            addCriterion("SUFFIX =", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotEqualTo(String value) {
            addCriterion("SUFFIX <>", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixGreaterThan(String value) {
            addCriterion("SUFFIX >", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("SUFFIX >=", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLessThan(String value) {
            addCriterion("SUFFIX <", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLessThanOrEqualTo(String value) {
            addCriterion("SUFFIX <=", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLike(String value) {
            addCriterion("SUFFIX like", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotLike(String value) {
            addCriterion("SUFFIX not like", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixIn(List<String> values) {
            addCriterion("SUFFIX in", values, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotIn(List<String> values) {
            addCriterion("SUFFIX not in", values, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixBetween(String value1, String value2) {
            addCriterion("SUFFIX between", value1, value2, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotBetween(String value1, String value2) {
            addCriterion("SUFFIX not between", value1, value2, "suffix");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("NICKNAME is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("NICKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("NICKNAME =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("NICKNAME <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("NICKNAME >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("NICKNAME >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("NICKNAME <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("NICKNAME <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("NICKNAME like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("NICKNAME not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("NICKNAME in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("NICKNAME not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("NICKNAME between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("NICKNAME not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andPersonidIsNull() {
            addCriterion("PersonId is null");
            return (Criteria) this;
        }

        public Criteria andPersonidIsNotNull() {
            addCriterion("PersonId is not null");
            return (Criteria) this;
        }

        public Criteria andPersonidEqualTo(Integer value) {
            addCriterion("PersonId =", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidNotEqualTo(Integer value) {
            addCriterion("PersonId <>", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidGreaterThan(Integer value) {
            addCriterion("PersonId >", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidGreaterThanOrEqualTo(Integer value) {
            addCriterion("PersonId >=", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidLessThan(Integer value) {
            addCriterion("PersonId <", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidLessThanOrEqualTo(Integer value) {
            addCriterion("PersonId <=", value, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidIn(List<Integer> values) {
            addCriterion("PersonId in", values, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidNotIn(List<Integer> values) {
            addCriterion("PersonId not in", values, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidBetween(Integer value1, Integer value2) {
            addCriterion("PersonId between", value1, value2, "personid");
            return (Criteria) this;
        }

        public Criteria andPersonidNotBetween(Integer value1, Integer value2) {
            addCriterion("PersonId not between", value1, value2, "personid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidIsNull() {
            addCriterion("PrimaryPhoneId is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidIsNotNull() {
            addCriterion("PrimaryPhoneId is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidEqualTo(Integer value) {
            addCriterion("PrimaryPhoneId =", value, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidNotEqualTo(Integer value) {
            addCriterion("PrimaryPhoneId <>", value, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidGreaterThan(Integer value) {
            addCriterion("PrimaryPhoneId >", value, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidGreaterThanOrEqualTo(Integer value) {
            addCriterion("PrimaryPhoneId >=", value, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidLessThan(Integer value) {
            addCriterion("PrimaryPhoneId <", value, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidLessThanOrEqualTo(Integer value) {
            addCriterion("PrimaryPhoneId <=", value, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidIn(List<Integer> values) {
            addCriterion("PrimaryPhoneId in", values, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidNotIn(List<Integer> values) {
            addCriterion("PrimaryPhoneId not in", values, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidBetween(Integer value1, Integer value2) {
            addCriterion("PrimaryPhoneId between", value1, value2, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andPrimaryphoneidNotBetween(Integer value1, Integer value2) {
            addCriterion("PrimaryPhoneId not between", value1, value2, "primaryphoneid");
            return (Criteria) this;
        }

        public Criteria andLegalnameIsNull() {
            addCriterion("LegalName is null");
            return (Criteria) this;
        }

        public Criteria andLegalnameIsNotNull() {
            addCriterion("LegalName is not null");
            return (Criteria) this;
        }

        public Criteria andLegalnameEqualTo(String value) {
            addCriterion("LegalName =", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameNotEqualTo(String value) {
            addCriterion("LegalName <>", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameGreaterThan(String value) {
            addCriterion("LegalName >", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameGreaterThanOrEqualTo(String value) {
            addCriterion("LegalName >=", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameLessThan(String value) {
            addCriterion("LegalName <", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameLessThanOrEqualTo(String value) {
            addCriterion("LegalName <=", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameLike(String value) {
            addCriterion("LegalName like", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameNotLike(String value) {
            addCriterion("LegalName not like", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameIn(List<String> values) {
            addCriterion("LegalName in", values, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameNotIn(List<String> values) {
            addCriterion("LegalName not in", values, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameBetween(String value1, String value2) {
            addCriterion("LegalName between", value1, value2, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameNotBetween(String value1, String value2) {
            addCriterion("LegalName not between", value1, value2, "legalname");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PEOPLE
     *
     * @mbggenerated do_not_delete_during_merge Wed Sep 04 11:08:45 CDT 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PEOPLE
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
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