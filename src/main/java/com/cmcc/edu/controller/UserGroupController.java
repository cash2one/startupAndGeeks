package com.cmcc.edu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmcc.edu.entity.Class;
import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.Grade;
import com.cmcc.edu.entity.Notice;
import com.cmcc.edu.entity.School;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.Teacher;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.entity.UserGroup;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.ICourseService;
import com.cmcc.edu.service.IGradeService;
import com.cmcc.edu.service.ISchoolService;
import com.cmcc.edu.service.IScoreService;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.ITeacherService;
import com.cmcc.edu.service.IUserGroupService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;

/**
 * 用户自定义管理
 * @author 
 *
 */
@Controller
@RequestMapping("/phonebook/usergroup")
public class UserGroupController extends PageController<UserGroup>{

    @Autowired
    private ICourseService courseService;
    @Autowired
    private IClassService classService;
    
    @Autowired
    private IUserGroupService userGroupService;
    
    @Autowired
    private IStudentService studentService;
    
    
    @Autowired
    private ISchoolService schoolService;
    
    @Autowired
    private IGradeService gradeService;
    
    @Autowired
    private ITeacherService teacherService;
    
    @Autowired
    private IUserService userService;
    
    private PageInfo<UserGroup> pageInfo;


    /**
     * 用户自定义查询
     * @param 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(@ModelAttribute UserGroup userGroup,Model model) {
        return "phonebook/usergroup";
    }
    
   
    
    /**
     * 已发通知Json
     * @param 
     * @return
     */
    @RequestMapping("/grouplistJson")
    @ResponseBody
    public String sendNoticeJson(UserGroup userGroup,Integer pageNumber ,Integer itemsOnPage) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	userGroup.setCreateid(user.getId());
    	userGroup.setType(user.getType());
    	 pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, userGroupService,userGroup);
    	 if(pageInfo.getList().size()>0){
	     		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     		for (UserGroup c : pageInfo.getList()) {
	     			if(c.getCreatdate()!=null){
	     				c.setTime(sdf2.format(c.getCreatdate()));
	     			}
	     			
	 			}
	     	}
     	String retru = Utility.createJsonStr(pageInfo);
 		return retru;
    }
    /**
     * 批量删除草稿箱
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(String ids) {
    	int num=0;
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(ids!=null && !"".equals(ids)){
    		String[] id=ids.split(",");
    		num=id.length;
    		for (String s : id) {
    			userGroupService.deleteUserGroup(Long.parseLong(s));
    			//scoreService.deleteByexamId(Long.parseLong(s));
			}
    		map.put("result", "删除成功");
    		map.put("num", num);
    	}else{
    		map.put("result", "请勾择删除项");
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    

    /**
     * 用户自定义查询
     * @param 
     * @return
     */
    @RequestMapping(value ="/detail")
    public String detail( Long id,Model model) {
    	//Map<String,Object> map=new HashMap<String,Object>();
    	UserGroup usergroup=userGroupService.findOne(id);
    	String stuId=usergroup.getStuId();
    	String teaId=usergroup.getTeacherId();
    	usergroup.setStuId(stuId.trim());
    	usergroup.setTeacherId(teaId.trim());
    	List<UserGroup> list=userGroupService.findStuAndTeaByID(usergroup);
    	model.addAttribute("usergrouplist", list);
    	//model.addAttribute("groupname", usergroup.getName());
        return "phonebook/groupPersonList";
    }
    
    @RequestMapping(value = "/save")
    @ResponseBody
    @Transactional
    public String create(@ModelAttribute UserGroup userGroup) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	Map<String,Object> map=new HashMap<String,Object>();
    	String idStr=userGroup.getPersonStr();
    	if(idStr!=null && !"".equals(idStr)){
    		String[] ids=idStr.split(",");
    		String stuId="";
    		String teaId="";
    		for (String s : ids) {
    			String[] val=s.split("##");
    			if(val[1].equals("student")){
    				stuId+=val[0]+",";
    			}else{
    				teaId+=val[0]+",";
    			}
    			
    		}
    		stuId=stuId.substring(0,stuId.length()-1);
    		teaId=teaId.substring(0,teaId.length()-1);
    		userGroup.setStuId(stuId);
    		userGroup.setTeacherId(teaId);
        }
    	if(userGroup.getId()!=null){
    		userGroupService.updateUserGroup(userGroup);
    		map.put("result", "修改成功");
    	}else{
    		userGroup.setCreateid(user.getId());
        	userGroup.setCreatdate(new Date());
        	userGroup.setType(user.getType());
        	userGroupService.insert(userGroup);
        	map.put("result", "添加成功");
    		
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    
    @RequestMapping("tree")
	@ResponseBody
	public void jsonTree(String groupId,String method,HttpServletResponse response)  throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//Long schoolId=100202001l;
		
		School school=schoolService.findOne(100202001l);
		if(school==null){
			return ;
		}
		UserGroup usergroup=null;
		if("update".equals(method)&&groupId!=null&&!groupId.trim().equals("")){
			
			usergroup=userGroupService.findOne(Long.parseLong(groupId));
			
		}
		
		
		long schoolId=school.getId();
		Map<String, Object> mapschool = new HashMap<String, Object>(); 
		//mapschool.put("nodeType", "school");
		mapschool.put("id", schoolId+"##school");
		mapschool.put("name", school.getName()); 
		mapschool.put("nodetype", "school");
		mapschool.put("pId", null);
		list.add(mapschool); 
		
		
		
		Map<String, Object> teaRoot = new HashMap<String, Object>(); 
		//mapschool.put("nodeType", "school");
		teaRoot.put("id", "teaRoot");
		teaRoot.put("name", "老师"); 
		teaRoot.put("nodetype", "teaRoot");
		teaRoot.put("pId", schoolId+"##school");
		list.add(teaRoot); 
		
		List<Grade> gradelist= gradeService.selectGradeBySchool(100202001l);
		Iterator<Grade> gradeiterator = gradelist.iterator();
		Grade grade;
		while (gradeiterator.hasNext()) {
			grade = gradeiterator.next();
			int gradeId=grade.getId();
			Map<String, Object> map = new HashMap<String, Object>(); 
			//map.put("nodeType", "grade");
			map.put("id", String.valueOf(gradeId)+"##teagrade");
			map.put("name", grade.getName());
			map.put("pId", "teaRoot");
			map.put("nodetype", "teagrade");
			list.add(map);
			
			List<Teacher>  tealist=teacherService.findTeacherByGradeId(gradeId);
			Iterator<Teacher> Teacheriterator = tealist.iterator();
			Teacher teacher;
			while (Teacheriterator.hasNext()) {
				teacher = Teacheriterator.next();
				Map<String, Object> teachermap = new HashMap<String, Object>(); 
				//map.put("nodeType", "grade");
				teachermap.put("id", String.valueOf(teacher.getId())+"##teacher");
				teachermap.put("name", teacher.getUsername());
				teachermap.put("pId", String.valueOf(gradeId)+"##teagrade");
				teachermap.put("nodetype", "teacher");
				if(usergroup!=null){
					if(usergroup.getTeacherId().indexOf(teacher.getId().toString())!=-1){
						teachermap.put("checked", true);
					}
				}
				
				list.add(teachermap);
			}
		}
		
		Map<String, Object> stuRoot = new HashMap<String, Object>(); 
		//mapschool.put("nodeType", "school");
		stuRoot.put("id", "stuRoot");
		stuRoot.put("name", "学生"); 
		stuRoot.put("nodetype", "stuRoot");
		stuRoot.put("pId", schoolId+"##school");
		list.add(stuRoot); 
		List<Student> stulist= studentService.findStuTreeBySchoolId(100202001l);
		Iterator<Student> iterator = stulist.iterator();
		Student stu;
		//Class sl;
		int graId=-1;
		int classId=-1;
		boolean isExist=false;
		while (iterator.hasNext()) {
					    stu=iterator.next();
						if(stu.getGradeId()!=null&&stu.getGradeId()!=graId){
							graId=stu.getGradeId();
							//int newgraId=stu.getGradeId();
							Map<String, Object> mapsgrade = new HashMap<String, Object>(); 
							mapsgrade.put("id", graId+"##grade");
							mapsgrade.put("name", stu.getGraname());
							mapsgrade.put("pId", "stuRoot");
							mapsgrade.put("nodetype", "grade");
							list.add(mapsgrade);
							if(stu.getClassId()!=null&&stu.getClassId()!=classId){
								classId=stu.getClassId();
								//int newgraId=stu.getGradeId();
								Map<String, Object> mapclass = new HashMap<String, Object>(); 
								mapclass.put("id", classId+"##class");
								mapclass.put("name", stu.getClassname());
								mapclass.put("pId", String.valueOf(graId)+"##grade");
								mapclass.put("nodetype", "class");
								list.add(mapclass);
								
								if(stu.getStuId()!=null){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									Map<String, Object> mapstu = new HashMap<String, Object>(); 
									mapstu.put("id", stu.getStuId().toString()+"##student");
									mapstu.put("name", stu.getStuname());
									mapstu.put("pId", String.valueOf(classId)+"##class");
									mapstu.put("nodetype", "student");
									if(usergroup!=null){
										if(usergroup.getTeacherId().indexOf(stu.getStuId().toString())!=-1){
											mapstu.put("checked", true);
										}
									}
									list.add(mapstu);
									
								}
								
							}else{
								
								if(stu.getStuId()!=null){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									Map<String, Object> mapstu = new HashMap<String, Object>(); 
									mapstu.put("id", stu.getStuId().toString()+"##student");
									mapstu.put("name", stu.getStuname());
									mapstu.put("pId", String.valueOf(classId)+"##class");
									mapstu.put("nodetype", "student");
									if(usergroup!=null){
										if(usergroup.getTeacherId().indexOf(stu.getStuId().toString())!=-1){
											mapstu.put("checked", true);
										}
									}
									list.add(mapstu);
									
								}
							}
							
						}else{
							if(stu.getClassId()!=null&&stu.getClassId()!=classId){
								classId=stu.getClassId();
								//int newgraId=stu.getGradeId();
								Map<String, Object> mapclass = new HashMap<String, Object>(); 
								mapclass.put("id", classId+"##class");
								mapclass.put("name", stu.getClassname());
								mapclass.put("pId", String.valueOf(graId)+"##grade");
								mapclass.put("nodetype", "class");
								list.add(mapclass);
								if(stu.getStuId()!=null){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									Map<String, Object> mapstu = new HashMap<String, Object>(); 
									mapstu.put("id", stu.getStuId().toString()+"##student");
									mapstu.put("name", stu.getStuname());
									mapstu.put("pId", String.valueOf(classId)+"##class");
									mapstu.put("nodetype", "student");
									if(usergroup!=null){
										if(usergroup.getTeacherId().indexOf(stu.getStuId().toString())!=-1){
											mapstu.put("checked", true);
										}
									}
									list.add(mapstu);
									
								}
								
							}else{
								
								if(stu.getStuId()!=null){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									Map<String, Object> mapstu = new HashMap<String, Object>(); 
									mapstu.put("id", stu.getStuId().toString()+"##student");
									mapstu.put("name", stu.getStuname());
									mapstu.put("pId", String.valueOf(classId)+"##class");
									mapstu.put("nodetype", "student");
									if(usergroup!=null){
										if(usergroup.getTeacherId().indexOf(stu.getStuId().toString())!=-1){
											mapstu.put("checked", true);
										}
									}
									list.add(mapstu);
									
								}
							}
							
							
						}
		 }			
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String nodes = com.alibaba.fastjson.JSON.toJSONString(list);
		response.getWriter().print(nodes);
		return;
	
   
  }
    
    /**
     * 接收人json对象
     * @param 
     * @return
     */
    @RequestMapping("/addreceive")
    @ResponseBody
    public String addreceive() throws Exception {
    	List<Student>  stu= new ArrayList<Student>();
    	//Map<String,List<Object>> map=new HashMap<String,List<Object>>();
    	
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	
    	
    	List<Grade> gradelist= gradeService.selectGradeBySchool(user.getSchoolId());
		Iterator<Grade> gradeiterator = gradelist.iterator();
		Grade grade;
		while (gradeiterator.hasNext()) {
			grade = gradeiterator.next();
			int gradeId=grade.getId();
			Student student= new Student();
			List<Teacher>  tealist=teacherService.findTeacherByGradeId(gradeId);
			
			if(tealist.size()>0){
				List<Object> list=new ArrayList<Object>();
		    	list.addAll(tealist);
				student.setStrName("teacher##"+grade.getName()+"教师##"+grade.getId());
				student.setStrlist(list);
				stu.add(student);
			}
			

		}
    	
    	//Map<String,Object> map=new HashMap<String,Object>();
    	List<Student> stulist= studentService.findStuTreeBySchoolId(user.getSchoolId());
    	int claID=-1;
    	List<Object> slist=null;
    	Student stuobj= null;
		for (Student student : stulist) {
			if(claID!=-1&&student.getClassId()==claID){
				slist.add(student);
			}else{
				if(stuobj!=null&&slist!=null&&slist.size()>0){
					stuobj.setStrlist(slist);
					stu.add(stuobj);
				}
				claID=student.getClassId();
				stuobj=new Student();
				stuobj.setStrName("student##"+student.getClassname()+"##"+student.getClassId().toString());
				slist=new ArrayList<Object>();
				slist.add(student);
			}
		}
		if(stuobj!=null&&slist!=null&&slist.size()>0){
			stuobj.setStrlist(slist);
			stu.add(stuobj);
		}
		String nodes = com.alibaba.fastjson.JSON.toJSONString(stu);
		return nodes;
       // return "exam/receive";
    }
    
    /**
     * 跳到修改群组页面
     * @param 
     * @return
     */
    @RequestMapping(value = "/updateGroup")
    @ResponseBody
    public String updateGroup( Long id, Model model) {
    	UserGroup userGroup=userGroupService.findOne(id);
    	List<UserGroup> usergrouplist=userGroupService.findStuAndTeaByID(userGroup);
    	String perstr="";
    	String pername="";
    	for (UserGroup Group : usergrouplist) {
    		perstr+=Group.getUserid()+"##"+Group.getUserType()+",";
    		pername+=Group.getUsername()+",";
    	}
    	perstr=perstr.substring(0,perstr.length()-1);
    	pername=pername.substring(0,pername.length()-1);
    	userGroup.setPersonStr(perstr);
    	userGroup.setPersonName(pername);
    	String nodes = com.alibaba.fastjson.JSON.toJSONString(userGroup);
    	//model.addAttribute("userGroup", userGroup);
        return  nodes;
    }
    

}
