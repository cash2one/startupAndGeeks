package com.cmcc.edu.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Attendance implements Serializable {
    private Long id;

    private Date checkdate;

    private Time arrivetime;

    private Time leavetime;

    private Integer classId;

    private Integer stuId;

    private static final long serialVersionUID = 1L;
    
    private String stuids;//页面查询
    private String stateStr;//考勤管理中的状态   ：迟到/早退/正常
    private String time;
    private String late;//迟到天数
    private String leave;//早退天数
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    public java.sql.Time getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(java.sql.Time arrivetime) {
        this.arrivetime = arrivetime ;
    }

    public Time getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Time leavetime) {
        this.leavetime = leavetime;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getStuids() {
		return stuids;
	}

	public void setStuids(String stuids) {
		this.stuids = stuids;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLate() {
		return late;
	}

	public void setLate(String late) {
		this.late = late;
	}

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"time\":\"" + time +
                "\", \"name\":\"" + name + 
                "\", \"arrivetime\":\"" + arrivetime + 
                "\", \"leavetime\":\"" + leavetime +
                "\", \"late\":\"" + late + 
                "\", \"leave\":\"" + leave + 
                "\",\"stateStr\":\"" + stateStr +
                "\"}";
    }
}