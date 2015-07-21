package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Attendance;

public interface AttendanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);
    
    List<Attendance> selectByList(Attendance record);
}