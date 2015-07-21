package com.cmcc.edu.controller;


import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.edu.entity.Attendance;
import com.cmcc.edu.entity.Class;
import com.cmcc.edu.entity.Homework;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IAttendanceService;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.IPageService;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;
/**
 * 考勤管理
 * @author
 *
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController extends PageController<Attendance>{

    @Autowired
    private IAttendanceService attendanceService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IUserService userService;  
    @Autowired
    private Class clazz;
    @Autowired
    private Student student;
    
   	private String username ;
	private User user ;//当前登录用户信息
	private PageInfo<Attendance> pageInfo; 
	
   

	/**
     * 跳转到日考勤页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String dayJump(Model model){
    	username = (String)SecurityUtils.getSubject().getPrincipal();
    	user = userService.findByUsername(username);   	
    	clazz=classService.find(user.getId().intValue());//根据教师得到班级
    	clazz.setName(clazz.getGradeName()+clazz.getName());
    	model.addAttribute("clazz", clazz);
    	return "attendance/day";
    }
    
    

    /**
     * 日考勤
     * @param 
     * @return
     * @throws ParseException 
     */
    @RequestMapping("/day")
    @ResponseBody
    public String day(@ModelAttribute Attendance attendance,String stuid,Integer pageNumber ,Integer itemsOnPage) throws ParseException {  
    	username = (String)SecurityUtils.getSubject().getPrincipal();
    	user = userService.findByUsername(username);  
    	if(user!=null){
    		Class cl=new Class();
    		cl.setTeacherId(user.getId());
    		List<Class> list=classService.classList(cl);
    		if(list.size()>0){
    			attendance.setClassId(list.get(0).getId());
    		}
    	}
    	if(stuid!=null && !"".equals(stuid)){
    		stuid=stuid.substring(0, stuid.length()-1);
    		attendance.setStuids(stuid);
    	}
    	pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, attendanceService, attendance);
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    }
    
    
    /**
     * 跳转到月考勤页面
     * @return
     */
    @RequestMapping("/monthJump")
    public String monthJump(Model model){
    	username = (String)SecurityUtils.getSubject().getPrincipal();
    	user = userService.findByUsername(username);   	
    	clazz=classService.find(user.getId().intValue());//根据教师得到班级
    	clazz.setName(clazz.getGradeName()+clazz.getName());
    	model.addAttribute("clazz", clazz);
    	return "attendance/month";
    }
    /**
     * 月考勤
     * @param
     * @return
     * @throws ParseException 
     */
    @RequestMapping("/month")
    @ResponseBody
    public String month(@ModelAttribute Attendance attendance,String stuid,Integer pageNumber ,Integer itemsOnPage) throws ParseException {  
    	username = (String)SecurityUtils.getSubject().getPrincipal();
    	user = userService.findByUsername(username);  
    	if(user!=null){
    		Class cl=new Class();
    		cl.setTeacherId(user.getId());
    		List<Class> list=classService.classList(cl);
    		if(list.size()>0){
    			attendance.setClassId(list.get(0).getId());
    		}
    	}
    	if(stuid!=null && !"".equals(stuid) && !stuid.equals("undefined")){
    		stuid=stuid.substring(0, stuid.length()-1);
    		attendance.setStuids(stuid);
    	}
    	
    	if(pageNumber == null && itemsOnPage == null){
			if(pageInfo.getList().size()==0){
				pageInfo.setPageNum(pageInfo.getPageNum()-1);
			}
			pageInfo = new PageInfo<Attendance>(attendanceService.find(pageInfo.getPageNum()-1,pageInfo.getPageSize(),attendance));
		}else{
			pageInfo = new PageInfo<Attendance>(attendanceService.find(pageNumber,itemsOnPage,attendance));
	    	pageInfo.setPageNum(pageNumber);
	    	pageInfo.setPageSize(itemsOnPage);
		}	
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    }
    
   
    

}
