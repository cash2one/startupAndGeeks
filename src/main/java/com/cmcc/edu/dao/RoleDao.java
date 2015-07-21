package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Role;


public interface RoleDao {

    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);
    public int deleteBatchRecord(List<Long> idList);

    public Role findOne(Long roleId);
    public List<Role> findAll();
}
