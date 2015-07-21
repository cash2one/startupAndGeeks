package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Area;
import com.chuanggu.app.entity.AreaKey;
import com.chuanggu.app.entity.Student;

public interface AreaMapper {
    int deleteByPrimaryKey(AreaKey key);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(AreaKey key);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    List<Area> findProvince();
    
    List<Area> getCitysByProvinceId(Integer provinceCode);
    
    List<Area> getAreasByCityId(Integer cityCode);
}