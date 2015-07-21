package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.ExamType;

public interface ExamTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExamType record);

    int insertSelective(ExamType record);

    ExamType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExamType record);

    int updateByPrimaryKey(ExamType record);
    
    List<ExamType> selectExamTypeList(ExamType record);
}