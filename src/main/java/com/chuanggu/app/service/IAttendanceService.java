package com.chuanggu.app.service;

import java.util.List;
import java.util.Set;

import com.chuanggu.app.entity.Admin;
import com.chuanggu.app.entity.Attendance;
import com.chuanggu.app.entity.Teacher;

public interface IAttendanceService extends IPageService<Attendance>{
	/**
	 * 考勤管理   日考勤，
	 * @param attendance
	 * @return
	 */
	public List<Attendance> selectByList(Attendance attendance);
	/**
	 * 月考勤
	 * @param attendance
	 * @return
	 */
	public List<Attendance> selectByListMonth(Attendance attendance);
	
	public List<Attendance> findByMonth(int pageNumber, int itemsOnPage, Attendance t);
	
}
