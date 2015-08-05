package com.chuanggu.app.service;

import com.chuanggu.app.entity.User;
import com.chuanggu.app.exception.ServiceException;


public interface UserService {
	User findUserByLoginName(String name) throws ServiceException;

	User findUserByEmail(String email) throws ServiceException;
}
