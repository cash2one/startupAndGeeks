package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.UrlFilterMapper;
import com.cmcc.edu.entity.UrlFilter;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IUrlFilterService;
import com.cmcc.edu.service.IUserService;

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