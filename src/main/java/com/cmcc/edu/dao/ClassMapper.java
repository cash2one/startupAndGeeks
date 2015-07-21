package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Class;

public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
    
    List<Class> classList(Class record);
    
    List<Class> findAll();
    
    int deleteBatchRecord(List<Integer> idList);
    
    List<Class> selectClassBygrade(Class record);
    
    int insertBatch(List<Class> idList);
    
    Class selectByTeacher(Integer id);
}