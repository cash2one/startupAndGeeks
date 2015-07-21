package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Student;
import com.chuanggu.app.entity.Teacher;
import com.chuanggu.app.entity.UserGroup;

public interface UserGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
    
    List<UserGroup> findUserGroupList(UserGroup record);
    
    List<UserGroup> findOne(Long id);
    
    List<UserGroup> findStuAndTeaByID(UserGroup record);
    
    List<UserGroup> findUserGroupByTeaId(UserGroup createid);
}