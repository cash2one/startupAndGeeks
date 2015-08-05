package com.chuanggu.app.entity;

import java.util.Date;

public class Support {
    private String support_id;

    private String amout;

    private String number;

    private String limit_number;

    private String introduction;

    private String fee;

    private String state;

    private Date deliver_time;

    public String getSupport_id() {
        return support_id;
    }

    public void setSupport_id(String support_id) {
        this.support_id = support_id == null ? null : support_id.trim();
    }

    public String getAmout() {
        return amout;
    }

    public void setAmout(String amout) {
        this.amout = amout == null ? null : amout.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getLimit_number() {
        return limit_number;
    }

    public void setLimit_number(String limit_number) {
        this.limit_number = limit_number == null ? null : limit_number.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee == null ? null : fee.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getDeliver_time() {
        return deliver_time;
    }

    public void setDeliver_time(Date deliver_time) {
        this.deliver_time = deliver_time;
    }
}