package com.cmcc.edu.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.edu.entity.Class;
import com.cmcc.edu.entity.Comment;
import com.cmcc.edu.entity.Course;
import com.cmcc.edu.entity.Grade;
import com.cmcc.edu.entity.Homework;
import com.cmcc.edu.entity.Teacher;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.ICourseService;
import com.cmcc.edu.service.IGradeService;
import com.cmcc.edu.service.IHomeworkService;
import com.cmcc.edu.service.ITeacherService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;

/**
 * 作业管理
 * @author 
 *
 */
@Controller
@RequestMapping("/homework")
public class HomeworkController extends PageController<Homework>{

    
    @Autowired
    private IHomeworkService homeworkService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IClassService classService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    
    private PageInfo<Homework> pageInfo;
    
    /**
     * 发布作业跳转
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String saveJump() {
    	return "homework/save";
    }


    @InitBinder("homework")
	public void initBinder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("homework.");
	}

   
   
    
    @RequestMapping("/list")
    @ResponseBody
    public String list(){
    	Map<String,Object> map=new HashMap<String,Object>();
    	Course course=new Course();
    	List<Course> couList=courseService.courseList(course);
    	Class cl=new Class();
    	List<Class> clsList=classService.classList(cl);
    	for (Class c : clsList) {
			c.setName(c.getGradeName()+" "+c.getName());
		}
    	if(couList.size()>0){
    		map.put("couList", couList);    		
    	}
    	if(clsList.size()>0){
    		map.put("clsList", clsList);    	
    	}
    	
    	return Utility.createJsonStr(map);  	
    }

    
    /**
     * 发布作业页面的加载班级
     * @return
     */
    @RequestMapping("/saveList")
    @ResponseBody
    public String saveList(){
    	Map<String,Object> map=new HashMap<String,Object>();
    	List<Grade> gradeList=gradeService.findAll();
    	for (Grade grade : gradeList) {
    		Class c=new Class();
    		c.setGradeId(grade.getId());
			List<Class> classList=classService.classList(c);
			if(classList.size()>0){
				map.put(grade.getName(), classList);
			}
		}	
    	
    	List<Course> couList=courseService.courseList(new Course());
    	if(couList.size()>0){
    		map.put("couList", couList);    		
    	}
    	return Utility.createJsonStr(map);  	
    }
    
    /**
     * 发布作业
     * @param homework
     * @return
     * @throws ParseException 
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(@ModelAttribute Homework homework,String year,String hourse,String minutes) throws ParseException {    
    	Map<String,Object> map=new HashMap<String,Object>();
    	
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	
    	if(user!=null){
    		homework.setTeacherId(user.getId().intValue());;
    	}
    	//根据年月日，时，分来拼接时间
    	//start
    	String time="";
    	if(year!=null && !"".equals(year)){
    		time=year;
    	}
    	if(hourse!=null && !"".equals(hourse)){
    		time+=" "+hourse;
    	}
    	if(minutes!=null && !"".equals(minutes)){
    		time+=":"+minutes+":00";
    	}
    	if(time!=null && !"".equals(time)){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		homework.setSendtime(sdf.parse(time));
    	}
    	//end
    	
    	if(homework.getClassId()!=null && !"".equals(homework.getClassId())){
    		homework.setClassId(","+homework.getClassId()+",");
    	}

    	homework.setCreatetime(new Date());
    	if(homework.getPubstate().equals("0")){
    		homework.setPublishtime(new Date());
    	}
    	//根据科目，对象，内容来判断完整性
    	if(homework.getTitle()!=null && !homework.getTitle().equals("") && homework.getContent()!=null && !homework.getContent().equals("")  && homework.getCourseId()!=null && homework.getClassId()!=null && !homework.getClassId().equals("")){
    		homework.setComplete("0");//完整
    	}else{
    		homework.setComplete("1");//不完整
    	}
    	if(homework.getId()!=null){
    		homeworkService.update(homework);
    	}else{
    		homeworkService.insert(homework);
    	}
    	map.put("result", true);
    	return Utility.createJsonStr(map);
    }
    
    /**
     * 跳转到草稿页面
     * @param homework
     * @param model
     * @return
     */
    @RequestMapping("/deaftJump")
    public String deaftJump(@ModelAttribute Homework homework,Model model) {
    	return "homework/draft";
    }
    
    /**
     * 草稿箱
     * @param homework
     * @return
     */
    @RequestMapping("/deaft")
    @ResponseBody
    public String deaft(@ModelAttribute Homework homework,Integer pageNumber ,Integer itemsOnPage) {
    	pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, homeworkService, homework);
    	List<Class> clList=classService.classList(new Class());
    	if(pageInfo.getList().size()>0){
    		for (Homework homework2 : pageInfo.getList()) {	
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    			homework2.setCreateTimeStr(sdf.format(homework2.getCreatetime()));
    			
    			if(homework2.getClassId()!=null && !homework2.getClassId().equals("")){
    				String[] str=homework2.getClassId().split(",");
    				for (String s : str) {
    					if(s!=null && !"".equals(s)){
    						for (Class cl : clList) {
    							if(cl.getId()==Integer.parseInt(s)){
    								if(homework2.getClassName()!=null && !homework2.getClassName().equals("")){
    									homework2.setClassName(homework2.getClassName()+","+cl.getGradeName()+" "+cl.getName()+",");
    								}else{
    									homework2.setClassName(cl.getGradeName()+" "+cl.getName()+",");
    								}			
    							}
    						}
    					}
						
					}
    			}
			}
    		
    	}
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    }
    
    
    /**
     * 批量删除
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(String ids) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(ids!=null && !"".equals(ids)){
    		String[] id=ids.split(",");
    		for (String s : id) {
    			homeworkService.delete(Integer.parseInt(s));
			}
    		map.put("result", 1);
    	}else{
    		map.put("result", 0);
    	}
    	return Utility.createJsonStr(map);
    }
    /**
     * 批量发送
     * @param ids
     * @return
     */
    @RequestMapping("/batchSend")
    @ResponseBody
    public String batchSend(String ids) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(ids!=null && !"".equals(ids)){
    		String[] id=ids.split(",");
    		for (String s : id) {
    			Homework homework=homeworkService.find(Integer.parseInt(s));
    			homework.setPubstate("0");
    			homeworkService.update(homework);
			}
    		map.put("result", 1);
    	}else{
    		map.put("result", 0);
    	}
    	return Utility.createJsonStr(map);
    }
    
    /**
     * 跳转到历史作业页面
     */
    @RequestMapping("/historyJump")
    public String historyJump(){
    	return "homework/history";
    }

   
    
    /**
     * 历史作业查询
     * @throws ParseException 
     */
    @RequestMapping("/selectHistory")
    @ResponseBody
    public String selectHistory(@ModelAttribute Homework homework,String time,Model model,Integer pageNumber ,Integer itemsOnPage) throws ParseException {
    	if(time!=null && !time.equals("")){
    		homework.setTimeStr(time);
    	}
    	if(homework.getClassId()!=null && !"".equals(homework.getClassId())){
    		homework.setClassId(","+homework.getClassId()+",");
    	}
    	homework.setPubstate("0");
    	pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, homeworkService, homework);
    	List<Class> clList=classService.classList(new Class());
    	List<Course> couList=courseService.courseList(new Course());
    	List<Teacher> teachList=teacherService.findAll();
    	if(pageInfo.getList().size()>0){
    		for (Homework homework2 : pageInfo.getList()) {
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			if(homework2.getPublishtime()!=null){
    				homework2.setCreateTimeStr(sdf.format(homework2.getPublishtime()));
    				homework2.setCreateTimeString(sdf2.format(homework2.getPublishtime()));
    			}
    			
    			
    			if(homework2.getClassId()!=null && !homework2.getClassId().equals("")){
    				String[] str=homework2.getClassId().split(",");
    				for (String s : str) {
    					if(s!=null && !"".equals(s)){
    						for (Class cl : clList) {							
    							if(cl.getId()==Integer.parseInt(s)){
    								if(homework2.getClassName()!=null && !homework2.getClassName().equals("")){
    									homework2.setClassName(homework2.getClassName()+","+cl.getGradeName()+" "+cl.getName()+",");
    								}else{
    									homework2.setClassName(cl.getGradeName()+" "+cl.getName()+",");
    								}			
    							}
    						}
    					}
						
					}
    			}
    			if(homework2.getCourseId()!=null &&homework2.getCourseId()!=0){
    				for (Course course : couList) {
						if(homework2.getCourseId()==course.getId()){
							homework2.setCourseName(course.getName());
						}
					}
    			}
    			if(homework2.getTeacherId()!=null && homework2.getTeacherId()!=0){
    				for (Teacher teacher : teachList) {
						if(teacher.getId()==(long)(homework2.getTeacherId())){
							homework2.setTeacherName(teacher.getUsername());
						}
					}
    			}
    			
    			
			}
    		
    	}
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    }
    /**
     * 跳转到今日作业页面
     * @param homework
     * @param model
     * @return
     */
    @RequestMapping("/todayJump")
    public String todayJump(){
    	return "homework/today";
    }
    
    /**
     * 今日作业
     * @return
     */
    @RequestMapping("/today")
    @ResponseBody
    public String today(@ModelAttribute Homework homework,Integer pageNumber ,Integer itemsOnPage){
    	Map<String,Object> map=new HashMap<String,Object>();
    	homework.setPubstate("0");
    	if(homework.getClassId()!=null && !"".equals(homework.getClassId())){
    		homework.setClassId(","+homework.getClassId()+",");
    	}
    	pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, homeworkService, homework);
    	List<Class> clList=classService.classList(new Class());
    	List<Course> couList=courseService.courseList(new Course());
    	List<Teacher> teachList=teacherService.findAll();
    	if(pageInfo.getList().size()>0){
    		for (Homework homework2 : pageInfo.getList()) {
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			if(homework2.getPublishtime()!=null){
    				homework2.setCreateTimeStr(sdf.format(homework2.getPublishtime()));
    				homework2.setCreateTimeString(sdf2.format(homework2.getPublishtime()));
    			}
    			
    			
    			if(homework2.getClassId()!=null && !homework2.getClassId().equals("")){
    				String[] str=homework2.getClassId().split(",");
    				for (String s : str) {
    					if(s!=null && !"".equals(s)){
    						for (Class cl : clList) {
    							if(cl.getId()==Integer.parseInt(s) ){
    								if(homework2.getClassName()!=null && !homework2.getClassName().equals("")){
    									homework2.setClassName(homework2.getClassName()+","+cl.getGradeName()+" "+cl.getName()+",");
    								}else{
    									homework2.setClassName(cl.getGradeName()+" "+cl.getName()+",");
    								}			
    							}
    						}
    					}
						
					}
    			}
    			if(homework2.getCourseId()!=null &&homework2.getCourseId()!=0){
    				for (Course course : couList) {
						if(homework2.getCourseId()==course.getId()){
							homework2.setCourseName(course.getName());
						}
					}
    			}
    			if(homework2.getTeacherId()!=null && homework2.getTeacherId()!=0){
    				for (Teacher teacher : teachList) {
						if(teacher.getId()==(long)(homework2.getTeacherId())){
							homework2.setTeacherName(teacher.getUsername());
						}
					}
    			}
			}
    		
    	}
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    }

    /**
     * 编辑跳转
     */
    @RequestMapping("/editJump")
    public String editJump(Integer id,Model model){
    	Homework homework=homeworkService.find(id);
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time="";
    	if(homework.getSendtime()!=null){
    		time=sdf.format(homework.getSendtime());
    		model.addAttribute("year", time.substring(0, 10));
        	model.addAttribute("hourse", time.substring(11, 13));
        	model.addAttribute("minutes", time.substring(14, 16));
    	}
    	List<Grade> list=gradeService.findAll();
    	List<Class> clList=classService.classList(new Class());
    	String[] str=homework.getClassId().split(",");
    	for (String s : str) {
    		if(s!=null && !"".equals(s)){
    			for (Class cla : clList) {
    				if(Integer.parseInt(s)==cla.getId()){
    					for (Grade g : list) {
    						if(cla.getGradeId()==g.getId()){
    							if(homework.getClassName()!=null && !"".equals(homework.getClassName())){
    								homework.setClassName(homework.getClassName()+","+g.getName()+" "+cla.getName()+",");
    							}else{
    								homework.setClassName(g.getName()+" "+cla.getName()+",");
    							}	
    						}
    					}
    				}
    			}
    		}
			
		}
    	
    	model.addAttribute("homework", homework);   	
    	return "homework/save";
    }

    

}
