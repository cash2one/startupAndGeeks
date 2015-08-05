package com.chuanggu.app.dao;

import com.chuanggu.app.entity.Investor;

public interface InvestorDao {
    int insert(Investor record);

    int insertSelective(Investor record);
}