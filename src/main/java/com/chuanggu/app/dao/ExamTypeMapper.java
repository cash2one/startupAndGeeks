package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.ExamType;

public interface ExamTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExamType record);

    int insertSelective(ExamType record);

    ExamType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExamType record);

    int updateByPrimaryKey(ExamType record);
    
    List<ExamType> selectExamTypeList(ExamType record);
}