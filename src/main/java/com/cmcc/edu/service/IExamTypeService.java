package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.ExamType;
import com.cmcc.edu.entity.Notice;
import com.cmcc.edu.entity.Score;



public interface IExamTypeService {
	
	public int insert(ExamType record);
	
	public int update(ExamType record);
	
	public int delete(Long id);
	
	public ExamType find(Long id);
	
	public List<ExamType> selectExamTypeList(ExamType record);
}
