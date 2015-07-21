package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Homework;

public interface HomeworkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homework record);

    int insertSelective(Homework record);

    Homework selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Homework record);

    int updateByPrimaryKey(Homework record);
    
    List<Homework> homeworkList(Homework record);
    /**
     * 今日作业
     * @param record
     * @return
     */
    List<Homework> homeworkListNow(Homework record);
}