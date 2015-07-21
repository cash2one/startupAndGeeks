package com.cmcc.edu.service;

import java.util.List;

import com.cmcc.edu.entity.Homework;



public interface IHomeworkService extends IPageService<Homework>{
	public int insert(Homework record);
	
	public int update(Homework record);
	
	public int delete(Integer id);
	
	public Homework find(Integer id);
	
	public List<Homework> homeworkList(Homework record);
	/**
	 * 今日作业
	 * @param record
	 * @return
	 */
	public List<Homework> homeworkListNow(Homework record);
}
