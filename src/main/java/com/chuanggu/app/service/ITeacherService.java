package com.chuanggu.app.service;

import java.util.List;
import java.util.Set;

import com.chuanggu.app.entity.Teacher;

public interface ITeacherService extends IPageService<Teacher>{
	/**
     * 创建老师
     * @param teacher
     */
    public int createTeacher(Teacher user);

    public int updateTeacher(Teacher user);

    public int deleteTeacher(Long userId);


    Teacher findOne(Long userId);

    List<Teacher> findAll();
    
    public int deleteBatchRecord(List<Long> idList);
    
    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);


    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public Teacher findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);
    
    /**
     * excel教师信息导入
     */
    public void formExcel(String filename);
    /**
     * 批量上传教师信息
     */
    public void batch(String information);
    
    List<Teacher> findTeacherByIds(String teacherIds);
    
    List<Teacher> findTeacherByGradeId(Integer gradeId);
    
    List<Teacher> findTeacherBook(Teacher record);
    
    public int updateTeacherByPersonal(Teacher user);

}
