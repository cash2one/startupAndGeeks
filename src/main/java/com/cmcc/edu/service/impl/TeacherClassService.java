package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cmcc.edu.dao.StudentMapper;
import com.cmcc.edu.dao.TeacherClassMapper;
import com.cmcc.edu.entity.TeacherClass;
import com.cmcc.edu.service.ITeacherClassService;

public class TeacherClassService implements ITeacherClassService {
	
	@Autowired
	private TeacherClassMapper teacherClassMapper;
	
	@Override
	public List<TeacherClass> selectTeacherClassList(Long teacherId) {
		// TODO Auto-generated method stub
		return teacherClassMapper.selectByTeacherId(teacherId);
	}

}
