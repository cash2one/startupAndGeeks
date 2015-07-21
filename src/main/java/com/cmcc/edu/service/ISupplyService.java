package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.School;
import com.cmcc.edu.entity.Supply;
import com.cmcc.edu.entity.UserGroup;

public interface ISupplyService extends IPageService<Supply>{
	
	List<Supply> findSupplyList(Supply record);
	
	List<Supply> findParentSupply(Supply type);
	
	 int updateSupply(Supply record);
	 
	 int insert(Supply record);
	 
	 Supply findOne(Long id);
	 
	 int deleteSupply(Long id);

}
