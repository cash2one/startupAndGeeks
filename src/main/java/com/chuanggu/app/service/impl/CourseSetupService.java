	package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.chuanggu.app.dao.CourseSetupMapper;
import com.chuanggu.app.entity.CourseSetup;
import com.chuanggu.app.service.ICourseSetupService;


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
