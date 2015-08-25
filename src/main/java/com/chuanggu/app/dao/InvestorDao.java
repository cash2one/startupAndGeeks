package com.chuanggu.app.dao;

import java.util.List;
import java.util.Map;

import com.chuanggu.app.entity.Investor;
import com.chuanggu.app.entity.Smart;

public interface InvestorDao {
    int insert(Investor record);

    int insertSelective(Investor record);
    
    /*
     * myself
     */
    List<Investor> getInvestorList(Map<String, String> param);
    Smart getInvestorDetail(String id);
}