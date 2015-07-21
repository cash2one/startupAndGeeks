package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.CourseSetup;

public interface CourseSetupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseSetup record);

    int insertSelective(CourseSetup record);

    CourseSetup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseSetup record);

    int updateByPrimaryKey(CourseSetup record);
    
    List<CourseSetup> selectById(CourseSetup record);
}