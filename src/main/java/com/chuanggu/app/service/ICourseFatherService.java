package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.CourseFather;
import com.chuanggu.app.entity.CourseSetup;


public interface ICourseFatherService {
	
	public int insert(CourseFather record);
	
	public void insertForIds(String ids,Integer id);

	
}
