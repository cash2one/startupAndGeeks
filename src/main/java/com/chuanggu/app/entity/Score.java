package com.chuanggu.app.entity;

import java.io.Serializable;

public class Score implements Serializable {
    private Long id;

    private Integer examId;

    private Double score;

    private Integer classId;

    private String stuname;
    
    
    //班级平均分
    private Double classAverageMark;
    
    
    //年级平均分
    private Double gradeAverageMark;
    
    private String courseName;
    
    private String className;
    
    private String title;
    //班级排名
    private Integer classRanking;
    
  //班级排名
    private Integer gradeRanking;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

	public Double getClassAverageMark() {
		return classAverageMark;
	}

	public void setClassAverageMark(Double classAverageMark) {
		this.classAverageMark = classAverageMark;
	}

	public Double getGradeAverageMark() {
		return gradeAverageMark;
	}

	public void setGradeAverageMark(Double gradeAverageMark) {
		this.gradeAverageMark = gradeAverageMark;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassRanking() {
		return classRanking;
	}

	public void setClassRanking(Integer classRanking) {
		this.classRanking = classRanking;
	}

	public Integer getGradeRanking() {
		return gradeRanking;
	}

	public void setGradeRanking(Integer gradeRanking) {
		this.gradeRanking = gradeRanking;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
    
    
}