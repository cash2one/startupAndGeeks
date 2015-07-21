package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.TeacherClass;

public interface TeacherClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeacherClass record);

    int insertSelective(TeacherClass record);

    TeacherClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeacherClass record);

    int updateByPrimaryKey(TeacherClass record);
    
    List<TeacherClass> selectByTeacherId(Long teacherId);
}