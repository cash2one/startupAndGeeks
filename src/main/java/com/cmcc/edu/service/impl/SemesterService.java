package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.cmcc.edu.dao.SemesterMapper;
import com.cmcc.edu.entity.Semester;
import com.cmcc.edu.service.ISemesterService;


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
