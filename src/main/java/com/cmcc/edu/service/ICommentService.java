package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Comment;
import com.cmcc.edu.entity.Notice;



public interface ICommentService extends IPageService<Comment> {
	
	public int insert(Comment record);
	
	public int update(Comment record);
	
	public int delete(Long id);
	
	public Comment find(Long id);
	
	
	List<Comment> findAll(Comment record);
	

	
}
