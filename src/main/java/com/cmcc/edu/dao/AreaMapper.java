package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Area;
import com.cmcc.edu.entity.AreaKey;
import com.cmcc.edu.entity.Student;

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