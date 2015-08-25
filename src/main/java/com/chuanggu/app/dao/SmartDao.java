package com.chuanggu.app.dao;

import java.util.List;
import java.util.Map;

import com.chuanggu.app.entity.Smart;


public interface SmartDao {
    int deleteByPrimaryKey(String smart_id);

    int insert(Smart record);

    int insertSelective(Smart record);

    Smart selectByPrimaryKey(String smart_id);
    
    int updateByPrimaryKeySelective(Smart record);

    int updateByPrimaryKeyWithBLOBs(Smart record);

    int updateByPrimaryKey(Smart record);
    
    /*
     * myself
     */
    List<Smart> getSmartList(Map<String, String> param);
    
    Smart getSmartDetail(String id);
}