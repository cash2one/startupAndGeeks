package com.chuanggu.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.ProjectDao;
import com.chuanggu.app.dto.ProjectDto;
import com.chuanggu.app.entity.Project;
import com.chuanggu.app.entity.ProjectWithBLOBs;
import com.chuanggu.app.service.ProjectService;


@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Override
	public void addProject(ProjectWithBLOBs project) {
		// TODO Auto-generated method stub
		projectDao.addProject(project);
	}

	@Override
	public List<ProjectDto> getProjectList(String id) {
		// TODO Auto-generated method stub
		return projectDao.getProjectList(id);
	}

	@Override
	public Project getProjectDetail(String id) {
		// TODO Auto-generated method stub
		return projectDao.getProjectDetail(id);
	}

	@Override
	public void applyRoadShow(String id, Date applyFrom,Date applyTo) {
		// TODO Auto-generated method stub
		projectDao.applyRoadShowDate(id,applyFrom, applyTo);
	}

	@Override
	public void approveRoadShow(String id, Date approveDate) {
		// TODO Auto-generated method stub
		projectDao.approveRoadShow(id, approveDate);
	}

}
