package com.chuanggu.app.dao;

import com.chuanggu.app.entity.Company;

public interface CompanyDao {
    int deleteByPrimaryKey(String company_id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(String company_id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}