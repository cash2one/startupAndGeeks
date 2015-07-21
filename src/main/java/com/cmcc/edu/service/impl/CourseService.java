package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.cmcc.edu.dao.CourseMapper;
import com.cmcc.edu.entity.Course;
import com.cmcc.edu.service.ICourseService;


@Service
public class CourseService implements ICourseService{
	
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public List<Course> courseList(Course record) {	
		return courseMapper.courseList(record);
	}

	@Override
	public int insert(Course record) {
		return courseMapper.insert(record);
	}

	@Override
	public int uodate(Course record) {
		return courseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Integer id) {
		return courseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Course find(Integer id) {
		return courseMapper.selectByPrimaryKey(id);
	}

	
	
	
}