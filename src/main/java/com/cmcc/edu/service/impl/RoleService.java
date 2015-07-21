package com.cmcc.edu.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.dao.RoleMapper;
import com.cmcc.edu.entity.Role;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IResourceService;
import com.cmcc.edu.service.IRoleService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.pagehelper.PageHelper;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IResourceService resourceService;

	@Override
	public int createRole(Role role) {
		role.setResourceId(role.getResourceIdsStr());
		return roleMapper.insert(role);
	}

	@Override
	public Role updateRole(Role role) {
		role.setResourceId(role.getResourceIdsStr());
		roleMapper.updateByPrimaryKey(role);
		return role;
	}

	@Override
	public void deleteRole(Long roleId) {
		roleMapper.deleteByPrimaryKey(roleId);		
	}

	@Override
	public Role findOne(Long roleId) {
		Role role=roleMapper.findOne(roleId);
		if(role==null){
			return null;
		}
		return role;
	}

	@Override
	public List<Role> findAll() {
		return roleMapper.findAll();
	}

	@Override
	public int deleteBatchRecord(List<Long> idList) {
		return roleMapper.deleteBatchRecord(idList);
	}

	@Override
	public Set<String> findRoles(Long... roleIds) {
		 Set<String> roles = new HashSet<String>();
	        for(Long roleId : roleIds) {
	            Role role = findOne(roleId);
	            if(role != null) {
	                roles.add(role.getRole());
	            }
	        }
	        return roles;
	}

	@Override
	public Set<String> findPermissions(Long[] roleIds) {
		 Set<Long> resourceIds = new HashSet<Long>();
	        for(Long roleId : roleIds) {
	            Role role = findOne(roleId);
	            if(role != null) {
	                resourceIds.addAll(role.getResourceIds());
	            }
	        }
	        return resourceService.findPermissions(resourceIds);
	}

	@Override
	public List<Role> findAllowed(int pageNumber,int itemsOnPage,Long id) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return roleMapper.findAllowed(id);
	}

	@Override
	public List<Role> findAll(int pageNumber, int itemsOnPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> find(int pageNumber, int itemsOnPage, Role t) {
		// TODO Auto-generated method stub
		return null;
	}
 

}