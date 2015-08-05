package com.chuanggu.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.UserDao;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.exception.ServiceException;
import com.chuanggu.app.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Resource(shareable = true)
	private UserDao userDao;
	@Override
	public User findUserByLoginName(String name) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByEmail(String email) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
