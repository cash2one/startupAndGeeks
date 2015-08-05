package com.chuanggu.app.dao;

import com.chuanggu.app.entity.smart;

public interface SmartDao {
    int deleteByPrimaryKey(String smart_id);

    int insert(smart record);

    int insertSelective(smart record);

    smart selectByPrimaryKey(String smart_id);

    int updateByPrimaryKeySelective(smart record);

    int updateByPrimaryKeyWithBLOBs(smart record);

    int updateByPrimaryKey(smart record);
}