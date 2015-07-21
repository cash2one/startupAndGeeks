package com.chuanggu.app.dao;

import com.chuanggu.app.entity.CourseFather;

public interface CourseFatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseFather record);

    int insertSelective(CourseFather record);

    CourseFather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseFather record);

    int updateByPrimaryKey(CourseFather record);
}