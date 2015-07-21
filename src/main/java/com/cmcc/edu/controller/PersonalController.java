package com.cmcc.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cmcc.edu.constant.Constants;
import com.cmcc.edu.entity.Admin;
import com.cmcc.edu.entity.Image;
import com.cmcc.edu.entity.Teacher;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.service.IAdminService;
import com.cmcc.edu.service.ITeacherService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;

@Controller
@RequestMapping("/personal")
public class PersonalController{
	@Autowired
    private ITeacherService teacherService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IAdminService adminService;
    
    @Autowired
    private CredentialsMatcher matcher;
	
	
    @RequestMapping
    public String list() {
        return "personal/info";
    }
    
    @RequestMapping("info")
    public String info(Model model) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	
    	if(user.getType()==Constants.TEACHER){
    		Teacher tea=teacherService.findOne(user.getId());
    		model.addAttribute("user", tea);
    		 return "personal/info";
    	}else{
    		Admin tea=adminService.findOne(user.getId());
    		model.addAttribute("user", tea);
    		 return "personal/admininfo";
    	}
       
    	//return null;
    }
    
    @RequestMapping("saveinfo")
    @ResponseBody
    public String saveinfo(@ModelAttribute Teacher teacher) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	Map<String,Object> map=new HashMap<String,Object>();
    	//int type=user.getType();
//    	if(type==Constants.TEACHER){
    		teacherService.updateTeacherByPersonal(teacher);
//    	}else{
    		
    		//adminService.updateAdminByPersonal(user);
    	//}
    	map.put("result", "修改成功");
        return  Utility.createJsonStr(map) ;
    }
    
    @RequestMapping("saveadmininfo")
    @ResponseBody
    public String saveadmininfo(@ModelAttribute Admin admin) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	Map<String,Object> map=new HashMap<String,Object>();
    	//int type=user.getType();
    		
    		adminService.updateAdminByPersonal(admin);
    	map.put("result", "修改成功");
        return  Utility.createJsonStr(map) ;
    }
    
    @RequestMapping("editPassword")
    public String edit() {
        return "personal/editPassword";
    }
    
    @RequestMapping("checkPassword")
    @ResponseBody
    public String checkPassword(String password) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	Map<String,Object> map=new HashMap<String,Object>();
    	
    	SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//lt=username+salt
                "com.cmcc.edu.realm.UserRealm_0"  //realm name
        );
       UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), password);
       boolean success = matcher.doCredentialsMatch(token, authenticationInfo);
    	//String pa=com.cmcc.edu.util.EncryptHelper.toMD5(password.trim());
    	if(success){
    		map.put("result", "密码正确");
    	}else{
    		map.put("result", "密码错误");
    	}
        return  Utility.createJsonStr(map) ;
    }
    
    @RequestMapping("savePassword")
    @ResponseBody
    public String savePassword(String password) {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	Map<String,Object> map=new HashMap<String,Object>();
    	
    	int type=user.getType();
    	
    	if(type==Constants.TEACHER){
    		teacherService.changePassword(user.getId(), password);
    	}else{
    		adminService.changePassword(user.getId(), password);
    		
    	}
    	map.put("result", "修改成功");
        return  Utility.createJsonStr(map) ;
    }
    @RequestMapping("portrait")
    public String portrait() {
        return "personal/portrait";
    }
    
    @RequestMapping("upload")
    @ResponseBody
    public String headPhoto(HttpServletRequest request,@RequestParam MultipartFile myfile,@ModelAttribute Image image) throws IOException {
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
    	String realPath = multipartRequest.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
        //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
    	 FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));
    	 return realPath;
    }
//    @RequestMapping("upload")
//    @ResponseBody
//    public String headPhoto(MultipartHttpServletRequest multipartRequest,@RequestParam MultipartFile[] myfile,@ModelAttribute Image image) {
//    	String username = (String)SecurityUtils.getSubject().getPrincipal();
//    	File myfile = new File(image.getUrl());
//    	String srcpath = myfile.getAbsolutePath()+"\\"+myfile.getName();
//    	String subpath = myfile.getAbsolutePath()+"\\"+ username+"_"+System.currentTimeMillis();
//    	ImageCut imageCut = new ImageCut(image,srcpath,subpath);
//    	imageCut.cut();
//    	return subpath;
// 
//    }
}
