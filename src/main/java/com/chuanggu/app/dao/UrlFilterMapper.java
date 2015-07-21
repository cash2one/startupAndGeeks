package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.UrlFilter;

public interface UrlFilterMapper {
    int deleteByPrimaryKey(Long id);

	int insert(UrlFilter record);

	int insertSelective(UrlFilter record);

	UrlFilter selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UrlFilter record);

	int updateByPrimaryKey(UrlFilter record);
	
	List<UrlFilter> findAll();

	
}