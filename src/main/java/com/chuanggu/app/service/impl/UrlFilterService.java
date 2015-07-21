package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.UrlFilterMapper;
import com.chuanggu.app.entity.UrlFilter;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.service.IUrlFilterService;
import com.chuanggu.app.service.IUserService;

@Service
public class UrlFilterService implements IUrlFilterService {

    @Autowired
    private UrlFilterMapper urlFilterMapper;

	@Override
	public int createUrlFilter(UrlFilter urlFilter) {	
		return urlFilterMapper.insert(urlFilter);
	}

	@Override
	public UrlFilter updateUrlFilter(UrlFilter urlFilter) {
		urlFilterMapper.updateByPrimaryKey(urlFilter);
		return urlFilter;
	}

	@Override
	public void deleteUrlFilter(Long urlFilterId) {
		urlFilterMapper.deleteByPrimaryKey(urlFilterId);		
	}

	@Override
	public UrlFilter findOne(Long urlFilterId) {
		UrlFilter urlFileter=urlFilterMapper.selectByPrimaryKey(urlFilterId);
		if(urlFileter==null){
			return null;
		}
		return urlFileter;
	}

	@Override
	public List<UrlFilter> findAll() {
		return urlFilterMapper.findAll();
	}

	
 
     
    
 

}