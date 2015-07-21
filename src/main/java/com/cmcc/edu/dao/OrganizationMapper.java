package com.cmcc.edu.dao;

import java.util.List;
import java.util.Map;

import com.cmcc.edu.entity.Organization;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Organization record);

	int insertSelective(Organization record);

	Organization selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Organization record);

	int updateByPrimaryKey(Organization record);
	
	int deleteByPrientids(Organization record);
	
	List<Organization> findOne(Long organizationId);
	
	List<Organization> findAll();
	
	List<Organization> findAllWithExclude(Organization record);
	
	int moveSource(Map<String,Object> map);
	
	int moveSourceDescendants(Map<String,Object> map);

	
}