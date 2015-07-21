package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.User;

public interface UserDao {

    
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);
	int deleteBatchRecord(List<Long> idList);
}