package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.User;

public interface UserDao {

    
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);
	int deleteBatchRecord(List<Long> idList);
}