package com.chuanggu.app.service;

import java.util.List;
import java.util.Map;

import com.chuanggu.app.entity.Investor;
import com.chuanggu.app.entity.Maker;
import com.chuanggu.app.entity.Smart;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.exception.ServiceException;


public interface UserService {
	Boolean findUserByLoginName(String name) throws ServiceException;

	Boolean findUserByEmail(String email) throws ServiceException;
	
	List<Smart> getSmartList(Map<String,String> param);
	Smart getSmartDetail(String id);
	
	List<Maker> getMakerList(Map<String,String> param);
	Maker getMakerDetail(String id);
	
	List<Investor> getInvestorList(Map<String,String> param);
	Smart getInvestorDetail(String id);
}
