package com.chuanggu.app.controller;

import java.io.IOException;
import java.text.ParseException;
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

import com.chuanggu.app.entity.Class;
import com.chuanggu.app.entity.Exam;
import com.chuanggu.app.entity.Grade;
import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.Noticeperson;
import com.chuanggu.app.entity.Student;
import com.chuanggu.app.entity.Teacher;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.entity.UserGroup;
import com.chuanggu.app.service.IClassService;
import com.chuanggu.app.service.ICourseService;
import com.chuanggu.app.service.IGradeService;
import com.chuanggu.app.service.INoticeService;
import com.chuanggu.app.service.INoticepersonService;
import com.chuanggu.app.service.IScoreService;
import com.chuanggu.app.service.IStudentService;
import com.chuanggu.app.service.ITeacherService;
import com.chuanggu.app.service.IUserGroupService;
import com.chuanggu.app.service.IUserService;
import com.chuanggu.app.util.Utility;
import com.chuanggu.app.util.pagehelper.PageInfo;


/**
 * 通知管理
 * @author 
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends PageController<Notice> {
	
	
	    @Autowired
	    private IScoreService scoreService;
	    @Autowired
	    private ICourseService courseService;
	    @Autowired
	    private IClassService classService;
	    
	    @Autowired
	    private INoticeService noticeService;
	    
	    @Autowired
	    private IStudentService studentService;
	    
	    @Autowired
	    private ITeacherService teacherService;
	    
	    @Autowired
	    private IGradeService gradeService;
	    
	    @Autowired
	    private IUserGroupService userGroupService;
	    
	    @Autowired
	    private INoticepersonService noticepersonService;
	    
	    @Autowired
	    private IUserService userService;
	    
	    private PageInfo<Notice> pageInfo;
	    
	    
	    /**
	     * 跳到发布通知页面
	     * @return
	     */
	    @RequestMapping(method = RequestMethod.GET)
	    public String showpublishForm(Model model) {
	    	 return "notice/noticeAdd";
	    }
	    
	    
	    /**
	     * 发布通知
	     * @param homework
	     * @return
	     */
	    @RequestMapping(value="/save")
	    @ResponseBody
	    @Transactional
	    public String save( @ModelAttribute Notice notice,String dtime,String isAdd) throws IOException, ParseException {  
	    	//Map<String,Object> map=new HashMap<String,Object>();
	    	String username = (String)SecurityUtils.getSubject().getPrincipal();
	    	User user = userService.findByUsername(username);
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	if(notice.getPubstate().equals("0")){
	    		notice.setPublishtime(new Date());
	    		notice.setReceivetime(new Date());
	    	}
	    	if(notice.getSendtype().equals("1")){
	    		notice.setSendtime(sdf.parse(dtime));
	    		notice.setReceivetime(sdf.parse(dtime));
	    	}
	    	//noticeService.insert(notice);
	    	if(notice.getId()!=null&&(isAdd==null||isAdd.equals(""))){
	    		String idstr=notice.getPersonStr();
	    		noticeService.update(notice);
	    		if(idstr!=null && !"".equals(idstr)){
	    			noticepersonService.deleteByNoticeId(notice.getId());
		    		String[] id=idstr.split(",");
		    		for (String s : id) {
		    			String[] personstr=s.split("##");
		    			Noticeperson person=new Noticeperson();
		    			person.setIsread("1");
		    			person.setReceiveid(Long.parseLong(personstr[0]));
		    			person.setReceivetime(new Date());
		    			person.setReceivetype(personstr[1]);
		    			person.setGrouptype(personstr[2]);
		    			person.setNoticeId(notice.getId());
		    			if(personstr[3]!=null&&!personstr[3].equals("null")&&!personstr[3].equals("undefined")){
		    				person.setType(Integer.parseInt(personstr[3]));
		    			}
		    			if(personstr[4]!=null&&!personstr[4].equals("null")&&!personstr[4].equals("undefined")){
		    				person.setGroupId(Long.parseLong(personstr[4]));
		    				//person.setType(Integer.parseInt(personstr[3]));
		    			}
		    			noticepersonService.insert(person);
		    			//noticeService.delete(Long.parseLong(s));
					}
		    		
		    	}
	    		map.put("result", "修改成功");
	    	}else{
	    		String idstr=notice.getPersonStr();
	    		notice.setSendId(user.getId());
	    		notice.setType(user.getType());
	    		notice.setCreatdate(new Date());
	    		noticeService.insert(notice);
	    		if(idstr!=null && !"".equals(idstr)){
		    		String[] id=idstr.split(",");
		    		for (String s : id) {
		    			String[] personstr=s.split("##");
		    			Noticeperson person=new Noticeperson();
		    			person.setIsread("1");
		    			person.setReceiveid(Long.parseLong(personstr[0]));
		    			person.setReceivetime(new Date());
		    			person.setReceivetype(personstr[1]);
		    			person.setGrouptype(personstr[2]);
		    			person.setNoticeId(notice.getId());
		    			if(personstr[3]!=null&&!personstr[3].equals("null")&&!personstr[3].equals("undefined")){
		    				person.setType(Integer.parseInt(personstr[3]));
		    			}
		    			if(personstr[4]!=null&&!personstr[4].equals("null")&&!personstr[4].equals("undefined")){
		    				person.setGroupId(Long.parseLong(personstr[4]));
		    			}
		    			noticepersonService.insert(person);
		    			//noticeService.delete(Long.parseLong(s));
					}
		    		
		    	}
	        	map.put("result", "添加成功");
	    	}
	    	
	    	
			
	    	return Utility.createJsonStr(map) ;
	    }
	    
	    
	    /**
	     * 已收通知Json
	     * @param 
	     * @return
	     */
	    @RequestMapping("/receiveNoticeJson")
	    @ResponseBody
	    public String receiveNoticeJson(Notice notice,Integer pageNumber ,Integer itemsOnPage) {
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	String username = (String)SecurityUtils.getSubject().getPrincipal();
	    	User user = userService.findByUsername(username);
	    	notice.setReceiveid(user.getId());
	    	notice.setType(user.getType());
	    	//notice.setReceivetype("0");
	    	notice.setSearchType("receive");
	    	 pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, noticeService,notice);
	    	 if(pageInfo.getList().size()>0){
	     		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     		for (Notice c : pageInfo.getList()) {
	     			if(c.getReceivetime()!=null){
	     				c.setTime(sdf.format(c.getReceivetime()));
	     			}
	     			
	 			}
	     		//map.put("list", pageInfo);
	     	}
	     	String retru = Utility.createJsonStr(pageInfo);
	 		return retru;
	    }
	    
	    /**
	     * 查看已收通知
	     * @param 
	     * @return
	     */
	    @RequestMapping("/receiveNotice")
	    public String receiveNotice(@ModelAttribute Notice notice,Model model) {
	        return "notice/noticeReceive";
	    }
	    
	    
	    /**
	     * 已发通知Json
	     * @param 
	     * @return
	     */
	    @RequestMapping("/sendNoticeJson")
	    @ResponseBody
	    public String sendNoticeJson(Notice notice,Integer pageNumber ,Integer itemsOnPage) {
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	String username = (String)SecurityUtils.getSubject().getPrincipal();
	    	User user = userService.findByUsername(username);
	    	notice.setSendId(user.getId());
	    	notice.setType(user.getType());
	    	notice.setSearchType("send");
	    	 pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, noticeService,notice);
	    	 if(pageInfo.getList().size()>0){
		     		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     		for (Notice c : pageInfo.getList()) {
		     			if(c.getSendtime()!=null){
		     				c.setTime(sdf.format(c.getSendtime()));
		     			}
		     			
		 			}
		     	}
	     	String retru = Utility.createJsonStr(pageInfo);
	 		return retru;
	    }
	    /**
	     * 查看已发通知
	     * @param 
	     * @return
	     */
	    @RequestMapping("/sendNotice")
	    public String sendNotice(@ModelAttribute Notice notice,Model model) {
	        return "notice/noticeSend";
	    }
	    /**
	     * 查看接收人
	     * @param 
	     * @return
	     */
	    @RequestMapping(value = "/personlist.html", method = RequestMethod.GET)
	    public String showMaintainForm( Long id, Model model) {
	        model.addAttribute("personlist", noticepersonService.noticePersonList(id));
	        return "notice/noticePersonList";
	    }
	    
	    /**
	     * 查看草稿箱
	     * @param 
	     * @return
	     */
	    @RequestMapping(value = "/draft", method = RequestMethod.GET)
	    public String daft( Model model) {
//	    	String username = (String)SecurityUtils.getSubject().getPrincipal();
//	    	User user = userService.findByUsername(username);
//	        model.addAttribute("daftlist", noticeService.findDaftList(user.getId()));
	        return "notice/noticeDrafts";
	    }
	    
	    /**
	     * 草稿箱Json
	     * @param 
	     * @return
	     */
	    @RequestMapping("/draftJson")
	    @ResponseBody
	    public String draftJson(Notice notice,Integer pageNumber ,Integer itemsOnPage) {
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	String username = (String)SecurityUtils.getSubject().getPrincipal();
	    	User user = userService.findByUsername(username);
	    	notice.setSendId(user.getId());
	    	notice.setType(user.getType());
	    	notice.setSearchType("draft");
	    	 pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, noticeService, notice);
	    	 if(pageInfo.getList().size()>0){
		     		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     		for (Notice c : pageInfo.getList()) {
		     			if(c.getCreatdate()!=null){
		     				c.setTime(sdf.format(c.getCreatdate()));
		     			}
		     			
		 			}
		     	}
	     	String retru = Utility.createJsonStr(pageInfo);
	 		return retru;
	    }
	    
	    /**
	     * 批量发送
	     * @param ids
	     * @return
	     */
	    @RequestMapping(value="/batchSend")
	    @ResponseBody
	    public String batchSend(String ids,HttpServletResponse response) {
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	int num=0;
	    	if(ids!=null && !"".equals(ids)){
	    		String[] id=ids.split(",");
	    		num=id.length;
	    		for (String s : id) {
	    			//Exam exam=examService.find(Long.parseLong(s));
	    			//exam.setPubstate("0");
	    			noticeService.updatePubStateById(Long.parseLong(s));
				}
	    		map.put("result", "发送成功");
	    		map.put("num", num);
	    	}else{
	    		map.put("result", "请勾择修改项");
	    	}
	    	
	    	return Utility.createJsonStr(map) ;
	    }
	    /**
	     * 跳到修改草稿页面
	     * @param 
	     * @return
	     */
	    @RequestMapping(value = "/updatedeaft", method = RequestMethod.GET)
	    public String updatedeaft( Long id, Model model,String isAdd) {
	    	
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Notice notice=noticeService.find(id);
	    	if(notice!=null && notice.getSendtime()!=null){
	    		model.addAttribute("sendtimeTemp", sdf.format(notice.getSendtime()));
			}
	    	model.addAttribute("notice", notice);
	    	//model.addAttribute("personlist", noticepersonService.noticePersonList(id));
	    	List<Noticeperson> personlist=noticepersonService.noticePersonList(id);
	    	if(personlist.size()>0){
	     		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String pStr="";
	    		String pName="";
	    		String type="";
	     		for (int i=0;i<personlist.size();i++) {
	     			if(i<personlist.size()-1){
	     				pStr+=personlist.get(i).getReceiveid()+"##"+personlist.get(i).getReceivetype()+"##"+personlist.get(i).getGrouptype()+"##"+personlist.get(i).getType()+"##"+personlist.get(i).getGroupId()+",";
	     				pName+=personlist.get(i).getUsername()+",";
		     			
	     			}else{
	     				pStr+=personlist.get(i).getReceiveid()+"##"+personlist.get(i).getReceivetype()+"##"+personlist.get(i).getGrouptype()+"##"+personlist.get(i).getType()+"##"+personlist.get(i).getGroupId();
	     				pName+=personlist.get(i).getUsername();
	     			}
	     			
	     			
	 			}
	     		notice.setPersonStr(pStr);
	     		notice.setPersonName(pName);
	     	}
	    	
	    	if(isAdd!=null&&!isAdd.trim().equals("")){
	    		model.addAttribute("isAdd", isAdd);
	    		
	    	}
	        return  "notice/noticeAdd";
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
	    			noticeService.delete(Long.parseLong(s));
	    			noticepersonService.deleteByNoticeId(Long.parseLong(s));
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
	     * 批量删除通知对象表的数据
	     */
	    @RequestMapping("/batchDeletePerson")
	    @ResponseBody
	    public String batchDeletePerson(String personids) {
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	int num=0;
	    	if(personids!=null && !"".equals(personids)){
	    		String[] id=personids.split(",");
	    		num=id.length;
	    		for (String s : id) {
	    			//noticeService.delete(Long.parseLong(s));
	    			noticepersonService.delete(Long.parseLong(s));
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
	     * 修改草稿箱
	     * @param 
	     * @return
	     */
	    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	    public String update(@ModelAttribute Notice notice,String idstr,RedirectAttributes redirectAttributes) {
	    	
	    	if(notice.getId()!=null&&!notice.getId().equals("")){
	    		noticeService.update(notice);
	    		noticepersonService.deleteByNoticeId(notice.getId());
		    	if(idstr!=null && !"".equals(idstr)){
		    		String[] id=idstr.split(",");
		    		for (String s : id) {
		    			String[] personstr=s.split("##");
		    			Noticeperson person=new Noticeperson();
		    			person.setIsread("1");
		    			person.setReceiveid(Long.parseLong(personstr[0]));
		    			person.setReceivetime(new Date());
		    			person.setReceivetype(personstr[1]);
		    			person.setGrouptype(personstr[2]);
		    			person.setNoticeId(notice.getId());
		    			noticepersonService.insert(person);
		    			//noticeService.delete(Long.parseLong(s));
					}
		    		
		    	}
		    	redirectAttributes.addFlashAttribute("msg", "修改成功");
	    	}else{
	    		redirectAttributes.addFlashAttribute("msg", "修改失败");
	    	}
	    	
	        
	        return "redirect:/notice/daft";
	    }
	    /**
	     * 接收人json对象
	     * @param 
	     * @return
	     */
	    @RequestMapping("/addreceive1")
	    @ResponseBody
	    public void addreceive1(HttpServletResponse response) throws Exception {
	    	Map<String,List<Object>> map=new HashMap<String,List<Object>>();
	    	
	    	String username = (String)SecurityUtils.getSubject().getPrincipal();
	    	User user = userService.findByUsername(username);
	    	
	    	
	    	List<Grade> gradelist= gradeService.selectGradeBySchool(user.getSchoolId());
			Iterator<Grade> gradeiterator = gradelist.iterator();
			Grade grade;
			while (gradeiterator.hasNext()) {
				grade = gradeiterator.next();
				int gradeId=grade.getId();
				
				List<Teacher>  tealist=teacherService.findTeacherByGradeId(gradeId);
				
				for (Teacher teacher : tealist) {
					if(map.containsKey("teacher##"+grade.getName()+"##"+grade.getId())){
						List<Object> list=map.get("teacher##"+grade.getName()+"##"+grade.getId());
						list.add(teacher);
					}else{
						List<Object> list=new ArrayList<Object>();
						list.add(teacher);
						map.put("teacher##"+grade.getName()+"##"+grade.getId(), list);
					}
				}

			}
	    	
	    	//Map<String,Object> map=new HashMap<String,Object>();
	    	List<Student> stulist= studentService.findStuTreeBySchoolId(user.getSchoolId());
	    	
			for (Student student : stulist) {
				if(map.containsKey("student##"+student.getClassname()+"##"+student.getClassId().toString())){
					List<Object> list=map.get("student##"+student.getClassname()+"##"+student.getClassId().toString());
					list.add(student);
				}else{
					List<Object> list=new ArrayList<Object>();
					list.add(student);
					map.put("student##"+student.getClassname()+"##"+student.getClassId().toString(), list);
				}
			}
			
			UserGroup group =new UserGroup();
			group.setCreateid(user.getId());
			group.setType(user.getType());
			List<UserGroup> grouplist=userGroupService.findUserGroupByTeaId(group);
			Iterator<UserGroup> groupiterator = grouplist.iterator();
			UserGroup userGroup;
			while (groupiterator.hasNext()) {
				userGroup = groupiterator.next();
				String stuId=userGroup.getStuId();
		    	String teaId=userGroup.getTeacherId();
		    	userGroup.setStuId(stuId.trim());
		    	userGroup.setTeacherId(teaId.trim());
		    	List<UserGroup> usergrouplist=userGroupService.findStuAndTeaByID(userGroup);
		    	List<Object> list=new ArrayList<Object>();
		    	list.addAll(usergrouplist);
		    	map.put("usergroup##"+userGroup.getName()+"##"+userGroup.getId(), list);
			}
	    	
	    	
	    	
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String nodes = com.alibaba.fastjson.JSON.toJSONString(map);
			response.getWriter().print(nodes);
			return;
	       // return "exam/receive";
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
	    	//Map<String,List<Object>> map=null;
	    	
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
				//map=new HashMap<String,List<Object>>();
				List<Object> list=new ArrayList<Object>();
				if(tealist!=null&&tealist.size()>0){
					list.addAll(tealist);
					student.setStrName("teacher##"+grade.getName()+"##"+grade.getId());
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
			UserGroup group =new UserGroup();
			group.setCreateid(user.getId());
			group.setType(user.getType());
			List<UserGroup> grouplist=userGroupService.findUserGroupByTeaId(group);
			Iterator<UserGroup> groupiterator = grouplist.iterator();
			UserGroup userGroup;
			while (groupiterator.hasNext()) { 
				userGroup = groupiterator.next();
				String stuId=userGroup.getStuId();
		    	String teaId=userGroup.getTeacherId();
		    	userGroup.setStuId(stuId.trim());
		    	userGroup.setTeacherId(teaId.trim());
		    	List<UserGroup> usergrouplist=userGroupService.findStuAndTeaByID(userGroup);
		    	List<Object> list=new ArrayList<Object>();
		    	list.addAll(usergrouplist);
		    	Student student= new Student();
		    	student.setStrName("usergroup##"+userGroup.getName()+"##"+userGroup.getId());
		    	student.setStrlist(list);
		    	stu.add(student);
		    	//map.put("usergroup##"+userGroup.getName()+"##"+userGroup.getId(), list);
			}
	    	
			String nodes = com.alibaba.fastjson.JSON.toJSONString(stu);
			return nodes;
	       // return "exam/receive";
	    }
	    
	    /**
	     * 更改已读状态
	     * @param 
	     * @return
	     */
	    @RequestMapping(value = "/{personid}/updateread", method = RequestMethod.POST)
	    @ResponseBody
	    public String updateIsRead(@PathVariable("personid") Long personid,HttpServletResponse response) {
	    	Map<String,Object> map=new HashMap<String,Object>();
//	    	String username = (String)SecurityUtils.getSubject().getPrincipal();
//	    	User user = userService.findByUsername(username);
//	    	//Notice notice=noticeService.find(id);
//	    	Noticeperson person=new Noticeperson();
//	    	person.setNoticeId(id);
//	    	person.setReceiveid(user.getId());
//	    	person.setReceivetype("0");
//	    	List<Noticeperson> personList=noticepersonService.findPersonBynoticeId(person);
	    	//if(personList.size()>0)
	    	noticepersonService.updateIsreadById(personid);
	    	map.put("result", "更改成功");
	    	try {
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(Utility.createJsonStr(map));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return  null;
	    }

}
