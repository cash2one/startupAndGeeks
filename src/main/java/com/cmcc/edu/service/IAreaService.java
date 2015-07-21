package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Area;

public interface IAreaService {
	
	List<Area> findProvince();
	
    List<Area> getCitysByProvinceId(Integer provinceCode);
    
    List<Area> getAreasByCityId(Integer cityCode);

}
