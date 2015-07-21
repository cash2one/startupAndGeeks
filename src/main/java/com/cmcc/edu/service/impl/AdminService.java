package com.cmcc.edu.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.constant.Constants;
import com.cmcc.edu.dao.AdminMapper;
import com.cmcc.edu.entity.Admin;
import com.cmcc.edu.service.IAdminService;
import com.cmcc.edu.service.IRoleService;
import com.cmcc.edu.service.PasswordHelper;
import com.cmcc.edu.util.pagehelper.PageHelper;

@Service
public class AdminService implements IAdminService {
	@Autowired
	
	private AdminMapper adminDao;

    @Autowired
    private PasswordHelper passwordHelper;
    
    @Autowired
    private IRoleService roleService;
	
    @Override
	public int createAdmin(Admin user) {
    	passwordHelper.encryptPassword(user);
        user.setRoles(user.getRoleIdsStr());
		adminDao.insert(user);
		return user.getId().intValue();
	}

	@Override
	public int updateAdmin(Admin user) {
		user.setRoles(user.getRoleIdsStr());
		return adminDao.updateByPrimaryKeySelective(user);
	}
	
	@Override
	public int updateAdminByPersonal(Admin user) {
		return adminDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int deleteAdmin(Long userId) {

		return adminDao.deleteByPrimaryKey(userId);
	}
	
	@Override
	public List<Admin> findAllowed(int pageNumber,int itemsOnPage,Long schoolId) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		if(schoolId == Constants.AREAADMIN) return adminDao.findArea(schoolId); 
		return adminDao.findAllowed(schoolId);
	}

	@Override
	public int deleteBatchRecord(List<Long> idList) {
		
		
		return adminDao.deleteBatchRecord(idList);
	}

	@Override
    
   public Admin findOne(Long userId) {
   	List<Admin> userList = adminDao.findOne(userId);
       if(userList.size() == 0) {
           return null;
       }
       return userList.get(0);
   }
   
	
	  /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public Admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }
    
    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
    	Admin user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        return roleService.findRoles(user.getIdList().toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
    	Admin user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        return roleService.findPermissions(user.getIdList().toArray(new Long[0]));
    }

	@Override
	public void changePassword(Long userId, String newPassword) {

		  List<Admin> userList = adminDao.findOne(userId);
	   	  if(userList.size() == 0) {
	            return ;
	        }
	   	   Admin user=userList.get(0);
	       user.setPassword(newPassword);
	       passwordHelper.encryptPassword(user);
	       adminDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<Admin> findAll(int pageNumber, int itemsOnPage) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return adminDao.findAll();
	}

	@Override
	public List<Admin> find(int pageNumber, int itemsOnPage, Admin t) {
		// TODO Auto-generated method stub
		return null;
	}

}
