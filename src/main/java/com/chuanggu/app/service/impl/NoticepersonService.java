package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.NoticeMapper;
import com.chuanggu.app.dao.NoticepersonMapper;
import com.chuanggu.app.entity.Noticeperson;
import com.chuanggu.app.service.INoticeService;
import com.chuanggu.app.service.INoticepersonService;
@Service
public class NoticepersonService implements INoticepersonService{
	@Autowired
	private NoticepersonMapper noticepersonMapper;
	
	@Override
	public List<Noticeperson> noticePersonList(Long noticeId) {
		// TODO Auto-generated method stub
		return noticepersonMapper.noticePersonList(noticeId);
	}
	
	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return noticepersonMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Noticeperson find(Long id) {
		// TODO Auto-generated method stub
		return noticepersonMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int insert(Noticeperson record) {
		// TODO Auto-generated method stub
		return noticepersonMapper.insertSelective(record);
	}
	
	@Override
	public int update(Noticeperson record) {
		// TODO Auto-generated method stub
		return noticepersonMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public int deleteByNoticeId(Long noticeId) {
		// TODO Auto-generated method stub
		return noticepersonMapper.deleteByNoticeId(noticeId);
	}
	
	@Override
	public List<Noticeperson> findPersonBynoticeId(Noticeperson record) {
		// TODO Auto-generated method stub
		return noticepersonMapper.findPersonBynoticeId(record);
	}
	
	@Override
	public int updateIsreadById(Long id) {
		// TODO Auto-generated method stub
		return noticepersonMapper.updateIsreadById(id);
	}
	
}
