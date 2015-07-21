package com.chuanggu.app.controller;

import com.chuanggu.app.service.IPageService;
import com.chuanggu.app.util.pagehelper.PageInfo;

public class PageController<T> {
	

	public  PageInfo<T> getRecord(PageInfo<T> pageInfo,Integer pageNumber,Integer itemsOnPage,IPageService<T> iPageService){
		if(pageNumber == null && itemsOnPage == null){
			if( pageInfo.getList().size() ==0) pageInfo.setPageNum(pageInfo.getPageNum()-1);
			pageInfo = new PageInfo<T>(iPageService.findAll(pageInfo.getPageNum(),pageInfo.getPageSize()));
		}
    	else {
    		pageInfo = new PageInfo<T>(iPageService.findAll(pageNumber,itemsOnPage));
	    	pageInfo.setPageNum(pageNumber);
	    	pageInfo.setPageSize(itemsOnPage);
	    	}
		return pageInfo;
	}
	
	
	public  PageInfo<T> getAllowedRecord(PageInfo<T> pageInfo,Integer pageNumber,Integer itemsOnPage,IPageService<T> iPageService,Long type){
		if(pageNumber == null && itemsOnPage == null){
			if( pageInfo.getList().size() ==0) pageInfo.setPageNum(pageInfo.getPageNum()-1);
    		pageInfo = new PageInfo<T>(iPageService.findAllowed(pageInfo.getPageNum(),pageInfo.getPageSize(),type));
    		}
    	else {
    		pageInfo = new PageInfo<T>(iPageService.findAllowed(pageNumber,itemsOnPage,type));
	    	pageInfo.setPageNum(pageNumber);
	    	pageInfo.setPageSize(itemsOnPage);
	    	}
		return pageInfo;
	}

	public  PageInfo<T> getByObject(PageInfo<T> pageInfo,Integer pageNumber,Integer itemsOnPage,IPageService<T> iPageService,T t){
		if(pageNumber == null && itemsOnPage == null){
			if(pageInfo.getList().size()==0){
				pageInfo.setPageNum(pageInfo.getPageNum()-1);
			}
			pageInfo = new PageInfo<T>(iPageService.find(pageInfo.getPageNum()-1,pageInfo.getPageSize(),t));
		}else{
			pageInfo = new PageInfo<T>(iPageService.find(pageNumber,itemsOnPage,t));
	    	pageInfo.setPageNum(pageNumber);
	    	pageInfo.setPageSize(itemsOnPage);
		}	
		return pageInfo;
	}

}
