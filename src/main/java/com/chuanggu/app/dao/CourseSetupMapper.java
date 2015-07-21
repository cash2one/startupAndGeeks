package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.CourseSetup;

public interface CourseSetupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseSetup record);

    int insertSelective(CourseSetup record);

    CourseSetup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseSetup record);

    int updateByPrimaryKey(CourseSetup record);
    
    List<CourseSetup> selectById(CourseSetup record);
}