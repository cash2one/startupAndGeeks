package com.cmcc.edu.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.edu.entity.Resource;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IResourceService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.MultipleTree;
import com.cmcc.edu.web.bind.CurrentUser;

@Controller
public class IndexController {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, HttpServletResponse response) {
    	return "index/index";  	
    }
    
    @RequestMapping("/index")
    public String home() {
    	return "index/index";  	
    }
    @RequestMapping("/index/dynamic")
    public String dynamic() {
    	return "index/dynamic";  	
    }
    @RequestMapping("/index/faculty")
    public String faculty() {
    	return "index/faculty";  	
    }
    
    @RequestMapping(value = "/menu" , produces = "text/plain;charset=UTF-8")
    @ResponseBody  
    public String menu(@CurrentUser User loginUser) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        String jsonTree = MultipleTree.getTree(menus);
        return jsonTree;
    }

}
