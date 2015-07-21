package com.cmcc.edu.service;

import java.util.List;
import java.util.Set;

import com.cmcc.edu.entity.Role;

public interface IRoleService extends IPageService<Role> {

	public int createRole(Role role);
	
	public Role updateRole(Role role);
	
	public void deleteRole(Long roleId);
	
	public Role findOne(Long roleId);
	
	public List<Role> findAll();
	
	public int deleteBatchRecord(List<Long> idList);
	
	/**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);
	
   


    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}
