package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.ExamMapper;
import com.chuanggu.app.dao.ExamTypeMapper;
import com.chuanggu.app.dao.NoticeMapper;
import com.chuanggu.app.dao.ScoreMapper;
import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.ExamType;
import com.chuanggu.app.entity.Notice;


import com.chuanggu.app.entity.Score;
import com.chuanggu.app.service.IExamService;
import com.chuanggu.app.service.IExamTypeService;
import com.chuanggu.app.service.INoticeService;
import com.chuanggu.app.service.IScoreService;


@Service
public class ExamTypeService implements IExamTypeService{
	
	@Autowired
	private ExamTypeMapper examTypeMapper;

	@Override
	public int insert(ExamType record) {
		return examTypeMapper.insert(record);
	}

	@Override
	public int update(ExamType record) {
		return examTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Long id) {
		return examTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ExamType find(Long id) {
		return examTypeMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<ExamType> selectExamTypeList(ExamType record) {
		// TODO Auto-generated method stub
		return examTypeMapper.selectExamTypeList(record);
	}

	

	
	
	
}
