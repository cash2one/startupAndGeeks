package com.cmcc.edu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class Student implements Serializable {
    private Long id;

    private String name;

    private Integer classId;

    private Integer gradeId;

    private String sex;

    private Date birthday;

    private String mobile;
    
    private Long schoolId;
    
    private Long stuId;
    
    private String schname;
    
    private String graname;
    
    private String classname;
    
    private String stuname;
    
    private String nickname;
    
    private String strName;
    
    List<Object> strlist=new ArrayList<Object>();
    
    
    private String time;
    
    private String stuno;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getStuId() {
		return stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public String getSchname() {
		return schname;
	}

	public void setSchname(String schname) {
		this.schname = schname;
	}

	public String getGraname() {
		return graname;
	}

	public void setGraname(String graname) {
		this.graname = graname;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	
	public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public List<Object> getStrlist() {
		return strlist;
	}

	public void setStrlist(List<Object> strlist) {
		this.strlist = strlist;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStuno() {
		return stuno;
	}

	public void setStuno(String stuno) {
		this.stuno = stuno;
	}

	

    
    
}