package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.School;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.Teacher;

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