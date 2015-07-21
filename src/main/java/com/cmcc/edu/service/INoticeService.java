package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.Notice;
import com.cmcc.edu.entity.User;



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
