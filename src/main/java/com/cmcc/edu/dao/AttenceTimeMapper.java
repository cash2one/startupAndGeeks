package com.cmcc.edu.dao;

import com.cmcc.edu.entity.AttenceTime;

public interface AttenceTimeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AttenceTime record);

    int insertSelective(AttenceTime record);

    AttenceTime selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttenceTime record);

    int updateByPrimaryKey(AttenceTime record);
}