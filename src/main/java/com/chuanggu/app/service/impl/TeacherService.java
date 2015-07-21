package com.chuanggu.app.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuanggu.app.dao.TeacherMapper;
import com.chuanggu.app.entity.Teacher;
import com.chuanggu.app.service.IRoleService;
import com.chuanggu.app.service.ITeacherService;
import com.chuanggu.app.service.PasswordHelper;
import com.chuanggu.app.util.pagehelper.PageHelper;


@Service
public class TeacherService implements ITeacherService {
	@Autowired
	
	private TeacherMapper teacherDao;

    @Autowired
    private PasswordHelper passwordHelper;
    
    @Autowired
    private IRoleService roleService;
	
    @Override
	public int createTeacher(Teacher user) {
    	passwordHelper.encryptPassword(user);
        user.setRoles(user.getRoleIdsStr());
		teacherDao.insert(user);
		return user.getId().intValue();
	}

	@Override
	public int updateTeacher(Teacher user) {
		user.setRoles(user.getRoleIdsStr());
		return teacherDao.updateByPrimaryKeySelective(user);
	}
	
	@Override
	public int updateTeacherByPersonal(Teacher user) {
		return teacherDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int deleteTeacher(Long userId) {

		return teacherDao.deleteByPrimaryKey(userId);
	}

	@Override
	public int deleteBatchRecord(List<Long> idList) {
		
		
		return teacherDao.deleteBatchRecord(idList);
	}

	@Override
	public Teacher findOne(Long userId) {
		
	List<Teacher> userList = teacherDao.findOne(userId);
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
    public Teacher findByUsername(String username) {
        return teacherDao.findByUsername(username);
    }
    
    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
    	Teacher user =findByUsername(username);
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
    	Teacher user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        return roleService.findPermissions(user.getIdList().toArray(new Long[0]));
    }

	@Override
	public void changePassword(Long userId, String newPassword) {

		  List<Teacher> userList = teacherDao.findOne(userId);
	   	  if(userList.size() == 0) {
	            return ;
	        }
	   	   Teacher user=userList.get(0);
	       user.setPassword(newPassword);
	       passwordHelper.encryptPassword(user);
	       teacherDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<Teacher> findAllowed(int pageNumber,int itemsOnPage,Long schoolId) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return teacherDao.findAllowed(schoolId);
	}

	/**
	 * excel导入教师信息
	 */
	@Override
	public void formExcel(String filename) {
		
		// 创建对Excel工作簿文件的引用
        HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(
			        filename));
	        HSSFSheet sheet = workbook.getSheetAt(0);

	        int j = 1;//从第2行开始堵数据
	        // 第在excel中读取一条数据就将其插入到数据库中
	        while (j < sheet.getPhysicalNumberOfRows()) {
	            HSSFRow row = sheet.getRow(j);
	            Teacher tea=new Teacher();
	            for (int i = 0; i <= 3; i++) {
	                HSSFCell cell = row.getCell((short) i);
	                
	                if(i==0){
	                	tea.setUsername(cell.getStringCellValue());
	                }else if(i==1){
	                	cell.setCellType(cell.CELL_TYPE_STRING);
	                	tea.setMobile(cell.getStringCellValue());
	                }else if(i==2){
	                	tea.setCourse(cell.getStringCellValue());  
	                }
	                
	            }  
	            teacherDao.insertSelective(tea); 
	            j++;    
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 批量上传教师信息
	 */
	@Override
	public void batch(String information) {
		String[] teaList=information.split(";");
		for (String t : teaList) {
			String[] tea=t.split("、");
			Teacher teacher=new Teacher();
			for (int i = 0; i < tea.length; i++) {
				if(i==0){
					teacher.setUsername(tea[i]);
				}else if(i==1){	
					teacher.setMobile(tea[i]);
				}else if (i==2){
					teacher.setCourse(tea[i]);
				}
			}
			teacherDao.insert(teacher);
		}		
	}
	
	@Override
	public List<Teacher> findTeacherByIds(String teacherIds) {
		// TODO Auto-generated method stub
		return teacherDao.findTeacherByIds(teacherIds);
	}
	
	
	@Override
	public List<Teacher> findTeacherByGradeId(Integer gradeId) {
		// TODO Auto-generated method stub
		return teacherDao.findTeacherByGradeId(gradeId);
	}

	@Override
	public List<Teacher> findAll(int pageNumber, int itemsOnPage) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return  teacherDao.findAll();
	}

	@Override
	public List<Teacher> findAll() {
		
		return teacherDao.findAll();
	}

	@Override
	public List<Teacher> find(int pageNumber, int itemsOnPage, Teacher t) {
		PageHelper.startPage(pageNumber, itemsOnPage);
		return teacherDao.findTeacherBook(t);
	}
	
	@Override
	public List<Teacher> findTeacherBook(Teacher record) {
		// TODO Auto-generated method stub
		return teacherDao.findTeacherBook(record);
	}
 

}
