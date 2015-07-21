package com.cmcc.edu.service;

import java.text.ParseException;
import java.util.List;

import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.Student;

public interface IStudentService extends IPageService<Student>{
	
	List<Student> findStudentById(Integer stuId);
	
	List<Student> findStudentByClassId(Integer classId);
	
	/**
	 * excel导入学生信息
	 */
	public void formExcel(Integer classId,Integer gradeId,String filename);
	
	/**
	 * 批量上传学生信息
	 */
	public void batch(String information,Integer classId,Integer gradeId) throws ParseException;
	
	List<Student> findStuByClassIdAndGradeId(Student record);
	
	 List<Student> findStuTreeBySchoolId(Long schoolId);
	 
	 List<Student> selectStudentList(Student record);
	 
	 List<Student> findClassBySchoolId(Long schoolId);
	 
	 List<Student> findStuList(Student record);
	 
	 int insert(Student student);

}
