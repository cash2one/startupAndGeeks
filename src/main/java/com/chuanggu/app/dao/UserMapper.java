package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.User;

public interface UserMapper {
//    int deleteByPrimaryKey(Long id);
//
//	int insert(User record);
//
//	int insertSelective(User record);
//
//	User selectByPrimaryKey(Long id);
//
//	int updateByPrimaryKeySelective(User record);
//
//	int updateByPrimaryKey(User record);
//	
//	
//	int createUser(User user);
//    int updateUser(User user);
//    void deleteUser(Long userId);
//
//    List<User> findOne(Long userId);
//
//    List<User> findAll();

    User findByUsername(String username);
    
//    int deleteBatchRecord(List<Long> idList);
	
}