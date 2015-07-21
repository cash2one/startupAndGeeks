package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Semester;

public interface SemesterMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Semester record);

    int insertSelective(Semester record);

    Semester selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Semester record);

    int updateByPrimaryKey(Semester record);
    
    List<Semester> semesterList(Semester record);
}