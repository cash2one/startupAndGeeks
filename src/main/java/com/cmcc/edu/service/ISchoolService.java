package com.cmcc.edu.service;

import java.util.List;
import java.util.Set;

import com.cmcc.edu.entity.School;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.Teacher;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.entity.UserGroup;

public interface ISchoolService extends IPageService<School>{
	

    School findOne(Long id);

    List<School> findSchoolList(School record);
    
    int insert(School record);
    
    int deleteSchool(Long id);
	
	 int updateSchool(School record);
   
	 Integer findMaxSchoolId(String schoolCode);
	 
	 School findDetailSchoolById(Long id);

}
