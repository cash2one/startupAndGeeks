package com.cmcc.edu.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Admin extends User implements Serializable {
 


    private Boolean free;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date validity;

	private String telephone;

    private String email;

    private String createName;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private static final long serialVersionUID = 1L;
    
    private Long schoolId; 

 

    public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}


    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}