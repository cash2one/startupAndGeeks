package com.chuanggu.app.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chuanggu.app.entity.Resource;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.service.IResourceService;
import com.chuanggu.app.service.IUserService;
import com.chuanggu.app.util.MultipleTree;
import com.chuanggu.app.web.bind.CurrentUser;

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
