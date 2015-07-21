package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Supply;
import com.cmcc.edu.entity.SupplyType;

public interface ISupplyTypeService {
	
	List<SupplyType> findTypeBySupply(Integer type);
	
	

}
