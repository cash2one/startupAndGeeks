package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.ExamType;
import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.Score;



public interface IExamTypeService {
	
	public int insert(ExamType record);
	
	public int update(ExamType record);
	
	public int delete(Long id);
	
	public ExamType find(Long id);
	
	public List<ExamType> selectExamTypeList(ExamType record);
}
