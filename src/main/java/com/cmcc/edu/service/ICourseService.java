package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Course;



public interface ICourseService {
	public List<Course> courseList(Course record);
	
	public int insert(Course record);
	
	public int uodate(Course record);
	
	public int delete(Integer id);
	
	public Course find(Integer id);
}
