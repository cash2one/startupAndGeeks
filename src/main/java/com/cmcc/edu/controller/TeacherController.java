package com.cmcc.edu.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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

import com.cmcc.edu.constant.Constants;
import com.cmcc.edu.entity.Organization;
import com.cmcc.edu.entity.Role;
import com.cmcc.edu.entity.Teacher;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IOrganizationService;
import com.cmcc.edu.service.IRoleService;
import com.cmcc.edu.service.ITeacherService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.spring.SpringUtils;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends PageController<Teacher>{

    @Autowired
    private ITeacherService teacherService;
    
    @Autowired
    private IUserService userService;

    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IRoleService roleService;
    

    
    private PageInfo<Teacher> pageInfo;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
    	 return "teacher/list";
    }
    
    @RequestMapping("teacherList")
    @ResponseBody
    public String teacherList(Integer pageNumber, Integer itemsOnPage) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	if(user.getType() == Constants.SUPERADMIN){
    		pageInfo = getRecord(pageInfo,pageNumber, itemsOnPage, teacherService);
    	}
    	else if (user.getType() == Constants.AREAADMIN){
    		pageInfo = getAllowedRecord(pageInfo,pageNumber,itemsOnPage,teacherService,user.getSchoolId());
    	}
    	else if (user.getType() == Constants.SCHOOLADMIN){
    		pageInfo = getAllowedRecord(pageInfo,pageNumber,itemsOnPage,teacherService,user.getSchoolId());
    	}
    	String retru = Utility.createJsonStr(pageInfo);
		return retru;
    }
    

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("user", new Teacher());
        model.addAttribute("op", "新增");
        model.addAttribute("permission", "teacher:create");
        return "teacher/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Teacher user, RedirectAttributes redirectAttributes) {
    	teacherService.createTeacher(user);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/teacher";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", teacherService.findOne(id));
        model.addAttribute("permission", "teacher:update");
        model.addAttribute("op", "修改");
        return "teacher/edit";
    }
    

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@Validated Teacher user, RedirectAttributes redirectAttributes) {
    	teacherService.updateTeacher(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/teacher";
    }



    @RequestMapping(value = "/{id}/delete")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	teacherService.deleteTeacher(id);
    	map.put("result", true);
        return Utility.createJsonStr(map);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRecord(@ModelAttribute User userList, RedirectAttributes redirectAttributes) {
    	List<Long> idList = userList.getIdList();
        int count = teacherService.deleteBatchRecord(idList);
        redirectAttributes.addFlashAttribute("msg", "成功删除"+count+"个用户");
        return "redirect:/teacher";
    }

    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("teacher", teacherService.findOne(id));
        model.addAttribute("op", "修改密码");
        return "teacher/changePassword";
    }

    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
    	teacherService.changePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/teacher";
    }


    private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
    }
    @RequestMapping("roleNames")
    @ResponseBody
    public String roleNames(String roleIds) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String result="";
    	if(roleIds!=null && !"".equals(roleIds)){
    		String[] roleid=roleIds.split(",");
    		for (String id : roleid) {
    			Role role = getRoleService().findOne((long)Integer.parseInt(id));
    			if(role!=null){
    				result+=role.getRole()+",";	
    			}		
			}
    		if(result.length()>0){
    			result=result.substring(0, result.length()-1);
    		}
    	}
         map.put("s", result);
         return Utility.createJsonStr(map);
    }
    public  IRoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtils.getBean(IRoleService.class);
        }
        return roleService;
    }
    @RequestMapping("organizationName")
    @ResponseBody
    public String organizationName(String organizationId) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	Organization organization = getOrganizationService().findOne((long)Integer.parseInt(organizationId));
        if(organization == null) {
        	 map.put("name", "");
        }else{
        	map.put("name", organization.getName());
        }
        return Utility.createJsonStr(map);
    }
    public  IOrganizationService getOrganizationService() {
        if(organizationService == null) {
            organizationService = SpringUtils.getBean(IOrganizationService.class);
        }
        return organizationService;
    }
    
    @RequestMapping("detail.html")
    public String detail(Integer id,Model model) {
    	Teacher teacher=teacherService.findOne((long)id);
    	model.addAttribute("teacher", teacher);
        return "teacher/detail";
    }

}
