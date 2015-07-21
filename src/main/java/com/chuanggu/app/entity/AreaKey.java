package com.chuanggu.app.entity;

import java.io.Serializable;

public class AreaKey implements Serializable {
    private Integer code;

    private Integer id;

    private static final long serialVersionUID = 1L;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}