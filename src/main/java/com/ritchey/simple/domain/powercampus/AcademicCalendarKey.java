package com.ritchey.simple.domain.powercampus;


import java.io.Serializable;

import com.ritchey.simple.domain.chapel.Reflect;

public class AcademicCalendarKey implements Reflect, Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACADEMICCALENDAR.ACADEMIC_YEAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    private String academicYear;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACADEMICCALENDAR.ACADEMIC_TERM
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    private String academicTerm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ACADEMICCALENDAR.ACADEMIC_SESSION
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    private String academicSession;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    private static final long serialVersionUID = 20130904110845L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACADEMICCALENDAR.ACADEMIC_YEAR
     *
     * @return the value of ACADEMICCALENDAR.ACADEMIC_YEAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public String getAcademicYear() {
        return academicYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACADEMICCALENDAR.ACADEMIC_YEAR
     *
     * @param academicYear the value for ACADEMICCALENDAR.ACADEMIC_YEAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear == null ? null : academicYear.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACADEMICCALENDAR.ACADEMIC_TERM
     *
     * @return the value of ACADEMICCALENDAR.ACADEMIC_TERM
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public String getAcademicTerm() {
        return academicTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACADEMICCALENDAR.ACADEMIC_TERM
     *
     * @param academicTerm the value for ACADEMICCALENDAR.ACADEMIC_TERM
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public void setAcademicTerm(String academicTerm) {
        this.academicTerm = academicTerm == null ? null : academicTerm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ACADEMICCALENDAR.ACADEMIC_SESSION
     *
     * @return the value of ACADEMICCALENDAR.ACADEMIC_SESSION
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public String getAcademicSession() {
        return academicSession;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ACADEMICCALENDAR.ACADEMIC_SESSION
     *
     * @param academicSession the value for ACADEMICCALENDAR.ACADEMIC_SESSION
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    public void setAcademicSession(String academicSession) {
        this.academicSession = academicSession == null ? null : academicSession.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[");
        sb.append("Hash = ").append(hashCode());
        sb.append(",academicYear=").append(academicYear);
        sb.append(",academicTerm=").append(academicTerm);
        sb.append(",academicSession=").append(academicSession);
        sb.append(",serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    @Override
    public Object get(String name) {
        if (name.equals("academicYear")) return academicYear;
        if (name.equals("academicTerm")) return academicTerm;
        if (name.equals("academicSession")) return academicSession;
        return null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    @Override
    public void set(String name, Object value) {
        if (name.equals("academicYear")) academicYear = value==null?null:(java.lang.String) value;
        if (name.equals("academicTerm")) academicTerm = value==null?null:(java.lang.String) value;
        if (name.equals("academicSession")) academicSession = value==null?null:(java.lang.String) value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    @Override
    public void copy(Object a) {
        AcademicCalendarKey castObject = (AcademicCalendarKey) a;
        this.academicYear = castObject.academicYear;
        this.academicTerm = castObject.academicTerm;
        this.academicSession = castObject.academicSession;
    }

    public String simpleName() {
        return "AcademicCalendarKey";
    }
}