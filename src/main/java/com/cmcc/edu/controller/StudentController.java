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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.edu.entity.Class;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;



@Controller
@RequestMapping("/phonebook/student")
public class StudentController extends PageController<Student>{
	
	
	@Autowired
    private IStudentService studentService;
	
	@Autowired
    private IClassService classService;
	
	private PageInfo<Student> pageInfo;
	
	 @Autowired
	  private IUserService userService;
	
	/**
     * 通讯录学生查询
     * @param 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "phonebook/student";
    }
    
    /**
     * 学生查询Json
     * @param 
     * @return
     */
    @RequestMapping("/studentJson")
    @ResponseBody
    public String studentJson(Student student,Integer pageNumber ,Integer itemsOnPage) {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	student.setSchoolId(user.getSchoolId());
    	 pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, studentService,student);
    	 if(pageInfo.getList().size()>0){
	     		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	     		for (Student c : pageInfo.getList()) {
	     			if(c.getBirthday()!=null){
	     				c.setTime(sdf.format(c.getBirthday()));
	     			}
	     			
	 			}
	     	}
     	String retru = Utility.createJsonStr(pageInfo);
 		return retru;
    }
    /**
     * 班级ajax
     * @param 
     * @return
     */
    @RequestMapping("/classJson")
    @ResponseBody
    public String courseJson(Model model) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	List<Student> stulist= studentService.findClassBySchoolId(user.getSchoolId());
    	//List<Class> clList=classService.classList(new Class());
    	
    	map.put("classList", stulist);
		return Utility.createJsonStr(map) ;
    }
    @RequestMapping("/saveBath")
    @ResponseBody
    public String saveBath(String msg,Integer id) throws ParseException {    
    	Map<String,Object> map=new HashMap<String,Object>();
    	Class c=classService.findOne(id);
    	msg=msg.substring(9, msg.length()-2);  
    	String[] str=msg.split("],");
    	for (String s : str) {
			s=s.substring(1, s.length());
			String[] one=s.split(",");
			Student student =new Student();
			for (int i = 0; i < one.length; i++) {
				if(i==0){
					student.setName(one[i].substring(1, one[i].length()-1));
				}else if(i==1){
					student.setMobile(one[i].substring(1, one[i].length()-1));
				}else if(i==2){
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					Date date=sdf.parse(one[i].substring(1, one[i].length()-1));
					student.setBirthday(date);
				}
			}
			if(c!=null){
				student.setGradeId(c.getGradeId());
			}
			student.setClassId(id);
			studentService.insert(student);
		}
    	map.put("result", true);
    	return Utility.createJsonStr(map);
    }
    @RequestMapping("/forExcel")
    @ResponseBody
    public String forExcel(String uptextfield,Integer id){
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(id!=null){
    		Class c=classService.findOne(id);
    		if(c!=null){
    			studentService.formExcel(c.getId(), c.getGradeId(), uptextfield);
    			map.put("result", true);
    		}else{
    			map.put("result", false);
    		}
    	}else{
    		map.put("result", false);
    	}
    	return Utility.createJsonStr(map);
    }

}
