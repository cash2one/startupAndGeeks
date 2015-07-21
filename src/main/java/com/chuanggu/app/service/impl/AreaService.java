package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.AreaMapper;
import com.chuanggu.app.entity.Area;
import com.chuanggu.app.service.IAreaService;

@Service
public class AreaService implements IAreaService{

	
	 @Autowired
		private AreaMapper areaMapper;
	 
	 
	 
	 @Override
	public List<Area> findProvince() {
		// TODO Auto-generated method stub
		return areaMapper.findProvince();
	}
	 
	 @Override
	public List<Area> getAreasByCityId(Integer cityCode) {
		// TODO Auto-generated method stub
		return areaMapper.getAreasByCityId(cityCode);
	}
	 
	 @Override
	public List<Area> getCitysByProvinceId(Integer provinceCode) {
		// TODO Auto-generated method stub
		return areaMapper.getCitysByProvinceId(provinceCode);
	}
}
