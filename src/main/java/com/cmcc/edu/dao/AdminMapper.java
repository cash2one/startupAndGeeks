package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Admin;

public interface AdminMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    List<Admin> findAll();
    
    List<Admin> findOne(Long userId);
    
    Admin findByUsername(String username);

    int deleteBatchRecord(List<Long> idList);

	List<Admin> findAllowed(Long schoolId);

	List<Admin> findArea(Long type);
	
	void lockUser(String username);
}