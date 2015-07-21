package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.Teacher;
import com.cmcc.edu.entity.UserGroup;

public interface IUserGroupService extends IPageService<UserGroup>{
	
	List<UserGroup> findUserGroupList(UserGroup record);
	
	int deleteUserGroup(Long id);
	
	 int updateUserGroup(UserGroup record);
	 
	 UserGroup findOne(Long id);
	 
	 List<UserGroup> findStuAndTeaByID(UserGroup record);
	 
	 int insert(UserGroup record);
	 
	 List<UserGroup> findUserGroupByTeaId(UserGroup createid);

}
