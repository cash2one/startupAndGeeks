package com.chuanggu.app.entity;

import java.io.Serializable;
import java.util.List;

public class CourseSorting  implements Serializable {
	
	private Integer week;
	
	private List<CourseSetup> list;

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public List<CourseSetup> getList() {
		return list;
	}

	public void setList(List<CourseSetup> list) {
		this.list = list;
	}
	
	

}
