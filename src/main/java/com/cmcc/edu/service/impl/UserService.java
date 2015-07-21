package com.cmcc.edu.service.impl;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcc.edu.constant.Constants;
import com.cmcc.edu.dao.AdminMapper;
import com.cmcc.edu.dao.TeacherMapper;
import com.cmcc.edu.dao.UserMapper;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IRoleService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.service.PasswordHelper;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userDao;
    
    @Autowired
    private AdminMapper adminDao;
    
    @Autowired
    private TeacherMapper teacherDao;
    
    @Autowired
    private PasswordHelper passwordHelper;
    
    
    @Autowired
    private IRoleService roleService;
 
     
//    public User findOne(Long userId) {
//    	List<User> userList = userDao.findOne(userId);
//        if(userList.size() == 0) {
//            return null;
//        }
//        return userList.get(0);
//    }
//    
//    public int createUser(User user) {
//    	
//    	 //加密密码
//        passwordHelper.encryptPassword(user);
//        user.setRoles(user.getRoleIdsStr());
//        userDao.createUser(user);
//        return user.getId().intValue();
//    }
//    public User updateUser(User user) {
//    	user.setRoles(user.getRoleIdsStr());
//    	userDao.updateUser(user);
//        return user;
//    }
//    public int deleteBatchRecord(List<Long> idList) {
//    	
//		return userDao.deleteBatchRecord(idList);
//	}
//    /**
//     * 修改密码
//     * @param userId
//     * @param newPassword
//     */
//    public void changePassword(Long userId, String newPassword) {
//    	List<User> userList = userDao.findOne(userId);
//    	 if(userList.size() == 0) {
//             return ;
//         }
//    	User user=userList.get(0);
//        user.setPassword(newPassword);
//        passwordHelper.encryptPassword(user);
//        userDao.updateUser(user);
//    }
//
//     
//    public void deleteUser(Long userId) {
//        userDao.deleteUser(userId);
//    }
//     
//    public List<User> findAll() {
//        return userDao.findAll();
//    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    
    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
    }

	@Override
	public void lockUser(String username) {
		
		User user =findByUsername(username);
		
		if (user.getType() == Constants.TEACHER)
			teacherDao.lockUser(username);
		else 
			adminDao.lockUser(username);
		
	}
 

}