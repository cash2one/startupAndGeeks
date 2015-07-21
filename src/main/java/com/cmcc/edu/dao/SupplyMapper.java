package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.School;
import com.cmcc.edu.entity.Supply;

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