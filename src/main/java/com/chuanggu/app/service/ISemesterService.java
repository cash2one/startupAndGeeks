package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Semester;


public interface ISemesterService {
	
	public List<Semester> semesterList(Semester record);
	
	public int insert(Semester record);
	
	public int update(Semester record);
	
	public int delete(Integer id);
	
	public Semester find(Integer id);
	
}
