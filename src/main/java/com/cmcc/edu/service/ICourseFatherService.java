package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.CourseFather;
import com.cmcc.edu.entity.CourseSetup;


public interface ICourseFatherService {
	
	public int insert(CourseFather record);
	
	public void insertForIds(String ids,Integer id);

	
}
