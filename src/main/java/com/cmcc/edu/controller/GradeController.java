package com.cmcc.edu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cmcc.edu.entity.Grade;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.entity.Class;
import com.cmcc.edu.service.IClassService;
import com.cmcc.edu.service.IGradeService;
import com.cmcc.edu.service.ITeacherService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;

@Controller
@RequestMapping("/grade")
public class GradeController extends PageController<Grade>{

    @Autowired
    private IGradeService gradeService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IClassService classService;
    
    @Autowired
    private ITeacherService teacherService;
    
    private PageInfo<Grade> pageInfo;
    
    private String[] gradeChange ={"一","二","三","四","五","六","七","八","九"};

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "grade/list";
    }
    @RequestMapping("gradeList")
    @ResponseBody
    public String gradeList(Integer pageNumber,Integer itemsOnPage){
    	pageInfo = getRecord(pageInfo,pageNumber, itemsOnPage, gradeService);
    	return Utility.createJsonStr(pageInfo);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);    	
        model.addAttribute("grade", new Grade());
        model.addAttribute("op", "新增");
        model.addAttribute("permission", "grade:create");
        return "grade/edit";
    }
    
    @RequestMapping(value = "/createBatch", method = RequestMethod.GET)
    public String showCreateBatchForm(Model model) {
        setCommonData(model);    	
        model.addAttribute("grade", new Grade());
        model.addAttribute("op", "批量新增");
        model.addAttribute("permission", "grade:createBatch");
        return "grade/editBatch";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Grade grade, RedirectAttributes redirectAttributes) {
    	gradeService.insert(grade);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/grade";
    }
    
    @RequestMapping(value = "/createBatch", method = RequestMethod.POST)
    public String createBatch(Grade grade, RedirectAttributes redirectAttributes) {

        int count = Integer.parseInt(grade.getGradeOrder());
    	List<Grade> list = new ArrayList<Grade>();
    	for(int i = 0 ; i < count ; i++){  		
    		Grade g = new Grade();
    		if(i > 8 )
    			 g.setName("高中"+gradeChange[i%9]+"年级");
    		else g.setName(gradeChange[i]+"年级");
    		g.setCreateName(grade.getCreateName());
    		g.setCreateTime(grade.getCreateTime());
    		g.setSchoolId(grade.getSchoolId());
    		list.add(g);   		
    	}
    	int success = gradeService.insertBatch(list);
    	if(success < count)
    		redirectAttributes.addFlashAttribute("msg", "成功添加"+success+"个年级，其他未成功添加的原因可能是已经存在改年级或者插入失败");
    	else redirectAttributes.addFlashAttribute("msg", "批量新增成功");
        return "redirect:/grade";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("grade", gradeService.findOne(id));
        model.addAttribute("permission", "grade:update");
        model.addAttribute("op", "修改");
        return "grade/edit";
    }
    

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@Validated Grade grade, RedirectAttributes redirectAttributes) {
    	gradeService.updateGrade(grade);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/grade";
    }



    @RequestMapping(value = "/{id}/delete")
    @ResponseBody
    public String delete(@PathVariable("id")Integer id) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	Grade g=gradeService.findOne(id);
    	List<Class> clList=classService.classList(new Class());
    	if(g!=null){
    		for (Class c : clList) {
				if(c!=null && c.getGradeId()==g.getId()){
					if(c.getTeacherId()!=null){
						teacherService.deleteTeacher(c.getTeacherId());
					}
					classService.deleteClass(c.getId());
				}
			}
    		gradeService.deleteGrade(id);
    		map.put("result", true);
    	}else{
    		map.put("result", false);
    	}   	
        return Utility.createJsonStr(map);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRecord(@ModelAttribute Grade gradeList, RedirectAttributes redirectAttributes) {
    	List<Integer> idList = gradeList.getIdList();
        int count = gradeService.deleteBatchRecord(idList);
        redirectAttributes.addFlashAttribute("msg", "成功删除"+count+"个用户");
        return "redirect:/grade";
    }

 
    private void setCommonData(Model model) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	model.addAttribute("schoolId",  user.getSchoolId());
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
    }
}
