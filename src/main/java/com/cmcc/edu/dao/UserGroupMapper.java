package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.Teacher;
import com.cmcc.edu.entity.UserGroup;

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