package com.cmcc.edu.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmcc.edu.entity.Resource;
import com.cmcc.edu.entity.Role;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IResourceService;
import com.cmcc.edu.service.IRoleService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.spring.SpringUtils;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;


@Controller
@RequestMapping("/role")
public class RoleController extends PageController<Role>{

    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private IUserService userService;

    @Autowired
    private IResourceService resourceService;
    
    private PageInfo<Role> pageInfo;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "role/list";
    }
    @RequestMapping("roleList")
    @ResponseBody
    public String list(Integer pageNumber,Integer itemsOnPage) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	pageInfo = getAllowedRecord(pageInfo,pageNumber,itemsOnPage,roleService,new Long(user.getType()));
        return Utility.createJsonStr(pageInfo);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("role", new Role());
        model.addAttribute("op", "新增");
        model.addAttribute("permission", "role:create");
        return "role/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Role role, RedirectAttributes redirectAttributes) {
        roleService.createRole(role);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/role";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(id));
        model.addAttribute("op", "修改");
        model.addAttribute("permission", "role:update");
        return "role/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes) {
        roleService.updateRole(role);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/role";
    }


    @RequestMapping(value = "/{id}/delete")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
    	Map<String,Object> map=new HashMap<String,Object>();
        roleService.deleteRole(id);
        map.put("result", true);
        return Utility.createJsonStr(map);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRecord(@ModelAttribute Role roleList, RedirectAttributes redirectAttributes) {
    	List<Long> idList = roleList.getIdList();
        int count = roleService.deleteBatchRecord(idList);
        redirectAttributes.addFlashAttribute("msg", "成功删除"+count+"个角色");
        return "redirect:/role";
    }
    private void setCommonData(Model model) {
    	String userName = (String)SecurityUtils.getSubject().getPrincipal();
        Set<String> permissions = userService.findPermissions(userName);
        List<Resource> resourceList = resourceService.findAllowed(permissions);
        model.addAttribute("resourceList",resourceList);
    }
    @RequestMapping("resourceNames")
    @ResponseBody
    public  String resourceNames(String resourceIds) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String str="";
    	if(resourceIds!=null && !"".equals(resourceIds)){
    		String[] id=resourceIds.split(",");
    		for (String string : id) {
    			 Resource resource = getResourceService().findOne((long)Integer.parseInt(string));
    			 if(resource.getName()!=null && !"".equals(resource.getName())){
    				 str+=resource.getName()+",";
    			 }		 
			}
    		if(str.length()>0){
    			str=str.substring(0, str.length()-1);
    		}
    	}
    	map.put("name", str);
        return Utility.createJsonStr(map);
    }
    public  IResourceService getResourceService() {
        if(resourceService == null) {
            resourceService = SpringUtils.getBean(IResourceService.class);
        }
        return resourceService;
    }

}
