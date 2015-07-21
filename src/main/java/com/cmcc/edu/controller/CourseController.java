package com.cmcc.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cmcc.edu.entity.Course;
import com.cmcc.edu.entity.CourseSetup;
import com.cmcc.edu.entity.CourseSorting;
import com.cmcc.edu.entity.Semester;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.ICourseFatherService;
import com.cmcc.edu.service.ICourseService;
import com.cmcc.edu.service.ICourseSetupService;
import com.cmcc.edu.service.ISemesterService;
import com.cmcc.edu.util.Utility;

/**
 * 课程管理
 * @author
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;
    @Autowired
    private ICourseSetupService courseSetupService;
    @Autowired
    private ISemesterService semesterService;
    @Autowired
    private IClassService classService;
    @Autowired
    private ICourseFatherService courseFatherService;


    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String saveJump(Model model){
    	List<Semester> seList=semesterService.semesterList(new Semester());//学期
    	List<Class> clList=classService.classList(new Class());//班级
    	List<Course> coList=courseService.courseList(new Course());//课程
    	
    	for (Class cl : clList) {
			cl.setName(cl.getGradeName()+" "+cl.getName());
		}	
    	model.addAttribute("seList", seList);
    	model.addAttribute("clList", clList);
    	model.addAttribute("coList", coList);
    	return "course/save";
    }
    
    @InitBinder("courseSetup")
	public void initBinder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("courseSetup.");
	}
    
   
    
    /**
     * 添加课程设置
     * @param courseSetup
     * @param response
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(@ModelAttribute CourseSetup courseSetup,Model model,String courseIds) { 
    	Map<String,Object> map=new HashMap<String,Object>();
    	courseSetup.setState(1);
    	if(courseSetup!=null){      	
    		courseSetupService.insert(courseSetup);  
    		
    		if(courseIds!=null && !"".equals(courseIds)){
    			courseFatherService.insertForIds(courseIds, courseSetup.getId());
    		}
    		CourseSetup courseSetup2=new CourseSetup();
    		courseSetup2.setId(courseSetup.getId());
    		List<CourseSetup> list=courseSetupService.selectById(courseSetup2);	
    		map.put("list", list);
    	}	
    	CourseSetup courseSetup2=new CourseSetup();
    	courseSetup2.setSemesterId(courseSetup.getSemesterId());
    	courseSetup2.setClassId(courseSetup.getClassId());
    	return this.select(courseSetup2);  	
    }
    
    /**
     * 跳转到查询页面
     * @return
     */
    @RequestMapping("/selectJump")
    public String selectJump(Model model){
    	List<Semester> seList=semesterService.semesterList(new Semester());//学期
    	List<Class> clList=classService.classList(new Class());//班级
    	for (Class cl : clList) {
			cl.setName(cl.getGradeName()+" "+cl.getName());
		}	
    	model.addAttribute("seList", seList);
    	model.addAttribute("clList", clList);
    	return "course/select";
    }
    
    public Integer retun(List<CourseSorting> list,Integer week){
    	int a=0;
    	for (CourseSorting courseSorting : list) {
			if(courseSorting.getWeek()==1){
				a++;
			}
		}
    	return a;
    }
    public void add(Integer week,List<CourseSorting> list,CourseSetup cs){
    	if(this.retun(list, week)>0){
			for (CourseSorting courseSorting : list) {
				if(courseSorting.getWeek()==week){
					courseSorting.getList().add(cs);
				}
			}
		}else{
			CourseSorting courseSorting=new CourseSorting();
			courseSorting.setWeek(week);
			List<CourseSetup> list2=new ArrayList<CourseSetup>();
			list2.add(cs);
			courseSorting.setList(list2);
			list.add(courseSorting);
		}
    }
    
    @RequestMapping("/select")
    @ResponseBody
    public String select(@ModelAttribute CourseSetup courseSetup) {  
    	List<CourseSetup> slist=courseSetupService.selectById(courseSetup);
    	List<CourseSorting> list=new ArrayList<CourseSorting>();
    	
    	for (CourseSetup cs : slist) {
    		if(cs.getWeek().equals("星期一")){
    			this.add(1, list, cs);
    		}else if(cs.getWeek().equals("星期二")){
    			this.add(2, list, cs);
    		}
			else if(cs.getWeek().equals("星期三")){
				this.add(3, list, cs);	
			}
			else if(cs.getWeek().equals("星期四")){
				this.add(4, list, cs);
			}
			else if(cs.getWeek().equals("星期五")){
				this.add(5, list, cs);
			}
			else if(cs.getWeek().equals("星期六")){
				this.add(6, list, cs);
			}
			else if(cs.getWeek().equals("星期日")){
				this.add(7, list, cs);
			}
    		
			
		}
    	
    	
    	/*Map<String,Object> mapList=new HashMap<String,Object>();
 		for (CourseSetup cs : slist) {
 			if(mapList.containsKey(cs.getWeek().toString())){
 				List<CourseSetup> list=(List<CourseSetup>) mapList.get(cs.getWeek());
 				list.add(cs);
 			}else{
 				List<CourseSetup> list=new ArrayList<CourseSetup>();
 				list.add(cs);
 				mapList.put(cs.getWeek(), list);
 			}
 		}*/
    	Collections.sort(list, new Comparator<CourseSorting>(){
    		 public int compare(CourseSorting o1, CourseSorting o2) {  
                 
                 //按照学生的年龄进行升序排列  
                 if(o1.getWeek() > o2.getWeek()){  
                     return 1;  
                 }  
                 if(o1.getWeek() == o2.getWeek()){  
                     return 0;  
                 }  
                 return -1;  
             } 
    	});
    	return Utility.createJsonStr(list);
    }
    @RequestMapping("/listJump")
    public String listJump(){
    	return "course/list";
    }

    

}
 