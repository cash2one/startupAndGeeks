package com.chuanggu.app.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chuanggu.app.entity.Class;
import com.chuanggu.app.entity.Grade;
import com.chuanggu.app.entity.Student;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.service.IClassService;
import com.chuanggu.app.service.IGradeService;
import com.chuanggu.app.service.IStudentService;
import com.chuanggu.app.service.ITeacherService;
import com.chuanggu.app.service.IUserService;
import com.chuanggu.app.util.TreeNodeEx;
import com.chuanggu.app.util.Utility;
import com.chuanggu.app.util.pagehelper.PageInfo;

@Controller
@RequestMapping("/class")
public class ClassController extends PageController<Class>{

    @Autowired
    private IClassService classService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IStudentService studentService;
    
    @Autowired
    private ITeacherService teacherService;

    private User user;

    private List<Grade> listGrade;

    private PageInfo<Class> pageInfo;

	@ResponseBody
    @RequestMapping(value = "/classList")
    public String classList(Model model,
    				@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
    				@RequestParam(value = "itemsOnPage", required = false) Integer itemsOnPage) {
    	pageInfo = getRecord(pageInfo,pageNumber, itemsOnPage, classService);
    	String json = Utility.createJsonStr(pageInfo);
        return json;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "class/list";
    }

    @RequestMapping(value = "/create.html", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("class", new Class());
        model.addAttribute("op", "新增");
        model.addAttribute("permission", "class:create");
        return "class/edit";
    }

    @RequestMapping(value = "/createBatch.html", method = RequestMethod.GET)
    public String showCreateBatchForm(Model model) {
        setCommonData(model);
        model.addAttribute("class", new Class());
        model.addAttribute("op", "批量新增");
        model.addAttribute("permission", "class:createBatch");
        return "class/editBatch";
    }

    @RequestMapping(value = "/create.html", method = RequestMethod.POST)
    public String create(Class clazz, RedirectAttributes redirectAttributes) {
    	try{
    		classService.insert(clazz);
    		}catch(Exception e){
    			 redirectAttributes.addFlashAttribute("msg", "新增失败，请重新插入");
    			 return "redirect:/class";
    		}

        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/class";
    }

    @RequestMapping(value = "/createBatch.html", method = RequestMethod.POST)
    public String createBatch(Class clazz, RedirectAttributes redirectAttributes) {

        int count = Integer.parseInt(clazz.getClassOrder());
    	List<Class> list = new ArrayList<Class>();
    	for(int i = 1 ; i < count+1 ; i++){
    		Class c = new Class();
    	    c.setName(i+"班");
    		c.setCreateName(clazz.getCreateName());
    		c.setCreateTime(clazz.getCreateTime());
    		c.setGradeId(clazz.getGradeId());
    		c.setSchoolId(clazz.getSchoolId());
    		list.add(c);
    	}
    	int success = classService.insertBatch(list);
    	redirectAttributes.addFlashAttribute("msg", "批量新增"+success+"个班级成功");
        return "redirect:/class";
    }

    @RequestMapping(value = "/{id}/update.html", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	setCommonData(model);
        model.addAttribute("class", classService.findOne(id));
        model.addAttribute("permission", "class:update");
        model.addAttribute("op", "修改");
        return "class/edit";
    }


    @RequestMapping(value = "/{id}/update.html", method = RequestMethod.POST)
    public String update(@Validated Class clazz, RedirectAttributes redirectAttributes) {
    	classService.updateClass(clazz);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/class";
    }

	@RequestMapping(value = "addStudents.html")
	public String addStudents(Integer id,Model model) {
		model.addAttribute("id", id);
		return "class/addStudents";
	}

    @RequestMapping(value = "/{id}/delete.html", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Integer id, Model model) {
        setCommonData(model);
        model.addAttribute("class", classService.findOne(id));
        model.addAttribute("op", "删除");
        model.addAttribute("permission", "class:delete");
        return "class/edit";
    }


    @RequestMapping(value = "/{id}/delete.html")
    @ResponseBody
    public String delete(@PathVariable("id")Integer id, RedirectAttributes redirectAttributes) throws ServletException, IOException {
    	Map<String,Object> map=new HashMap<String,Object>();
    	Class c=classService.find(id);
    	if(c!=null){
    		if(c.getTeacherId()!=null){
    			teacherService.deleteTeacher(c.getTeacherId());
    		}
    		classService.deleteClass(id);
    		map.put("result", true);
    	} else{
    		map.put("result", false);
    	}   	
        return Utility.createJsonStr(map);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRecord(@ModelAttribute Class classList, RedirectAttributes redirectAttributes) {
    	List<Integer> idList = classList.getIdList();
        int count = classService.deleteBatchRecord(idList);
        redirectAttributes.addFlashAttribute("msg", "成功删除"+count+"个用户");
        return "redirect:/class";
    }


    private void setCommonData(Model model) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	if(user == null || listGrade == null){
    		user = userService.findByUsername(username);
        	listGrade = gradeService.findAll();
    	}
    	model.addAttribute("listGrade", listGrade);
    	model.addAttribute("schoolId",  user.getSchoolId());
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
    }


    @RequestMapping("tree")
	@ResponseBody
	public void jsonTree(String treetype,HttpServletResponse response)  throws Exception{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> mapschool = new HashMap<String, String>();
		//mapschool.put("nodeType", "school");
		mapschool.put("id", "0"+"##school");
		mapschool.put("name", "学军中学");
		mapschool.put("pId", null);
		list.add(mapschool);
		List<Grade> gradelist= gradeService.selectGradeBySchool(0l);
		Iterator<Grade> iterator = gradelist.iterator();
		Grade grade;
		Class sl;
		while (iterator.hasNext()) {
			grade = iterator.next();
			int gradeId=grade.getId();
			Map<String, String> map = new HashMap<String, String>();
			//map.put("nodeType", "grade");
			map.put("id", String.valueOf(gradeId)+"##grade");
			map.put("name", grade.getName());
			map.put("pId", String.valueOf(0)+"##school");
			list.add(map);

			Class cl=new Class();
			cl.setSchoolId(0l);
			cl.setGradeId(gradeId);
			List<Class> classlist=classService.selectClassBygrade(cl);

			Iterator<Class> classiterator = classlist.iterator();
			while (classiterator.hasNext()) {

				sl=classiterator.next();
				int classId=sl.getId();
				Map<String, String> calssmap = new HashMap<String, String>();
				//calssmap.put("nodeType", "class");
				calssmap.put("id", String.valueOf(classId)+"##class");
				calssmap.put("name",sl.getName());
				calssmap.put("pId", String.valueOf(gradeId)+"##grade");
				list.add(calssmap);

				if(treetype!=null&&treetype.equals("stu")){
					Student stu=new Student();
					stu.setClassId(classId);
					stu.setGradeId(gradeId);
					List<Student> stulist=studentService.findStuByClassIdAndGradeId(stu);
					Student student;
					Iterator<Student> stuiterator = stulist.iterator();
					while (stuiterator.hasNext()) {

						student=stuiterator.next();
						Map<String, String> stumap = new HashMap<String, String>();
						//stumap.put("nodeType", "student");
						stumap.put("id", student.getId().toString());
						stumap.put("name",student.getName());
						stumap.put("pId", String.valueOf(classId)+"##class");
						list.add(stumap);
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

    @RequestMapping("classtree")
	@ResponseBody
	public void stujsonTree(String treetype,HttpServletResponse response)  throws Exception{
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
							mapschool.put("checked", true);
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
								list.add(mapclass);

								if(stu.getStuId()!=null&&treetype!=null&&treetype.equals("stu")){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									Map<String, Object> mapstu = new HashMap<String, Object>();
									mapstu.put("id", stu.getStuId().toString()+"##student");
									mapstu.put("name", stu.getClassname());
									mapstu.put("pId", String.valueOf(classId)+"##class");
									mapstu.put("nodetype", "student");
									list.add(mapstu);

								}

							}else{

								if(stu.getStuId()!=null&&treetype!=null&&treetype.equals("stu")){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									Map<String, Object> mapstu = new HashMap<String, Object>();
									mapstu.put("id", stu.getStuId().toString()+"##student");
									mapstu.put("name", stu.getClassname());
									mapstu.put("pId", String.valueOf(classId)+"##class");
									mapstu.put("nodetype", "student");
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
								if(stu.getStuId()!=null&&treetype!=null&&treetype.equals("stu")){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									Map<String, Object> mapstu = new HashMap<String, Object>();
									mapstu.put("id", stu.getStuId().toString()+"##student");
									mapstu.put("name", stu.getClassname());
									mapstu.put("pId", String.valueOf(classId)+"##class");
									mapstu.put("nodetype", "student");
									list.add(mapstu);

								}

							}else{

								if(stu.getStuId()!=null&&treetype!=null&&treetype.equals("stu")){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									Map<String, Object> mapstu = new HashMap<String, Object>();
									mapstu.put("id", stu.getStuId().toString()+"##student");
									mapstu.put("name", stu.getClassname());
									mapstu.put("pId", String.valueOf(classId)+"##class");
									mapstu.put("nodetype", "student");
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

    @RequestMapping("nodetree")
	@ResponseBody
	public void nodeTree(String treetype,HttpServletResponse response)  throws Exception{
		//List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		//Long schoolId=100202001l;
		List<TreeNodeEx> treeNodes = new ArrayList<TreeNodeEx>();
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
							TreeNodeEx treeNode = new TreeNodeEx();
							treeNode.setName(stu.getSchname());
							treeNode.setId(schoolId+"##school");
							treeNode.setParentId(null);
							treeNode.setNodeType("school");
							treeNode.setChecked(true);
							treeNodes.add(treeNode);
						}
						if(stu.getGradeId()!=null&&stu.getGradeId()!=graId){
							graId=stu.getGradeId();
							TreeNodeEx treeNode = new TreeNodeEx();
							treeNode.setName(stu.getGraname());
							treeNode.setId(graId+"##grade");
							treeNode.setParentId(schoolId+"##school");
							treeNode.setNodeType("grade");
							treeNodes.add(treeNode);
							//int newgraId=stu.getGradeId();
							if(stu.getClassId()!=null&&stu.getClassId()!=classId){
								classId=stu.getClassId();
								//int newgraId=stu.getGradeId();
								TreeNodeEx treeNodeclass = new TreeNodeEx();
								treeNodeclass.setName(stu.getClassname());
								treeNodeclass.setId(classId+"##class");
								treeNodeclass.setParentId(String.valueOf(graId)+"##grade");
								treeNodeclass.setNodeType("class");
								treeNodes.add(treeNodeclass);
								if(stu.getStuId()!=null&&treetype!=null&&treetype.equals("stu")){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									TreeNodeEx treeNodestu = new TreeNodeEx();
									treeNodestu.setName(stu.getStuname());
									treeNodestu.setId(stu.getStuId().toString());
									treeNodestu.setParentId(String.valueOf(classId)+"##class");
									treeNodestu.setNodeType("student");
									treeNodes.add(treeNodestu);
								}

							}else{

								if(stu.getStuId()!=null&&treetype!=null&&treetype.equals("stu")){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									TreeNodeEx treeNodestu = new TreeNodeEx();
									treeNodestu.setName(stu.getStuname());
									treeNodestu.setId(stu.getStuId().toString());
									treeNodestu.setParentId(String.valueOf(classId)+"##class");
									treeNodestu.setNodeType("student");
									treeNodes.add(treeNodestu);

								}
							}

						}else{
							if(stu.getClassId()!=null&&stu.getClassId()!=classId){
								classId=stu.getClassId();
								//int newgraId=stu.getGradeId();
								TreeNodeEx treeNodeclass = new TreeNodeEx();
								treeNodeclass.setName(stu.getClassname());
								treeNodeclass.setId(classId+"##class");
								treeNodeclass.setParentId(String.valueOf(graId)+"##grade");
								treeNodeclass.setNodeType("class");
								treeNodes.add(treeNodeclass);
								if(stu.getStuId()!=null&&treetype!=null&&treetype.equals("stu")){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									TreeNodeEx treeNodestu = new TreeNodeEx();
									treeNodestu.setName(stu.getStuname());
									treeNodestu.setId(stu.getStuId().toString());
									treeNodestu.setParentId(String.valueOf(classId)+"##class");
									treeNodestu.setNodeType("student");
									treeNodes.add(treeNodestu);

								}

							}else{

								if(stu.getStuId()!=null&&treetype!=null&&treetype.equals("stu")){
									//classId=stu.getClassId();
									//int newgraId=stu.getGradeId();
									TreeNodeEx treeNodestu = new TreeNodeEx();
									treeNodestu.setName(stu.getStuname());
									treeNodestu.setId(stu.getStuId().toString());
									treeNodestu.setParentId(String.valueOf(classId)+"##class");
									treeNodestu.setNodeType("student");
									treeNodes.add(treeNodestu);

								}
							}


						}
		 }
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String nodes = com.alibaba.fastjson.JSON.toJSONString(treeNodes);
		response.getWriter().print(nodes);
		return;


  }
    @RequestMapping("deleteFindOne")
    public String deleteFindOne(Integer id){
    	classService.deleteClass(id);
    	return "class/list";
    }
    
    @RequestMapping("detail.html")
    public String detail(Integer id,Model model){
    	Class c=classService.findOne(id);
    	if(c!=null){
    		List<Student> list=studentService.findStudentByClassId(c.getId());
    		if(list.size()>0){
    			for (Student student : list) {
    				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    				student.setTime(sdf.format(student.getBirthday()));
				}
    			model.addAttribute("result", "1");
    			model.addAttribute("list", list);
    		}else{
    			model.addAttribute("result", "0");
    		}
    	}else{
    		model.addAttribute("result", "0");
    	}
    	return "class/detail";
    }
}
