package com.chuanggu.app.dto;

import com.chuanggu.app.util.ItemState;

public class ProjectDto {

	private String projectId;

	private String projectName;

	private String projectLogoUrl;

	private String financeTarget;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLogoUrl() {
		return projectLogoUrl;
	}

	public void setProjectLogoUrl(String projectLogoUrl) {
		this.projectLogoUrl = projectLogoUrl;
	}

	public String getFinanceTarget() {
		return financeTarget;
	}

	public void setFinanceTarget(String financeTarget) {
		this.financeTarget = financeTarget;
	}

	private String state;

	public String getState() {
		return ItemState.valueOf(state).getName();
	}

	public void setState(String state) {
		this.state = state;
	}

}
