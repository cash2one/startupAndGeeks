package com.chuanggu.app.entity;

import java.util.Date;

public class Project {

    private String projectId;

    private String projectSlogan;

    private String projectName;

    private String locateCity;

    private String conductUrl;

    private String projectLogoUrl;

    private String businessPlanUrl;

    private String financeTarget;

    private String tranferSharePercent;

    private Date applyFrom;
    
    private Date applyTo;

    private Date confirmDate;

    private Date createTime;

    private Date updateTime;

    private String userId;

    private String state;

    public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectSlogan() {
		return projectSlogan;
	}

	public void setProjectSlogan(String projectSlogan) {
		this.projectSlogan = projectSlogan;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLocateCity() {
		return locateCity;
	}

	public void setLocateCity(String locateCity) {
		this.locateCity = locateCity;
	}

	public String getConductUrl() {
		return conductUrl;
	}

	public void setConductUrl(String conductUrl) {
		this.conductUrl = conductUrl;
	}

	public String getProjectLogoUrl() {
		return projectLogoUrl;
	}

	public void setProjectLogoUrl(String projectLogoUrl) {
		this.projectLogoUrl = projectLogoUrl;
	}

	public String getBusinessPlanUrl() {
		return businessPlanUrl;
	}

	public void setBusinessPlanUrl(String businessPlanUrl) {
		this.businessPlanUrl = businessPlanUrl;
	}

	public String getFinanceTarget() {
		return financeTarget;
	}

	public void setFinanceTarget(String financeTarget) {
		this.financeTarget = financeTarget;
	}

	public String getTranferSharePercent() {
		return tranferSharePercent;
	}

	public void setTranferSharePercent(String tranferSharePercent) {
		this.tranferSharePercent = tranferSharePercent;
	}

	public Date getApplyFrom() {
		return applyFrom;
	}

	public void setApplyFrom(Date applyFrom) {
		this.applyFrom = applyFrom;
	}

	public Date getApplyTo() {
		return applyTo;
	}

	public void setApplyTo(Date applyTo) {
		this.applyTo = applyTo;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}