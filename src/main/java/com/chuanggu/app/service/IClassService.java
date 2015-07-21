package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Class;


public interface IClassService extends IPageService<Class>{
	
	public int insert(Class record);
	
	public int updateClass(Class record);
	
	public int deleteClass(Integer id);
	
	public Class find(Integer id);
	
	public List<Class> classList(Class record);
		
	public Class findOne(Integer id);
	
	public int deleteBatchRecord(List<Integer> idList);

	public int insertBatch(List<Class> idList);
	
	List<Class> selectClassBygrade(Class record);
}
