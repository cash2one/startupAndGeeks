package com.chuanggu.app.controller.project;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chuanggu.app.dto.ResponseMessage;
import com.chuanggu.app.entity.ProjectWithBLOBs;
import com.chuanggu.app.service.ProjectService;
import com.chuanggu.app.util.ResponseMsgUtil;


@RequestMapping(value = "/project")
public class ProjectController {
	private static Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectService ps;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage addProject(@Valid @RequestBody ProjectWithBLOBs project){
		ps.addProject(project);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage listProject(@Valid @RequestBody ProjectWithBLOBs project){
		ps.addProject(project);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage detailProject(@Valid @RequestBody ProjectWithBLOBs project){
		ps.addProject(project);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	@RequestMapping(value="/apply", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage applyRoadShow(@Valid @RequestBody ProjectWithBLOBs project){
		ps.addProject(project);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	
	@RequestMapping(value="/approve", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage approveRoadShow(@Valid @RequestBody ProjectWithBLOBs project){
		ps.addProject(project);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
}
