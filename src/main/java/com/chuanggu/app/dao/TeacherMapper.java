package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Student;
import com.chuanggu.app.entity.Teacher;

public interface TeacherMapper {

	int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    List<Teacher> findAll();
    
    List<Teacher> findAllowed(Long schoolId);
    
    List<Teacher> findOne(Long userId);
    
    Teacher findByUsername(String username);

    int deleteBatchRecord(List<Long> idList);
    
    List<Teacher> findArea(Long schoolList);
    
	void lockUser(String username);
	
	List<Teacher> findTeacherByIds(String teacherIds);
	
	List<Teacher> findTeacherByGradeId(Integer gradeId);
	
	 List<Teacher> findTeacherBook(Teacher record);
}