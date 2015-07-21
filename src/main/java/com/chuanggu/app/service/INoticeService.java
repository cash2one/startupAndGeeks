package com.chuanggu.app.service;

import java.util.List;

import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.User;



public interface INoticeService extends IPageService<Notice> {
	
	public int insert(Notice record);
	
	public int update(Notice record);
	
	public int delete(Long id);
	
	public Notice find(Long id);
	
	public List<Notice> noticeReceiveList(Notice record);
	
	List<Notice> noticeSendList(Notice record);
	
	 List<Notice> findDaftList(Notice record);
	 
	 int updatePubStateById(Long id);
}
