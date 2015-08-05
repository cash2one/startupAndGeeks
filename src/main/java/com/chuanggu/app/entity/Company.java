package com.chuanggu.app.entity;

import java.util.Date;

public class Company {
    private String company_id;

    private String name;

    private Date establish_time;

    private String address;

    private String industry;

    private String stage;

    private String email;

    private String logo;

    private String website;

    private String tag;

    private String financing_or_not;

    private String financing_stage;

    private String financing_exp;

    private String introduction;

    private String group;

    private String state;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id == null ? null : company_id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getEstablish_time() {
        return establish_time;
    }

    public void setEstablish_time(Date establish_time) {
        this.establish_time = establish_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage == null ? null : stage.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getFinancing_or_not() {
        return financing_or_not;
    }

    public void setFinancing_or_not(String financing_or_not) {
        this.financing_or_not = financing_or_not == null ? null : financing_or_not.trim();
    }

    public String getFinancing_stage() {
        return financing_stage;
    }

    public void setFinancing_stage(String financing_stage) {
        this.financing_stage = financing_stage == null ? null : financing_stage.trim();
    }

    public String getFinancing_exp() {
        return financing_exp;
    }

    public void setFinancing_exp(String financing_exp) {
        this.financing_exp = financing_exp == null ? null : financing_exp.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}