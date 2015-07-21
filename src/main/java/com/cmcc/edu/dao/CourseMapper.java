package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Course;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    
    List<Course> courseList(Course record);
}