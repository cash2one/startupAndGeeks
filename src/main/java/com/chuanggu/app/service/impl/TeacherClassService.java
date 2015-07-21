package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chuanggu.app.dao.StudentMapper;
import com.chuanggu.app.dao.TeacherClassMapper;
import com.chuanggu.app.entity.TeacherClass;
import com.chuanggu.app.service.ITeacherClassService;

public class TeacherClassService implements ITeacherClassService {
	
	@Autowired
	private TeacherClassMapper teacherClassMapper;
	
	@Override
	public List<TeacherClass> selectTeacherClassList(Long teacherId) {
		// TODO Auto-generated method stub
		return teacherClassMapper.selectByTeacherId(teacherId);
	}

}
