package com.chuanggu.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chuanggu.app.dto.ProjectDto;
import com.chuanggu.app.entity.Maker;
import com.chuanggu.app.entity.Project;
import com.chuanggu.app.entity.ProjectWithBLOBs;


/**
 * 路演项目
 * @author ryan
 *
 */
public interface ProjectService {
	/**
     * 
     * @param 
     * @param 
     */
	void addProject(ProjectWithBLOBs project);
	
	List<ProjectDto> getProjectList(String id);
	Project getProjectDetail(String id);
	
	void applyRoadShow(String id,Date applyFrom,Date applyTo);
	void approveRoadShow(String id,Date approveDate);

}
