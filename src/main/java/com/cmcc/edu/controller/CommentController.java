package com.cmcc.edu.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.edu.entity.Class;
import com.cmcc.edu.entity.Comment;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.ICommentService;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;

/**
 * 学生点评
 * @author
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends PageController<Comment>{

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IStudentService studentService;
    
    private PageInfo<Comment> pageInfo;
    private  String pubstate="1";
    private  String isread="1";
   
    /**
     * 跳转到发布点评页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String saveJump(Model model){
    	
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);//当前登录用户信息
    	Class cla=new Class();
    	cla.setTeacherId(user.getId());
    	List<Class> claList=classService.classList(cla);//根据教师得到班级
    	Student student=new Student();
    	if(claList.size()>0){
    		student.setClassId(claList.get(0).getId());
    		List<Student> stuList=studentService.findStuByClassIdAndGradeId(student);//学生
    		if(stuList.size()>0){
        		model.addAttribute("stuList", stuList);
        		model.addAttribute("stuid", null);
        		model.addAttribute("gardeName", claList.get(0).getGradeName()+" "+claList.get(0).getName());
        	}
    		
    	}	
    	
    	
    	
    	return "comment/save";
    }

    /**
     * 发布学生点评
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(@ModelAttribute Comment comment,String year,String hours,String minutes) throws IOException, ParseException{
    	Map<String,Object> map=new HashMap<String,Object>();
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	
    	if(year!=null && !"".equals(year) && hours!=null && !"".equals(hours) && minutes!=null && !"".equals(minutes)){
    		String time=year+" "+hours+":"+minutes+":00";
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		comment.setSendtime(sdf.parse(time));
    	}
    	if(comment!=null){
    		comment.setCreatdate(new Date());
    		if(comment.getId()!=null){
    			comment.setSendId(user.getId().intValue());//发布人
        		comment.setIsread(isread);//未读
        		comment.setPublishtime(new Date());
        		commentService.update(comment);
        		map.put("result", true);
    		}else{
    			comment.setSendId(user.getId().intValue());//发布人
        		comment.setIsread(isread);//未读
        		comment.setPublishtime(new Date());
        		commentService.insert(comment);
        		map.put("result", true);
    		}
    		
    	}else{
    		map.put("result", false);
    	}
    	
    	return Utility.createJsonStr(map);
    }
    
    /**
     * 跳转到查询页面
     */
    @RequestMapping("/selectJump")
    public String selectJump(){
    	return "comment/select";
    }
    /**
     * 查看点评列表
     * @throws ParseException 
     * @throws IOException 
     */
    @RequestMapping("/select")
    @ResponseBody
    public String select(String start,String end,String content,Integer pageNumber ,Integer itemsOnPage) throws ParseException, IOException{
    	Comment comment =new Comment();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if(start!=null && !"".equals(start)){		
    		comment.setStarttime(sdf.parse(start));
    	}else{
    		comment.setStarttime(sdf.parse("1970-01-01"));
    	}
    	if(end!=null && !"".equals(end)){
    		comment.setEndtime(sdf.parse(end));
    	}else{
    		comment.setEndtime(new Date());
    	}
    	if(content!=null && !"".equals(content)){
    		comment.setContent(content);
    	}
    	pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, commentService,comment);
    	
    	if(pageInfo.getList().size()>0){
    		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		for (Comment c : pageInfo.getList()) {
				c.setPublishtimeName(sdf2.format(c.getPublishtime()));
			}
    	}
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    	
    }
    /**
     * 查看点评
     */
    @RequestMapping("/selectFindOne.html")
    public String selectFindOne(String id,Model model){
    	if(id!=null  && !"".equals(id)){
    		Comment com=commentService.find((long)Integer.parseInt(id));
    		if(com!=null && com.getSendtime()!=null){//定时时间转换成String类型
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			com.setSendtimeName(sdf.format(com.getSendtime()));
    		}
    		if(com.getReceiveStuId()!=null && !"".equals(com.getReceiveStuId())){
    			String[] str=com.getReceiveStuId().split(",");
    			for (String s : str) {
    				Student student=new  Student();
    				student.setId((long)Integer.parseInt(s));
    				List<Student> stu=studentService.findStuByClassIdAndGradeId(student);
    				if(stu.size()>0){
    					if(com.getReceiveStuIdName()!=null && !"".equals(com.getReceiveStuIdName())){
    						com.setReceiveStuIdName(com.getReceiveStuIdName()+","+stu.get(0).getName());
    					}else{
    						com.setReceiveStuIdName(stu.get(0).getName());
    					}
    					
    				}
				}
    		}
    		model.addAttribute("comment", com);
    	}
    	
    	return "comment/find";
    }
    /**
     * 转发
     */
    @RequestMapping("/forword")
    public String forword(String id,Model model){
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);//当前登录用户信息
    	Class cla=new Class();
    	cla.setTeacherId(user.getId());
    	List<Class> claList=classService.classList(cla);//根据教师得到班级
    	Student student=new Student();
    	if(claList.size()>0){
    		student.setClassId(claList.get(0).getId());
    	}	
    	
    	List<Student> stuList=studentService.findStuByClassIdAndGradeId(student);//学生
    	if(stuList.size()>0){
    		model.addAttribute("stuList", stuList);
    	}
    	if(id!=null && !"".equals(id)){
    		Comment comment=commentService.find((long)Integer.parseInt(id));
    		if(comment!=null && comment.getSendtime()!=null){
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
    			String sendtime=sdf.format(comment.getSendtime());
    			model.addAttribute("year", sendtime.substring(0, 10));
    			model.addAttribute("hourse", sendtime.substring(11, 13));
    			model.addAttribute("minutes", sendtime.substring(14, 16));
    		}
    		if(comment.getReceiveStuId()!=null && !"".equals(comment.getReceiveStuId())){
    			String[] stuid=comment.getReceiveStuId().split(","); 
    			for (String s : stuid) {
					for (Student stu : stuList) {
						if(stu.getId()==Integer.parseInt(s)){
							if(comment.getReceiveStuIdName()!=null && !"".equals(comment.getReceiveStuIdName())){
								comment.setReceiveStuIdName(comment.getReceiveStuIdName()+","+stu.getName());
							}else{
								comment.setReceiveStuIdName(stu.getName());
							}		
						}
					}
				}
    			model.addAttribute("stuid", stuid);
    		}
    		comment.setId(null);
    		model.addAttribute("comment", comment);
    	}
    	return "comment/save";
    }
    /**
     * 批量删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String ids){
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(ids!=null && !"".equals(ids)){
    		String[] id=ids.split(",");
    		for (String s : id) {
    			commentService.delete((long)Integer.parseInt(s));
			}
    		map.put("num", id.length);
    		map.put("result", true);
    	}else{
    		map.put("result", false);
    	}
    	return Utility.createJsonStr(map);
    }
    /**
     * 草稿跳转
     */
    @RequestMapping("/deaftJump")
    public String deaftJump(){
    	return "comment/deaft";
    }
    /**
     * 草稿查询
     * @param model
     * @param pageNumber
     * @param itemsOnPage
     * @return
     */
    @RequestMapping("/deaft")
    @ResponseBody
    public String deaft(Integer pageNumber,Integer itemsOnPage){
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	Comment comment=new Comment();
    	comment.setPubstate(pubstate);
    	comment.setSendId(user.getId().intValue());
    	pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, commentService,comment);
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(pageInfo.getList().size()>0){
    		for (Comment comment2 : pageInfo.getList()) {
    			comment2.setPublishtimeName(sdf.format(comment2.getPublishtime()));
			}   		
    	}
    	String retru = Utility.createJsonStr(pageInfo);
    	return retru;
    }
    /**
     * 立即发送
     */
    @RequestMapping("/send")
    @ResponseBody
    public String send(String ids){
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(ids!=null && !"".equals(ids)){
    		String[] id=ids.split(",");
    		for (String s : id) {
    			Comment c=commentService.find((long)Integer.parseInt(s));
    			if(c!=null){
    				c.setPublishtime(new Date());
    				c.setPubstate("0");
    				commentService.update(c);
    			}  			
			}
    		map.put("num", id.length);
    		map.put("result", true);
    	}else{
    		map.put("result", false);
    	}
    	return Utility.createJsonStr(map);
    }
    /**
     * 进入修改页面
     */
    @RequestMapping("/updateFindOne")
    public String updateFindOne(String id,Model model){
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);//当前登录用户信息
    	Class cla=new Class();
    	cla.setTeacherId(user.getId());
    	List<Class> claList=classService.classList(cla);//根据教师得到班级
    	Student student=new Student();
    	if(claList.size()>0){
    		student.setClassId(claList.get(0).getId());
    	}	
    	
    	List<Student> stuList=studentService.findStuByClassIdAndGradeId(student);//学生
    	if(stuList.size()>0){
    		model.addAttribute("stuList", stuList);
    	}
    	
    	if(id!=null && !"".equals(id)){
    		Comment comment=commentService.find((long)Integer.parseInt(id));
    		if(comment!=null && comment.getSendtime()!=null){
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			String sendtime=sdf.format(comment.getSendtime());
    			model.addAttribute("year", sendtime.substring(0, 10));
    			model.addAttribute("hourse", sendtime.substring(11, 13));
    			model.addAttribute("minutes", sendtime.substring(14, 16));
    		}
    		if(comment.getReceiveStuId()!=null && !"".equals(comment.getReceiveStuId())){
    			String[] stuid=comment.getReceiveStuId().split(","); 
    			for (String s : stuid) {
					for (Student stu : stuList) {
						if(stu.getId()==Integer.parseInt(s)){
							if(comment.getReceiveStuIdName()!=null && !"".equals(comment.getReceiveStuIdName())){
								comment.setReceiveStuIdName(comment.getReceiveStuIdName()+","+stu.getName());
							}else{
								comment.setReceiveStuIdName(stu.getName());
							}		
						}
					}
				}
    			model.addAttribute("stuid", stuid);
    		}
    		
    		model.addAttribute("comment", comment);
    	}
    	
    	
    	return "comment/save";
    }
}
