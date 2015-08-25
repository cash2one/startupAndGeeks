package com.chuanggu.app.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.InvestorDao;
import com.chuanggu.app.dao.MakerDao;
import com.chuanggu.app.dao.SmartDao;
import com.chuanggu.app.dao.UserDao;
import com.chuanggu.app.entity.Investor;
import com.chuanggu.app.entity.Maker;
import com.chuanggu.app.entity.Smart;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.exception.ServiceException;
import com.chuanggu.app.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Resource(shareable = true)
	private UserDao userDao;
	
	@Resource(shareable = true)
	private MakerDao makerDao;
	
	@Resource(shareable = true)
	private SmartDao smartDao;
	
	@Resource(shareable = true)
	private InvestorDao investorDao;
	
	@Override
	public Boolean findUserByLoginName(String name) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean findUserByEmail(String email) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Smart> getSmartList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return smartDao.getSmartList(param);
	}

	@Override
	public Smart getSmartDetail(String id) {
		// TODO Auto-generated method stub
		return smartDao.getSmartDetail(id);
	}

	@Override
	public List<Maker> getMakerList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return makerDao.getMakerList(param);
	}

	@Override
	public Maker getMakerDetail(String id) {
		// TODO Auto-generated method stub
		return makerDao.getMakerDetail(id);
	}

	@Override
	public List<Investor> getInvestorList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return investorDao.getInvestorList(param);
	}

	@Override
	public Smart getInvestorDetail(String id) {
		// TODO Auto-generated method stub
		return investorDao.getInvestorDetail(id);
	}
	
	

}
