package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.Noticeperson;



public interface INoticepersonService {
	
    public int insert(Noticeperson record);
	
	public int update(Noticeperson record);
	
	public int delete(Long id);
	
	public Noticeperson find(Long id);
	
	List<Noticeperson> noticePersonList(Long noticeId);
	
	int deleteByNoticeId(Long noticeId);
	
	List<Noticeperson> findPersonBynoticeId(Noticeperson record);
	
	int updateIsreadById(Long id);
}
