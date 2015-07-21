package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.UrlFilter;

public interface IUrlFilterService {

	public int createUrlFilter(UrlFilter urlFilter);	
	
	public UrlFilter updateUrlFilter(UrlFilter urlFilter);
	
	public void deleteUrlFilter(Long urlFilterId);
	
	public UrlFilter findOne(Long urlFilterId);
	
	public List<UrlFilter> findAll();
    
   
}
