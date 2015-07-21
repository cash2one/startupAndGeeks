package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.GradeMapper;
import com.cmcc.edu.entity.Grade;
import com.cmcc.edu.service.IGradeService;
import com.cmcc.edu.util.pagehelper.PageHelper;


@Service
public class GradeService implements IGradeService{
	
	@Autowired
	private GradeMapper gradeMapper;

	@Override
	public int insert(Grade record) {
		return gradeMapper.insertSelective(record);
	}

	@Override
	public int updateGrade(Grade record) {
		return gradeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteGrade(Integer id) {
		return gradeMapper.deleteByPrimaryKey(id);
	}

	

	@Override
	public List<Grade> noticeList(Grade record) {
		return gradeMapper.gradeList(record);
	}

	@Override
	public Grade selectByPrimaryKey(Integer id) {
		return gradeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Grade> findAll(int pageNumber, int itemsOnPage) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return gradeMapper.findAll();
	}
	
	@Override
	public List<Grade> findAll() {
		return gradeMapper.findAll();
	}

	@Override
	public Grade findOne(Integer id) {
		return gradeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBatchRecord(List<Integer> idList) {
		
		return gradeMapper.deleteBatchRecord(idList);
	}
	
	
	@Override
	public List<Grade> selectGradeBySchool(Long schoolId) {
		return gradeMapper.selectGradeBySchool(schoolId);
	}

	@Override
	public int insertBatch(List<Grade> idList) {
		return gradeMapper.insertBatch(idList);
	}

	@Override
	public List<Grade> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Grade> find(int pageNumber, int itemsOnPage, Grade t) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
