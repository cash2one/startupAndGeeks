package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Supply;
import com.chuanggu.app.entity.SupplyType;

public interface ISupplyTypeService {
	
	List<SupplyType> findTypeBySupply(Integer type);
	
	

}
