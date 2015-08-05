package com.chuanggu.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.ProjectDao;
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

}
