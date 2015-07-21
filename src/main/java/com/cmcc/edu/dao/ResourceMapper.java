package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

	int insertSelective(Resource record);

	Resource selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Resource record);

	int updateByPrimaryKey(Resource record);
	
	int deleteByParentids(Resource record);
	
	List<Resource> findAll();

	
}