package com.cmcc.edu.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Repository
public class Class implements Serializable {
    private Integer id;

    private String name;

    private Integer state;

    private Long teacherId;

    private Integer gradeId;

	private String classOrder;

    private Long schoolId;
    
    private String createName;
    
    private String gradeName;

   	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
       
    private List<Integer> idList ;

    public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }


    public String getClassOrder() {
        return classOrder;
    }

    public void setClassOrder(String classOrder) {
        this.classOrder = classOrder == null ? null : classOrder.trim();
    }
    
    public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}