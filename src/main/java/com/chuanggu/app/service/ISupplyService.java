package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.School;
import com.chuanggu.app.entity.Supply;
import com.chuanggu.app.entity.UserGroup;

public interface ISupplyService extends IPageService<Supply>{
	
	List<Supply> findSupplyList(Supply record);
	
	List<Supply> findParentSupply(Supply type);
	
	 int updateSupply(Supply record);
	 
	 int insert(Supply record);
	 
	 Supply findOne(Long id);
	 
	 int deleteSupply(Long id);

}
