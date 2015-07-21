package com.chuanggu.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Grade implements Serializable {
    private Integer id;

    private Long schoolId; 

    private String name;

    private String gradeOrder;

    private String state;
    
    private String createName;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    private List<Integer> idList ;

    public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGradeOrder() {
        return gradeOrder;
    }

    public void setGradeOrder(String gradeOrder) {
        this.gradeOrder = gradeOrder == null ? null : gradeOrder.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
    public String getCreateName() {
  		return createName;
  	}

  	public void setCreateName(String createName) {
  		this.createName = createName;
  	}

  	public Date getCreateTime() {
  		return createTime;
  	}

  	public void setCreateTime(Date createTime) {
  		this.createTime = createTime;
  	}

}