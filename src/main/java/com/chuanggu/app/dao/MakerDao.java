package com.chuanggu.app.dao;

import com.chuanggu.app.entity.Maker;

public interface MakerDao {
    int deleteByPrimaryKey(String maker_id);

    int insert(Maker record);

    int insertSelective(Maker record);

    Maker selectByPrimaryKey(String maker_id);

    int updateByPrimaryKeySelective(Maker record);

    int updateByPrimaryKeyWithBLOBs(Maker record);

    int updateByPrimaryKey(Maker record);
}