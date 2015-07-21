package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.School;
import com.chuanggu.app.entity.Student;
import com.chuanggu.app.entity.Teacher;

public interface SchoolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
    
    List<School> findOne(Long id);
    
    List<School> findSchoolList(School record);
    
    Integer findMaxSchoolId(String schoolCode);
    
    School findDetailSchoolById(Long id);
}