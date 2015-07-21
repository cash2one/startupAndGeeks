package com.cmcc.edu.entity;

import java.io.Serializable;

public class CourseFather implements Serializable {
    private Integer id;

    private Integer number;

    private Integer courseId;

    private Integer courseSetupId;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseSetupId() {
        return courseSetupId;
    }

    public void setCourseSetupId(Integer courseSetupId) {
        this.courseSetupId = courseSetupId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}