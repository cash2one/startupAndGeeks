package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.CommentMapper;
import com.chuanggu.app.dao.NoticeMapper;
import com.chuanggu.app.entity.Comment;
import com.chuanggu.app.entity.Notice;


import com.chuanggu.app.service.ICommentService;
import com.chuanggu.app.service.INoticeService;
import com.chuanggu.app.util.pagehelper.PageHelper;


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
