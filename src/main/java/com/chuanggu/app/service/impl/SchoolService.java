package com.chuanggu.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.SchoolMapper;
import com.chuanggu.app.dao.TeacherMapper;
import com.chuanggu.app.entity.School;
import com.chuanggu.app.entity.Teacher;
import com.chuanggu.app.service.ISchoolService;
import com.chuanggu.app.util.pagehelper.PageHelper;


@Service
public class SchoolService implements ISchoolService{
	
    @Autowired
	private SchoolMapper schoolMapper;
    
    
    
    @Override
    public School findOne(Long id) {
    	List<School> schoolList = schoolMapper.findOne(id);
	       if(schoolList.size() == 0) {
	           return null;
	       }
	       return schoolList.get(0);
    }
    
    @Override
    public List<School> findSchoolList(School record) {
    	// TODO Auto-generated method stub
    	return schoolMapper.findSchoolList(record);
    }
    
    @Override
    public List<School> find(int pageNumber, int itemsOnPage, School t) {
    	PageHelper.startPage(pageNumber, itemsOnPage);
		return schoolMapper.findSchoolList(t);
    }
    
    @Override
    public List<School> findAll(int pageNumber, int itemsOnPage) {
    	// TODO Auto-generated method stub
    	return null;
    }
    
    @Override
    public List<School> findAllowed(int pageNumber, int itemsOnPage, Long id) {
    	// TODO Auto-generated method stub
    	return null;
    }
    
    @Override
    public int deleteSchool(Long id) {
    	// TODO Auto-generated method stub
    	return schoolMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public int insert(School record) {
    	// TODO Auto-generated method stub
    	return schoolMapper.insert(record);
    }
    
    @Override
    public int updateSchool(School record) {
    	// TODO Auto-generated method stub
    	return schoolMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public Integer findMaxSchoolId(String schoolCode) {
    	// TODO Auto-generated method stub
    	return schoolMapper.findMaxSchoolId(schoolCode);
    }
    
    @Override
    public School findDetailSchoolById(Long id) {
    	// TODO Auto-generated method stub
    	return schoolMapper.findDetailSchoolById(id);
    }
	

}
