package com.chuanggu.app.controller;

import java.text.SimpleDateFormat;
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

import com.chuanggu.app.constant.Constants;
import com.chuanggu.app.entity.Admin;
import com.chuanggu.app.entity.Teacher;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.service.IAdminService;
import com.chuanggu.app.service.IOrganizationService;
import com.chuanggu.app.service.IRoleService;
import com.chuanggu.app.service.IUserService;
import com.chuanggu.app.util.Utility;
import com.chuanggu.app.util.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminController extends PageController<Admin>{

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IOrganizationService organizationService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IRoleService roleService;
    
    private PageInfo<Admin> pageInfo;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "admin/list";
    }
    
    @RequestMapping("adminList")
    @ResponseBody
    public String adminList(Integer pageNumber,Integer itemsOnPage) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);   	
    	if(user.getType() == Constants.SUPERADMIN){
    		 pageInfo =  getAllowedRecord(pageInfo,pageNumber,itemsOnPage,adminService,new Long(Constants.AREAADMIN));
    	}
    		
    	if(user.getType() == Constants.AREAADMIN){
    		pageInfo =getAllowedRecord(pageInfo,pageNumber,itemsOnPage,adminService,user.getSchoolId());
    	}
        return Utility.createJsonStr(pageInfo);
    }
    
    @RequestMapping("listAll")
    @ResponseBody
    public String listAll(Integer pageNumber,Integer itemsOnPage) {
    	pageInfo = getRecord(pageInfo,pageNumber,itemsOnPage,adminService);
        return Utility.createJsonStr(pageInfo);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("user", new Admin());
        model.addAttribute("op", "新增");
        model.addAttribute("permission", "admin:creates");
        return "admin/edit";
    }

    @RequestMapping(value = "/creates", method = RequestMethod.POST)
    public String create(Admin user, RedirectAttributes redirectAttributes) {
    	adminService.createAdmin(user);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/admin";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", adminService.findOne(id));
        model.addAttribute("permission", "admin:update");
        model.addAttribute("op", "修改");
        return "admin/edit";
    }
    

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@Validated Admin user, RedirectAttributes redirectAttributes) {
    	adminService.updateAdmin(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/admin";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("user", adminService.findOne(id));
        model.addAttribute("op", "删除");
        model.addAttribute("permission", "admin:delete");
        return "admin/edit";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    	adminService.deleteAdmin(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRecord(@ModelAttribute User userList, RedirectAttributes redirectAttributes) {
    	List<Long> idList = userList.getIdList();
        int count = adminService.deleteBatchRecord(idList);
        redirectAttributes.addFlashAttribute("msg", "成功删除"+count+"个用户");
        return "redirect:/admin";
    }

    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", adminService.findOne(id));
        model.addAttribute("op", "修改密码");
        return "admin/changePassword";
    }
    
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
        adminService.changePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/admin";
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
    @RequestMapping("detail.html")
    public String detail(Integer id,Model model) {
    	Admin admin=adminService.findOne((long)id);
    	model.addAttribute("admin", admin);
        return "admin/detail";
    }
}
