package com.chuanggu.app.service;

import java.util.List;
import java.util.Set;

import com.chuanggu.app.entity.School;
import com.chuanggu.app.entity.Student;
import com.chuanggu.app.entity.Teacher;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.entity.UserGroup;

public interface ISchoolService extends IPageService<School>{
	

    School findOne(Long id);

    List<School> findSchoolList(School record);
    
    int insert(School record);
    
    int deleteSchool(Long id);
	
	 int updateSchool(School record);
   
	 Integer findMaxSchoolId(String schoolCode);
	 
	 School findDetailSchoolById(Long id);

}
