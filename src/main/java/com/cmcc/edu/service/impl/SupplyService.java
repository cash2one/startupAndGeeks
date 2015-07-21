package com.cmcc.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.StudentMapper;
import com.cmcc.edu.dao.SupplyMapper;
import com.cmcc.edu.entity.Supply;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.ISupplyService;
import com.cmcc.edu.util.pagehelper.PageHelper;


@Service
public class SupplyService implements ISupplyService{

	@Autowired
	private SupplyMapper supplyMapper;
	
	
	@Override
	public List<Supply> find(int pageNumber, int itemsOnPage, Supply t) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return supplyMapper.findSupplyList(t);
	}
	
	@Override
	public List<Supply> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Supply> findAllowed(int pageNumber, int itemsOnPage, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Supply> findSupplyList(Supply record) {
		// TODO Auto-generated method stub
		return supplyMapper.findSupplyList(record);
	}
	
	@Override
	public List<Supply> findParentSupply(Supply type) {
		// TODO Auto-generated method stub
		return supplyMapper.findParentSupply(type);
	}
	
	
	@Override
	public int insert(Supply record) {
		// TODO Auto-generated method stub
		return supplyMapper.insert(record);
	}
	
	@Override
	public int updateSupply(Supply record) {
		// TODO Auto-generated method stub
		return supplyMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public Supply findOne(Long id) {
		// TODO Auto-generated method stub
		return supplyMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int deleteSupply(Long id) {
		// TODO Auto-generated method stub
		return supplyMapper.deleteByPrimaryKey(id);
	}
}
