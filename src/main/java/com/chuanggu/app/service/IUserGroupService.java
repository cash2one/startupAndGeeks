package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Student;
import com.chuanggu.app.entity.Teacher;
import com.chuanggu.app.entity.UserGroup;

public interface IUserGroupService extends IPageService<UserGroup>{
	
	List<UserGroup> findUserGroupList(UserGroup record);
	
	int deleteUserGroup(Long id);
	
	 int updateUserGroup(UserGroup record);
	 
	 UserGroup findOne(Long id);
	 
	 List<UserGroup> findStuAndTeaByID(UserGroup record);
	 
	 int insert(UserGroup record);
	 
	 List<UserGroup> findUserGroupByTeaId(UserGroup createid);

}
