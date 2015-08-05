package com.chuanggu.app.entity;

public class ProjectWithBLOBs extends Project {
    private String projectDesc;

    private String marketAnalysis;

    private String reqiuirementPosition;

    private String profitPattern;

    private String developPlan;

    private String extra;

    public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getMarketAnalysis() {
		return marketAnalysis;
	}

	public void setMarketAnalysis(String marketAnalysis) {
		this.marketAnalysis = marketAnalysis;
	}

	public String getReqiuirementPosition() {
		return reqiuirementPosition;
	}

	public void setReqiuirementPosition(String reqiuirementPosition) {
		this.reqiuirementPosition = reqiuirementPosition;
	}

	public String getProfitPattern() {
		return profitPattern;
	}

	public void setProfitPattern(String profitPattern) {
		this.profitPattern = profitPattern;
	}

	public String getDevelopPlan() {
		return developPlan;
	}

	public void setDevelopPlan(String developPlan) {
		this.developPlan = developPlan;
	}

	public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}