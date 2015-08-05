package com.chuanggu.app.entity;

import java.util.Date;

public class FinanceProcess {
    private String financing_id;

    private String amount;

    private String stage;

    private String company;

    private Date time;

    public String getFinancing_id() {
        return financing_id;
    }

    public void setFinancing_id(String financing_id) {
        this.financing_id = financing_id == null ? null : financing_id.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage == null ? null : stage.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}