package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    
    List<Student> findStudentById(Integer stuId);
    
    List<Student> findStudentByClassId(Integer classId);
    
    List<Student> findStuByClassIdAndGradeId(Student record);
    
    List<Student> findStuTreeBySchoolId(Long schoolId);
    
    List<Student> selectStudentList(Student record);
    
    List<Student> findClassBySchoolId(Long schoolId);
    
    List<Student> findStuList(Student record);
}