package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Grade;

public interface GradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
    
    List<Grade> gradeList(Grade record);
    
    List<Grade> findAll();
    
    int deleteBatchRecord(List<Integer> idList);
    
    List<Grade> selectGradeBySchool(Long schoolId);
    
    int insertBatch(List<Grade> idList);
}