package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.ExamMapper;
import com.cmcc.edu.dao.ExamTypeMapper;
import com.cmcc.edu.dao.NoticeMapper;
import com.cmcc.edu.dao.ScoreMapper;
import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.ExamType;
import com.cmcc.edu.entity.Notice;


import com.cmcc.edu.entity.Score;
import com.cmcc.edu.service.IExamService;
import com.cmcc.edu.service.IExamTypeService;
import com.cmcc.edu.service.INoticeService;
import com.cmcc.edu.service.IScoreService;


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
