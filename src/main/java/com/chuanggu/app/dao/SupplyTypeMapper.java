package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Supply;
import com.chuanggu.app.entity.SupplyType;

public interface SupplyTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupplyType record);

    int insertSelective(SupplyType record);

    SupplyType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SupplyType record);

    int updateByPrimaryKey(SupplyType record);
    
    List<SupplyType> findTypeBySupply(Integer type);
    
}