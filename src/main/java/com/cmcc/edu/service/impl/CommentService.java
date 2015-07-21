package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.CommentMapper;
import com.cmcc.edu.dao.NoticeMapper;
import com.cmcc.edu.entity.Comment;
import com.cmcc.edu.entity.Notice;


import com.cmcc.edu.service.ICommentService;
import com.cmcc.edu.service.INoticeService;
import com.cmcc.edu.util.pagehelper.PageHelper;


@Service
public class CommentService implements ICommentService{
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public int insert(Comment record) {
		return commentMapper.insert(record);
	}

	@Override
	public int update(Comment record) {
		return commentMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Long id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Comment find(Long id) {
		return commentMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Comment> findAll(Comment record) {
		return commentMapper.commentSendList(record);
	}

	@Override
	public List<Comment> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> find(int pageNumber, int itemsOnPage,Comment t) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return commentMapper.commentSendList(t);
	}

	

	

	
	
	
}
