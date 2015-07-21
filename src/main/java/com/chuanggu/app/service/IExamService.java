package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Comment;
import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.Score;



public interface IExamService extends IPageService<Exam>{
	
	public int insert(Exam record);
	
	public int update(Exam record);
	
	public int delete(Long id);
	
	public Exam find(Long id);
	
	public List<Exam> selectExamList(Exam record);
	
	public List<Exam> selectExamForScore(Exam record);
	
	int updateEnterStateById(Exam record);
	
	List<Exam> selectExamDraftList(Exam record);
	
	public int updatePubStateById(Long id);
}
