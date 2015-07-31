package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.CourseSetup;


public interface ICourseSetupService {
	public int insert(CourseSetup record);
	
	public List<CourseSetup> selectByCourseSetup(CourseSetup record);
	
	public int update(CourseSetup record);
	
	public int delete(Integer id);
	
	public CourseSetup find(Integer id);
	
	public List<CourseSetup> selectById(CourseSetup record);
}