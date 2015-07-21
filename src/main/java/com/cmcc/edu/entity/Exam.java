package com.cmcc.edu.entity;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {
    private Long id;

    private String title;

    private Date publishtime;

    private Integer examtypeId;

    private Integer courseId;

    private String examstate;

    private Long sendId;

    private String content;

    private String sendtype;

    private Date sendtime;

    private String hidden;

    private String isread;

    private Date starttime;

    private Date endtime;

    private String classid;

    private Integer gradeId;

    private String pubstate;

    private String enterstate;

    private String resname;
    
    private String examstatename;
    
    private String datenum;
    
    private Long enterId;

    private Date entertime;
    
    private String entername;
    
    private String examtypeName;
    
    private String couseName;
    
    private String enterPerson;
    
    private String examTime;
    
    private String examtype;
    
    private String className;
    
    private String time;
    

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getExamtypeId() {
        return examtypeId;
    }

    public void setExamtypeId(Integer examtypeId) {
        this.examtypeId = examtypeId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getExamstate() {
        return examstate;
    }

    public void setExamstate(String examstate) {
        this.examstate = examstate == null ? null : examstate.trim();
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSendtype() {
        return sendtype;
    }

    public void setSendtype(String sendtype) {
        this.sendtype = sendtype == null ? null : sendtype.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden == null ? null : hidden.trim();
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread == null ? null : isread.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getPubstate() {
        return pubstate;
    }

    public void setPubstate(String pubstate) {
        this.pubstate = pubstate == null ? null : pubstate.trim();
    }

    public String getEnterstate() {
        return enterstate;
    }

    public void setEnterstate(String enterstate) {
        this.enterstate = enterstate == null ? null : enterstate.trim();
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname == null ? null : resname.trim();
    }

	public String getExamstatename() {
		return examstatename;
	}

	public void setExamstatename(String examstatename) {
		this.examstatename = examstatename;
	}

	public String getDatenum() {
		return datenum;
	}

	public void setDatenum(String datenum) {
		this.datenum = datenum;
	}

	public Long getEnterId() {
		return enterId;
	}

	public void setEnterId(Long enterId) {
		this.enterId = enterId;
	}

	public Date getEntertime() {
		return entertime;
	}

	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}

	public String getEntername() {
		return entername;
	}

	public void setEntername(String entername) {
		this.entername = entername;
	}

	public String getExamtypeName() {
		return examtypeName;
	}

	public void setExamtypeName(String examtypeName) {
		this.examtypeName = examtypeName;
	}

	public String getCouseName() {
		return couseName;
	}

	public void setCouseName(String couseName) {
		this.couseName = couseName;
	}

	public String getEnterPerson() {
		return enterPerson;
	}

	public void setEnterPerson(String enterPerson) {
		this.enterPerson = enterPerson;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getExamtype() {
		return examtype;
	}

	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
    
    
    
}