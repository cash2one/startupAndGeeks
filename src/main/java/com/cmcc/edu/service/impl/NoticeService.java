package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.NoticeMapper;
import com.cmcc.edu.entity.Notice;


import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.INoticeService;
import com.cmcc.edu.util.pagehelper.PageHelper;


@Service
public class NoticeService implements INoticeService{
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public int insert(Notice record) {
		return noticeMapper.insert(record);
	}

	@Override
	public int update(Notice record) {
		return noticeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Long id) {
		return noticeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Notice find(Long id) {
		return noticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Notice> noticeReceiveList(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.noticeReceiveList(record);
	}
	
	@Override
	public List<Notice> noticeSendList(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.noticeSendList(record);
	}

	
	@Override
	public List<Notice> findDaftList(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.findDaftList(record);
	}
	
	
	@Override
	public List<Notice> find(int pageNumber, int itemsOnPage, Notice t) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNumber, itemsOnPage);
		if(t.getSearchType().equals("receive")){
			return noticeMapper.noticeReceiveList(t);
		}else if(t.getSearchType().equals("send")){
			return noticeMapper.noticeSendList(t);
		}else if(t.getSearchType().equals("draft")){
			return noticeMapper.findDaftList(t);
		}
		return null;
	}
	
	@Override
	public List<Notice> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Notice> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public int updatePubStateById(Long id) {
		// TODO Auto-generated method stub
		return noticeMapper.updatePubStateById(id);
	}

	
	
	
}
