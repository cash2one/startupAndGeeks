package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.StudentMapper;
import com.cmcc.edu.dao.UserGroupMapper;
import com.cmcc.edu.entity.Teacher;
import com.cmcc.edu.entity.UserGroup;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.IUserGroupService;
import com.cmcc.edu.util.pagehelper.PageHelper;
@Service
public class UserGroupService implements IUserGroupService{
	
	@Autowired
	private UserGroupMapper userGroupMapper;
	
	
	@Override
	public List<UserGroup> findUserGroupList(UserGroup record) {
		// TODO Auto-generated method stub
		return userGroupMapper.findUserGroupList(record);
	}
	
	@Override
	public int deleteUserGroup(Long id) {
		// TODO Auto-generated method stub
		return userGroupMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int updateUserGroup(UserGroup record) {
		// TODO Auto-generated method stub
		return userGroupMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public UserGroup findOne(Long id) {
		List<UserGroup> usergroupList = userGroupMapper.findOne(id);
	       if(usergroupList.size() == 0) {
	           return null;
	       }
	       return usergroupList.get(0);
	}
	
	@Override
	public List<UserGroup> findStuAndTeaByID(UserGroup record) {
		// TODO Auto-generated method stub
		return userGroupMapper.findStuAndTeaByID(record);
	}
	
	@Override
	public int insert(UserGroup record) {
		// TODO Auto-generated method stub
		return userGroupMapper.insert(record);
	}
	
	@Override
	public List<UserGroup> findUserGroupByTeaId(UserGroup createid) {
		// TODO Auto-generated method stub
		return userGroupMapper.findUserGroupByTeaId(createid);
	}
	
	
	@Override
	public List<UserGroup> find(int pageNumber, int itemsOnPage, UserGroup t) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNumber, itemsOnPage);
		return userGroupMapper.findUserGroupList(t);
	}
	
	@Override
	public List<UserGroup> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<UserGroup> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
