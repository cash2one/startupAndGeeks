package com.chuanggu.app.dao;

import org.springframework.stereotype.Repository;

import com.chuanggu.app.entity.User;

@Repository
public interface UserDao {
	User findUserByLoginName(String name);
	User findUserByEmail(String email);

}
