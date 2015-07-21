package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Area;

public interface IAreaService {
	
	List<Area> findProvince();
	
    List<Area> getCitysByProvinceId(Integer provinceCode);
    
    List<Area> getAreasByCityId(Integer cityCode);

}
