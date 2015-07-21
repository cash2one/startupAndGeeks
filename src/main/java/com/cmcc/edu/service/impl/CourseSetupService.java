	package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.cmcc.edu.dao.CourseSetupMapper;
import com.cmcc.edu.entity.CourseSetup;
import com.cmcc.edu.service.ICourseSetupService;


@Service
public class CourseSetupService implements ICourseSetupService{
	
	@Autowired
	private CourseSetupMapper courseSetupMapper;

	@Override
	public int insert(CourseSetup record) {
		return courseSetupMapper.insert(record);
	}



	@Override
	public int update(CourseSetup record) {
		return courseSetupMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Integer id) {
		return courseSetupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CourseSetup find(Integer id) {
		return courseSetupMapper.selectByPrimaryKey(id);
	}



	



	@Override
	public List<CourseSetup> selectById(CourseSetup record) {
		return courseSetupMapper.selectById(record);
	}



	@Override
	public List<CourseSetup> selectByCourseSetup(CourseSetup record) {
		return null;
	}

	

	
	
	
}
