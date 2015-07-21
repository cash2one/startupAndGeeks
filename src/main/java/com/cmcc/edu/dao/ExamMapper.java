package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.Notice;

public interface ExamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
    
    
    List<Exam> selectExamList(Exam record);
    
    List<Exam> selectExamForScore(Exam record);
    
    int updateEnterStateById(Exam record);
    
    List<Exam> selectExamDraftList(Exam record);
    
    int updatePubStateById(Long id);
}