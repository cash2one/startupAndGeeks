package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Comment;
import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.Notice;
import com.cmcc.edu.entity.Score;



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
