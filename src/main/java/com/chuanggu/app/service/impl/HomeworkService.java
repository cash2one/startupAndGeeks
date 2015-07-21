package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import com.chuanggu.app.dao.HomeworkMapper;
import com.chuanggu.app.entity.Homework;
import com.chuanggu.app.service.IHomeworkService;
import com.chuanggu.app.util.pagehelper.PageHelper;



@Service
public class HomeworkService implements IHomeworkService{
	
	@Autowired
	private HomeworkMapper homeworkMapper;

	@Override
	public int insert(Homework record) {
		return homeworkMapper.insert(record);
	}

	@Override
	public int update(Homework record) {
		return homeworkMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Integer id) {
		return homeworkMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Homework find(Integer id) {
		return homeworkMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Homework> homeworkList(Homework record) {
		return homeworkMapper.homeworkList(record);
	}

	/**
	 * 今日作业
	 */
	@Override
	public List<Homework> homeworkListNow(Homework record) {
		return homeworkMapper.homeworkListNow(record);
	}

	@Override
	public List<Homework> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Homework> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Homework> find(int pageNumber, int itemsOnPage, Homework t) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return  homeworkMapper.homeworkList(t);
	}



	

	
	
	
}
