package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
    
    List<Score> selectScoreList(Score record);
    
    List<Score> selectScoreByExamId(Long examId);
    
    int deleteByexamId(Long examId);
}