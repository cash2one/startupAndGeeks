package com.chuanggu.app.dao;

import java.util.Date;
import java.util.List;

import com.chuanggu.app.dto.ProjectDto;
import com.chuanggu.app.entity.Project;
import com.chuanggu.app.entity.ProjectWithBLOBs;

public interface ProjectDao {
    int deleteByPrimaryKey(Integer id);
    
    int insertSelective(ProjectWithBLOBs record);

    ProjectWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProjectWithBLOBs record);

    int updateByPrimaryKey(Project record);
    
    /**
     * 申请路演时间
     */
    void applyRoadShowDate(String projectId,Date applyFrom,Date applyTo);
    /**
     * 审核并确认路演时间的具体时间
     */
    void approveRoadShow(String projectId,Date confirmDate);
    /**
     * 获取路演项目的详细信息
     */
    ProjectWithBLOBs getProjectDetail(String projectId);
    /**
     * 添加路演项目信息
     */
    int addProject(ProjectWithBLOBs record);
    /**
     * 获取个人名下所有项目
     */
    List<ProjectDto> getProjectList(String userId);
}