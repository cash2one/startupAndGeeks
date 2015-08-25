package com.chuanggu.app.dao;

import java.util.List;
import java.util.Map;

import com.chuanggu.app.entity.Maker;

public interface MakerDao {
    int deleteByPrimaryKey(String maker_id);

    int insert(Maker record);

    int insertSelective(Maker record);

    Maker selectByPrimaryKey(String maker_id);

    int updateByPrimaryKeySelective(Maker record);

    int updateByPrimaryKeyWithBLOBs(Maker record);

    int updateByPrimaryKey(Maker record);
    /*
     * myself
     */
    List<Maker> getMakerList(Map<String, String> param);
    Maker getMakerDetail(String id);
}
   