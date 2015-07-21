package com.cmcc.edu.service;

import java.util.List;



public interface IPageService<T> {
	
	List<T> findAll(int pageNumber,int itemsOnPage);
	
	List<T> findAllowed(int pageNumber,int itemsOnPage,Long id);
	
	List<T> find(int pageNumber,int itemsOnPage,T t);
	

}
