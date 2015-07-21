package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.SupplyMapper;
import com.chuanggu.app.dao.SupplyTypeMapper;
import com.chuanggu.app.entity.SupplyType;
import com.chuanggu.app.service.ISupplyTypeService;


@Service
public class SupplyTypeService implements ISupplyTypeService{

	
	@Autowired
	private SupplyTypeMapper supplyTypeMapper;
	
	
	@Override
	public List<SupplyType> findTypeBySupply(Integer type) {
		// TODO Auto-generated method stub
		return supplyTypeMapper.findTypeBySupply(type);
	}
}
