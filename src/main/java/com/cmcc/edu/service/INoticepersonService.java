package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Notice;
import com.cmcc.edu.entity.Noticeperson;



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
