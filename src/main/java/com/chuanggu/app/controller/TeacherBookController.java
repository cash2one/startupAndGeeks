package com.chuanggu.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.chuanggu.app.entity.Student;
import com.chuanggu.app.entity.Teacher;
import com.chuanggu.app.entity.User;
import com.chuanggu.app.service.IOrganizationService;
import com.chuanggu.app.service.IRoleService;
import com.chuanggu.app.service.ITeacherService;
import com.chuanggu.app.service.IUserService;
import com.chuanggu.app.util.Utility;
import com.chuanggu.app.util.pagehelper.PageInfo;

@Controller
@RequestMapping("/phonebook/teacher")
public class TeacherBookController extends PageController<Teacher>{

    @Autowired
    private ITeacherService teacherService;
    
    @Autowired
    private IUserService userService;

    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IRoleService roleService;
    
    private PageInfo<Teacher> pageInfo;

    /**
     * 通讯录学生查询
     * @param 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "phonebook/teacher";
    }

    /**
     * 教师查询Json
     * @param 
     * @return
     */
    @RequestMapping("/teacherJson")
    @ResponseBody
    public String teacherJson(Teacher teacher,Integer pageNumber ,Integer itemsOnPage) {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	teacher.setSchoolId(user.getSchoolId());
    	pageInfo = getByObject(pageInfo,pageNumber,itemsOnPage,teacherService,teacher);
//    	 if(pageInfo.getList().size()>0){
//	     		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
//	     		for (Teacher c : pageInfo.getList()) {
//	     			if(c.getBirthday()!=null&&!c.getBirthday().trim().equals("")){
//	     				c.setTime(sdf.format(c.getBirthday()));
//	     			}
//	     			
//	 			}
//	     	}
     	String retru = Utility.createJsonStr(pageInfo);
 		return retru;
    }
  
}
