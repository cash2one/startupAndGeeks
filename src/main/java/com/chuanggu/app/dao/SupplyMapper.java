package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.School;
import com.chuanggu.app.entity.Supply;

public interface SupplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supply record);

    int insertSelective(Supply record);

    Supply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Supply record);

    int updateByPrimaryKey(Supply record);
    
    List<Supply> findSupplyList(Supply record);
    
    List<Supply> findParentSupply(Supply type);
}