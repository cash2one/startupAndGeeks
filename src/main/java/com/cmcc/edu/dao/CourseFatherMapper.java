package com.cmcc.edu.dao;

import com.cmcc.edu.entity.CourseFather;

public interface CourseFatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseFather record);

    int insertSelective(CourseFather record);

    CourseFather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseFather record);

    int updateByPrimaryKey(CourseFather record);
}