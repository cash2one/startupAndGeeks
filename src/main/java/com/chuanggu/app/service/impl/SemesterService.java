package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.chuanggu.app.dao.SemesterMapper;
import com.chuanggu.app.entity.Semester;
import com.chuanggu.app.service.ISemesterService;


@Service
public class SemesterService implements ISemesterService{
	
	@Autowired
	private SemesterMapper semesterMapper;

	@Override
	public List<Semester> semesterList(Semester record) {
		return semesterMapper.semesterList(record);
	}

	@Override
	public int insert(Semester record) {
		return semesterMapper.insert(record);
	}

	@Override
	public int update(Semester record) {
		return semesterMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Integer id) {
		return semesterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Semester find(Integer id) {
		return semesterMapper.selectByPrimaryKey(id);
	}

	
	

	
	
	
}
