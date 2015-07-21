package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.TeacherClass;

public interface TeacherClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeacherClass record);

    int insertSelective(TeacherClass record);

    TeacherClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeacherClass record);

    int updateByPrimaryKey(TeacherClass record);
    
    List<TeacherClass> selectByTeacherId(Long teacherId);
}