package com.chuanggu.app.entity;

public class Investor {
    private String investor_id;

    private String position;

    private String introduction;

    private String education;

    private String phone;

    private String invest_area;

    private String experience;

    private String invest_stage;

    private String invest_company;

    private String cash;

    private String success_example;

    private Byte verify;

    private String state;

    public String getInvestor_id() {
        return investor_id;
    }

    public void setInvestor_id(String investor_id) {
        this.investor_id = investor_id == null ? null : investor_id.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getInvest_area() {
        return invest_area;
    }

    public void setInvest_area(String invest_area) {
        this.invest_area = invest_area == null ? null : invest_area.trim();
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    public String getInvest_stage() {
        return invest_stage;
    }

    public void setInvest_stage(String invest_stage) {
        this.invest_stage = invest_stage == null ? null : invest_stage.trim();
    }

    public String getInvest_company() {
        return invest_company;
    }

    public void setInvest_company(String invest_company) {
        this.invest_company = invest_company == null ? null : invest_company.trim();
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash == null ? null : cash.trim();
    }

    public String getSuccess_example() {
        return success_example;
    }

    public void setSuccess_example(String success_example) {
        this.success_example = success_example == null ? null : success_example.trim();
    }

    public Byte getVerify() {
        return verify;
    }

    public void setVerify(Byte verify) {
        this.verify = verify;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}