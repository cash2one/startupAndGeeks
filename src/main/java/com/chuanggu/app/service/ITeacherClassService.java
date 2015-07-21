package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.ExamType;
import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.Score;
import com.chuanggu.app.entity.TeacherClass;



public interface ITeacherClassService {
	
	public List<TeacherClass> selectTeacherClassList(Long teacherId);
}
