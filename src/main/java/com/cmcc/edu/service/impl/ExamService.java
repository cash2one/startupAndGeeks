package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.CourseMapper;
import com.cmcc.edu.dao.ExamMapper;
import com.cmcc.edu.dao.NoticeMapper;
import com.cmcc.edu.dao.ScoreMapper;
import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.Notice;


import com.cmcc.edu.entity.Score;
import com.cmcc.edu.service.IExamService;
import com.cmcc.edu.service.INoticeService;
import com.cmcc.edu.service.IScoreService;
import com.cmcc.edu.util.pagehelper.PageHelper;


@Service
public class ExamService implements IExamService{
	
	@Autowired
	private ExamMapper examMapper;
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public int insert(Exam record) {
		return examMapper.insert(record);
	}

	@Override
	public int update(Exam record) {
		return examMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delete(Long id) {
		return examMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Exam find(Long id) {
		return examMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Exam> selectExamList(Exam record) {
		
		return examMapper.selectExamList(record);
	}
	
	
	
	@Override
	public List<Exam> selectExamForScore(Exam record) {
		// TODO Auto-generated method stub
		return examMapper.selectExamForScore(record);
	}

	
	@Override
	public int updateEnterStateById(Exam record) {
		// TODO Auto-generated method stub
		return examMapper.updateEnterStateById(record);
	}
	
	@Override
	public List<Exam> selectExamDraftList(Exam record) {
		// TODO Auto-generated method stub
		return examMapper.selectExamDraftList(record);
	}
	

	@Override
	public List<Exam> find(int pageNumber, int itemsOnPage, Exam t) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		// TODO Auto-generated method stub
		if(t.getExamtype().equals("examlist")){
			return examMapper.selectExamList(t);
		}else if(t.getExamtype().equals("draftlist")){
			return examMapper.selectExamDraftList(t);
		}else if(t.getExamtype().equals("examscore")){
			return examMapper.selectExamForScore(t);
		}
		return null;
	}
	
	@Override
	public List<Exam> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Exam> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public int updatePubStateById(Long id) {
		// TODO Auto-generated method stub
		return examMapper.updatePubStateById(id);
	}
	
	

	
	
	
}
