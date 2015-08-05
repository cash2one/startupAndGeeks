package com.chuanggu.app.dao;

import com.chuanggu.app.entity.FinanceProcess;

public interface FinanceProcessDao {
    int deleteByPrimaryKey(String financing_id);

    int insert(FinanceProcess record);

    int insertSelective(FinanceProcess record);

    FinanceProcess selectByPrimaryKey(String financing_id);

    int updateByPrimaryKeySelective(FinanceProcess record);

    int updateByPrimaryKey(FinanceProcess record);
}