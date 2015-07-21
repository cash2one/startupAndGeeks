package com.chuanggu.app.service;

import java.util.List;
import java.util.Set;

import com.chuanggu.app.entity.Admin;

public interface IAdminService extends IPageService<Admin>{
	/**
     * 创建管理员
     * @param admin
     */
    public int createAdmin(Admin user);

    public int updateAdmin(Admin user);

    public int deleteAdmin(Long userId);


    Admin findOne(Long userId);

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
    public Admin findByUsername(String username);

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
    
    
    public int updateAdminByPersonal(Admin user);
    
}
