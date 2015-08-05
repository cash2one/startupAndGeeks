package com.chuanggu.app.dao;

import com.chuanggu.app.entity.Support;

public interface SupportDao {
    int insert(Support record);

    int insertSelective(Support record);
}