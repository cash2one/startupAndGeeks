package com.cmcc.edu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmcc.edu.entity.Class;
import com.cmcc.edu.entity.Course;
import com.cmcc.edu.entity.Exam;
import com.cmcc.edu.entity.ExamType;
import com.cmcc.edu.entity.Score;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.ICourseService;
import com.cmcc.edu.service.IExamService;
import com.cmcc.edu.service.IExamTypeService;
import com.cmcc.edu.service.IScoreService;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;

/**
 * 考试管理管理
 * @author 
 *
 */
@Controller
@RequestMapping("/exam")
public class ExamController extends PageController<Exam>{

	@Autowired
    private IExamService examService;
	
	@Autowired
    private IExamTypeService examtypeService;
    @Autowired
    private IScoreService scoreService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IClassService classService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IStudentService studentService;
    
    private PageInfo<Exam> pageInfo;
    private String enterstate="0";

    /**
     * 发布考试跳转
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showpublishForm(Model model) {
    	Course course=new Course();
    	List<Course> couList=courseService.courseList(course);
    	ExamType examtype= new ExamType();
    	List<ExamType> examtypeList=examtypeService.selectExamTypeList(examtype);
    	model.addAttribute("examtypeList", examtypeList);
    	 model.addAttribute("couseList", couList);
    	 return "exam/examAdd";
    }
    /**
     * 发布考试
     * @param homework
     * @return
     */
    @RequestMapping(value="/save" ,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String save(@ModelAttribute Exam exam,String sTime,String etime,String dtime) throws IOException, ParseException{ 
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	Map<String,Object> map=new HashMap<String,Object>();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	exam.setStarttime(sdf.parse(sTime));
    	exam.setEndtime(sdf.parse(etime));
    	if(exam.getPubstate().equals("0")){
    		//exam.setExamstate("1");
    		exam.setEnterstate("1");
    		exam.setPublishtime(new Date());
    	}
    	if(exam.getSendtype().equals("1")){
    		exam.setSendtime(sdf.parse(dtime));
    	}
    	
		//comment.setSendtime(sdf.parse(time));
    	if(exam.getId()!=null){
    		examService.update(exam);
    		map.put("result", "修改成功");
    	}else{
    		exam.setSendId(user.getId());
    		examService.insert(exam);
        	map.put("result", "添加成功");
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    /**
     * 草稿箱
     * @param 
     * @return
     */
    @RequestMapping("/draftlist")
    public String deaft(Model model) {
        return "exam/examDrafts";
    }
    
    /**
     * 查看考试首页
     * @param 
     * @return
     */
    @RequestMapping("/examlist")
    public String searchExam(@ModelAttribute Exam exam,Model model) {
    	
        return "exam/examToview";
    }
    
    /**
     * 草稿箱json
     * @param 
     * @return
     */
    @RequestMapping("/draftlistjson")
    @ResponseBody
    public String draftlistjson(Integer pageNumber ,Integer itemsOnPage) {
    	Exam exam= new Exam();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	exam.setExamtype("draftlist");
        pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, examService,exam);
        if(pageInfo.getList().size()>0){
    		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		for (Exam c : pageInfo.getList()) {
    			
    			
    			if(c.getClassid()!=null&&!c.getClassid().equals("")){
    				StringBuilder s = new StringBuilder();
    				String[] classId=c.getClassid().split(",");
        	        for(String claId : classId) {
        	            Class calss = classService.findOne(Integer.parseInt(claId));
        	            if(calss == null) {
        	                return "";
        	            }
        	            s.append(calss.getName());
        	            s.append(",");
        	        }

        	        if(s.length() > 0) {
        	            s.deleteCharAt(s.length() - 1);
        	        }
    				
        	        
        	        c.setClassName(s.toString());
    			}
    			if(c.getEntertime()!=null){
    				c.setTime(sdf.format(c.getEntertime()));
    			}
    			
			}
    		//map.put("list", pageInfo);
    	}
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    }
    
    /**
     * 查看考试json
     * @param 
     * @return
     */
    @RequestMapping("/examlistjson")
    @ResponseBody
    public String searchExamjson( Exam exam,Integer pageNumber ,Integer itemsOnPage) {
    	  exam.setExamtype("examlist");
          pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, examService,exam);
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    }
    
    /**
     * 课程,考试类型ajax
     * @param 
     * @return
     */
    @RequestMapping("/courseJson")
    @ResponseBody
    public String courseJson(Model model) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	Course course=new Course();
    	List<Course> couList=courseService.courseList(course);
    	ExamType examtype= new ExamType();
    	List<ExamType> examtypeList=examtypeService.selectExamTypeList(examtype);
    	map.put("couseList", couList);
    	map.put("examtypeList", examtypeList);
    	List<Class> clList=classService.classList(new Class());
    	
    	map.put("classList", clList);
		return Utility.createJsonStr(map) ;
    }
    
    /**
     * 成绩查询首页
     * @param 
     * @return
     */
    @RequestMapping("/examscore")
    public String searchExamScore(@ModelAttribute Exam exam,Model model) {
        return "exam/examEntry";
    }
    
    /**
     * 成绩查询json
     * @param 
     * @return
     */
    @RequestMapping("/examscoreJson")
    @ResponseBody
    public String ExamScoreJson( Exam exam,Integer pageNumber ,Integer itemsOnPage) {
    	exam.setExamtype("examscore");
        pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, examService,exam);
        if(pageInfo.getList().size()>0){
    		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		for (Exam c : pageInfo.getList()) {
    			
    			
    			if(c.getClassid()!=null&&!c.getClassid().equals("")){
    				StringBuilder s = new StringBuilder();
    				String[] classId=c.getClassid().split(",");
        	        for(String claId : classId) {
        	            Class calss = classService.findOne(Integer.parseInt(claId));
        	            if(calss == null) {
        	                return "";
        	            }
        	            s.append(calss.getName());
        	            s.append(",");
        	        }

        	        if(s.length() > 0) {
        	            s.deleteCharAt(s.length() - 1);
        	        }
    				
        	        
        	        c.setClassName(s.toString());
    			}
    			
			}
    		//map.put("list", pageInfo);
    	}
        return Utility.createJsonStr(pageInfo);
    }
    
    /**
     * 成绩详细查询json
     * @param 
     * @return
     */
    @RequestMapping(value="/scoredetailJson",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String searchExamScoreDetail(Long id) {
    	//Map<String,Object> map=new HashMap<String,Object>();
    	List<Score> scorelist=scoreService.selectScoreByExamId(id);
    	
    	Map<String,List<Score>> map=new HashMap<String,List<Score>>();
		for (Score score : scorelist) {
			if(map.containsKey(score.getClassId().toString()+"##"+score.getClassName()+"##"+score.getCourseName())){
				List<Score> list=map.get(score.getClassId().toString()+"##"+score.getClassName()+"##"+score.getCourseName());
				list.add(score);
			}else{
				List<Score> list=new ArrayList<Score>();
				list.add(score);
				map.put(score.getClassId().toString()+"##"+score.getClassName()+"##"+score.getCourseName(), list);
			}
		}
		String nodes = com.alibaba.fastjson.JSON.toJSONString(map);
			
		return nodes;
    }
    
    /**
     * 跳转到成绩详细查询页面
     * @param 
     * @return
     */
    @RequestMapping("/scoredetaillist.html")
    public String searchExamScoreDetail( Long id,Model model) {
    	model.addAttribute("id", id);
        return "exam/examGradeList";
    }
    /**
     * 批量删除草稿箱及查看考试中的信息
     */
    @RequestMapping(value="/batchDelete")
    @ResponseBody
    public String batchDelete(String ids) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	int num=0;
    	if(ids!=null && !"".equals(ids)){
    		String[] id=ids.split(",");
    		num=id.length;
    		for (String s : id) {
    			examService.delete(Long.parseLong(s));
    			scoreService.deleteByexamId(Long.parseLong(s));
			}
    		map.put("result", "删除成功");
    		map.put("num", num);
    	}else{
    		map.put("result", "请勾择删除项");
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    
    /**
     * 批量删除考试成绩
     */
    @RequestMapping(value="/batchDeleteScore")
    @ResponseBody
    public String batchDeleteScore(String ids) {
    	int num=0;
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(ids!=null && !"".equals(ids)){
    		String[] id=ids.split(",");
    		num=id.length;
    		for (String s : id) {
    			//examService.delete(Long.parseLong(s));
    			scoreService.deleteByexamId(Long.parseLong(s));
    			Exam exam=examService.find(Long.parseLong(s));
    			exam.setEnterstate("1");
    			examService.updateEnterStateById(exam);
			}
    		map.put("result", "删除成功");
    		map.put("num", num);
    	}else{
    		map.put("result", "请勾择删除项");
    	}
    	
    	return Utility.createJsonStr(map) ;
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
    			examService.updatePubStateById(Long.parseLong(s));
			}
    		map.put("result", "发送成功");
    		map.put("num", num);
    	}else{
    		map.put("result", "请勾择修改项");
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    
    @RequestMapping("tree")
	@ResponseBody
	public void jsonTree(String examId,String method,HttpServletResponse response)  throws Exception{
    	
    	Exam exam=null;
		if("update".equals(method)&&examId!=null&&!examId.trim().equals("")){
			
			exam=examService.find(Long.parseLong(examId));
			
		}
    	
    	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//Long schoolId=100202001l;
		List<Student> stulist= studentService.findStuTreeBySchoolId(100202001l);
		Iterator<Student> iterator = stulist.iterator();
		Student stu;
		//Class sl;
		int graId=-1;
		int classId=-1;
		long schoolId=-1;
		boolean isExist=false;
		while (iterator.hasNext()) {
					    stu=iterator.next();
					//if(stu.getSchoolId()!=null&&stu.getSchoolId()!=schoolId){
						if(!isExist){
							schoolId=stu.getSchoolId();
							Map<String, Object> mapschool = new HashMap<String, Object>(); 
							//mapschool.put("nodeType", "school");
							mapschool.put("id", schoolId+"##school");
							mapschool.put("name", stu.getSchname());
							mapschool.put("nodetype", "school");
							mapschool.put("pId", null);
							list.add(mapschool);
						}
						if(stu.getGradeId()!=null&&stu.getGradeId()!=graId){
							graId=stu.getGradeId();
							//int newgraId=stu.getGradeId();
							Map<String, Object> mapsgrade = new HashMap<String, Object>(); 
							mapsgrade.put("id", graId+"##grade");
							mapsgrade.put("name", stu.getGraname());
							mapsgrade.put("pId", schoolId+"##school");
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
								
								if(exam!=null){
									if(exam.getClassid().indexOf(String.valueOf(classId))!=-1){
										mapclass.put("checked", true);
									}
								}
								list.add(mapclass);
								
								
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
								if(exam!=null){
									if(exam.getClassid().indexOf(String.valueOf(classId))!=-1){
										mapclass.put("checked", true);
									}
								}
								list.add(mapclass);
								
							}
							
							
						}
		 }			
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String nodes = com.alibaba.fastjson.JSON.toJSONString(list);
		response.getWriter().print(nodes);
		return ;
	
   
  }
    
    /**
     * 接收人json对象
     * @param 
     * @return
     */
    @RequestMapping(value="/addreceive",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String addreceive() throws Exception {
    	//Map<String,Object> map=new HashMap<String,Object>();
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	List<Student>  stu= new ArrayList<Student>();
    	List<Student> stulist= studentService.findClassBySchoolId(user.getSchoolId());
    	//Map<String,List<Student>> map=new HashMap<String,List<Student>>();
    	List<Object> slist=null;
    	Student stuobj= null;
    	int graId=-1;
		for (Student student : stulist) {
			if(graId!=-1&&student.getGradeId()==graId){
				//map.containsKey("student##"+student.getClassname()+"##"+student.getClassId().toString());
				//List<Object> list=map.get("student##"+student.getClassname()+"##"+student.getClassId().toString());
				slist.add(student);
			}else{
				if(stuobj!=null&&slist.size()>0){
					stuobj.setStrlist(slist);
					stu.add(stuobj);
				}
				graId=student.getGradeId();
				stuobj=new Student();
				stuobj.setStrName(student.getGradeId().toString()+"##"+student.getGraname());
				//map=new HashMap<String,List<Object>>();
				slist=new ArrayList<Object>();
				//if(student.getClassId()!=null){
					slist.add(student);
				//}
				
				//map.put("student##"+student.getClassname()+"##"+student.getClassId().toString(), list);
			}
		}
		if(stuobj!=null&&slist.size()>0){
			stuobj.setStrlist(slist);
			stu.add(stuobj);
		}
		String nodes = com.alibaba.fastjson.JSON.toJSONString(stu);
		//response.getWriter().print(nodes);
		return nodes;
       // return "exam/receive";
    }
    
    @RequestMapping(value = "/updatedeaft", method = RequestMethod.GET)
    public String updatedeaft( Long id, Model model) {
    	Exam exam=examService.find(id);
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(exam!=null && exam.getStarttime()!=null){
    		model.addAttribute("startTime", sdf.format(exam.getStarttime()));
		}
    	
    	if(exam!=null && exam.getEndtime()!=null){
    		model.addAttribute("endTime", sdf.format(exam.getEndtime()));
		}
    	
    	if(exam!=null && exam.getSendtime()!=null){
    		model.addAttribute("sendtimeTemp", sdf.format(exam.getSendtime()));
		}
    	if(exam.getClassid()!=null&&!exam.getClassid().equals("")){
			StringBuilder s = new StringBuilder();
			String[] classId=exam.getClassid().split(",");
	        for(String claId : classId) {
	            Class calss = classService.findOne(Integer.parseInt(claId));
	            if(calss == null) {
	                return "";
	            }
	            s.append(calss.getName());
	            s.append(",");
	        }

	        if(s.length() > 0) {
	            s.deleteCharAt(s.length() - 1);
	        }
			
	        
	        exam.setClassName(s.toString());
		}
    	Course course=new Course();
    	List<Course> couList=courseService.courseList(course);
    	ExamType examtype= new ExamType();
    	List<ExamType> examtypeList=examtypeService.selectExamTypeList(examtype);
    	model.addAttribute("examtypeList", examtypeList);
    	 model.addAttribute("couseList", couList);
    	model.addAttribute("exam", exam);
        return  "exam/examAdd";
    }
    
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Exam exam, RedirectAttributes redirectAttributes) {
    	examService.update(exam);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/exam/deaft";
    }
   
    /**
     * 跳转到成绩导入页面
     */
    @RequestMapping("importJump")
    public String importJump(){
    	return "exam/examTypist";
    }
    /**
     * 导入excel文档信息
     * @throws IOException 
     * 
     */
    @RequestMapping("forExcel")
    @ResponseBody
    public String forExcel(String file,String cla,String exam,String name) throws IOException{
    	Map<String,Object> map=new HashMap<String,Object>();	
    	scoreService.formExcel(Integer.parseInt(cla), Integer.parseInt(exam), name, file);
    	Exam e=examService.find((long)Integer.parseInt(exam));
    	if(e!=null){
    		e.setEnterstate(enterstate);//上学成绩后将考试表的字段改成已录入状态
    	}
    	examService.update(e);
    	
    	map.put("result", true);
    	return Utility.createJsonStr(map);
    }
    /**
     * 异步加载班级，考试名臣，成绩单名称
     */
    @RequestMapping("loading")
    @ResponseBody
    public String loading(){
    	Map<String,Object> map=new HashMap<String,Object>();
    	List<Class> classList=classService.classList(new Class());
    	List<Exam> examList=examService.selectExamList(new Exam());
    	if(classList.size()>0){
    		for (Class c : classList) {
    			c.setName(c.getGradeName()+" "+c.getName());
    		}
    		map.put("classList", classList);
    	}
    	if(examList.size()>0){
    		map.put("examList", examList);
    	}
    	return Utility.createJsonStr(map);
    	
    }

    /**
     * 导出excel
     * @throws IOException 
     */
    @RequestMapping("download")
    @ResponseBody
    public String download(HttpServletRequest request, String ids,HttpServletResponse response) throws IOException{
    	if(ids!=null && !"".equals(ids)){
    		List<Score> scoreList=scoreService.selectScoreByExamId((long)Integer.parseInt(ids));
    		String path=this.getClass().getClassLoader().getResource("").getPath();
    		scoreService.downLoadExcel(path.substring(1, path.length()-8)+"score.xls", scoreList);//将生成的excel文件放入指定的路径中
    		//将指定路径中的文件下载到本地
    		// path是指欲下载的文件的路径。
            File file = new File(path.substring(1, path.length()-8)+"score.xls");
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path.subSequence(1, path.length()-8)+"score.xls"));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            
            //下载好excel文件后，删除指定路径中的excel文件
            File f=new File(path.substring(1, path.length()-8)+"score.xls"); 
            if(f.exists() && f.isFile()){ 
            	f.delete();
            }
    	}
    	
    	return null;
    }
    
    /**
     * 查看考试模块 ，点击未录入，跳转录入页面
     * @param ids
     * @param model
     * @return
     */
    @RequestMapping("jumpImport")
    public String jumpImport(String ids,Model model){
    	Exam exam=examService.find((long)Integer.parseInt(ids));
    	if(exam!=null){
    		model.addAttribute("e", exam);
    	}
    	return this.importJump();
    }

}
