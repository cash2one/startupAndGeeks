package com.chuanggu.app.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class AttenceTime implements Serializable {
    private Long id;

    private Time arrivetime;

    private Time leavetime;

    private Integer classId;

    private Integer gradeId;

    private Long schoolId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(Time arrivetime) {
        this.arrivetime = arrivetime;
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

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}