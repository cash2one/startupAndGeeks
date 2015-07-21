package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.Notice;

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