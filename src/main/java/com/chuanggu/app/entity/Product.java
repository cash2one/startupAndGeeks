package com.chuanggu.app.entity;

import java.util.Date;

public class Product {
    private String product_id;

    private String name;

    private String amout;

    private String total_amout;

    private String support;

    private String originator;

    private String picture_url;

    private String state;

    private Date time_start;

    private Date time_end;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id == null ? null : product_id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAmout() {
        return amout;
    }

    public void setAmout(String amout) {
        this.amout = amout == null ? null : amout.trim();
    }

    public String getTotal_amout() {
        return total_amout;
    }

    public void setTotal_amout(String total_amout) {
        this.total_amout = total_amout == null ? null : total_amout.trim();
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support == null ? null : support.trim();
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator == null ? null : originator.trim();
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url == null ? null : picture_url.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getTime_start() {
        return time_start;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

    public Date getTime_end() {
        return time_end;
    }

    public void setTime_end(Date time_end) {
        this.time_end = time_end;
    }
}