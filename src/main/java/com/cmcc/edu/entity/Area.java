package com.cmcc.edu.entity;

import java.io.Serializable;

public class Area extends AreaKey implements Serializable {
    private String areaName;

    private Integer parentCode;

    private static final long serialVersionUID = 1L;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }
}