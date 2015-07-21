package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);
	
	Role findOne(Long id);
	
	List<Role> findAll();
	
	List<Role> findAllowed(Long id);
	
	int deleteBatchRecord(List<Long> roleList);

	
}