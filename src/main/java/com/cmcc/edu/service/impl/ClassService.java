package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.ClassMapper;
import com.cmcc.edu.entity.Class;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.util.pagehelper.PageHelper;


@Service
public class ClassService implements IClassService{
	
	@Autowired
	private ClassMapper classMapper;

	@Override
	public int insert(Class record) {
		return classMapper.insert(record);
	}

	@Override
	public int updateClass(Class record) {
		return classMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteClass(Integer id) {
		return classMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Class find(Integer id) {
		return classMapper.selectByTeacher(id);
	}

	@Override
	public List<Class> classList(Class record) {
		return classMapper.classList(record);
	}

	@Override
	public List<Class> findAll(int pageNumber,int itemsOnPage) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		List<Class> list = classMapper.findAll();
		return list;
	}

	@Override
	public Class findOne(Integer id) {
		return classMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteBatchRecord(List<Integer> idList) {
		
		return classMapper.deleteBatchRecord(idList);
	}
	
	@Override
	public List<Class> selectClassBygrade(Class record) {
		return classMapper.selectClassBygrade(record);
	}

	@Override
	public int insertBatch(List<Class> idList) {
		return classMapper.insertBatch(idList);
	}

	@Override
	public List<Class> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class> find(int pageNumber, int itemsOnPage, Class t) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
