package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Grade;

public interface IGradeService extends IPageService<Grade>{
	
	public int insert(Grade record);
	
	public int updateGrade(Grade record);
	
	public int deleteGrade(Integer id);
	
	public List<Grade> findAll();
	
	public Grade selectByPrimaryKey(Integer id);
	
	public List<Grade> noticeList(Grade record);
	
	public Grade findOne(Integer id);
	
	public int deleteBatchRecord(List<Integer> idList);
	
	public int insertBatch(List<Grade> idList);
	
	List<Grade> selectGradeBySchool(Long schoolId);
}
