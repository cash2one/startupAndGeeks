package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.SupplyMapper;
import com.cmcc.edu.dao.SupplyTypeMapper;
import com.cmcc.edu.entity.SupplyType;
import com.cmcc.edu.service.ISupplyTypeService;


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
