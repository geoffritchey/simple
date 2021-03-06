package com.ritchey.simple.mapper.powercampus;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ritchey.simple.domain.powercampus.AcademicCalendar;
import com.ritchey.simple.domain.powercampus.AcademicCalendarExample;
import com.ritchey.simple.domain.powercampus.AcademicCalendarKey;

public interface AcademicCalendarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int countByExample(AcademicCalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int deleteByExample(AcademicCalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int deleteByPrimaryKey(AcademicCalendarKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int insert(AcademicCalendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int insertSelective(AcademicCalendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    List<AcademicCalendar> selectByExample(AcademicCalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    AcademicCalendar selectByPrimaryKey(AcademicCalendarKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int updateByExampleSelective(@Param("record") AcademicCalendar record, @Param("example") AcademicCalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int updateByExample(@Param("record") AcademicCalendar record, @Param("example") AcademicCalendarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int updateByPrimaryKeySelective(AcademicCalendar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACADEMICCALENDAR
     *
     * @mbggenerated Wed Sep 04 11:08:45 CDT 2013
     */
    int updateByPrimaryKey(AcademicCalendar record);
}