package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.ExamType;
import com.cmcc.edu.entity.Notice;
import com.cmcc.edu.entity.Score;
import com.cmcc.edu.entity.TeacherClass;



public interface ITeacherClassService {
	
	public List<TeacherClass> selectTeacherClassList(Long teacherId);
}
