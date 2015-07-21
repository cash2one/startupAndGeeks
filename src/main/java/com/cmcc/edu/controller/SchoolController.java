package com.cmcc.edu.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.edu.entity.Area;
import com.cmcc.edu.entity.Notice;
import com.cmcc.edu.entity.Noticeperson;
import com.cmcc.edu.entity.School;
import com.cmcc.edu.entity.Student;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.entity.UserGroup;
import com.cmcc.edu.service.IAreaService;
import com.cmcc.edu.service.ISchoolService;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;


@Controller
@RequestMapping("/school")
public class SchoolController extends PageController<School>{

	
	@Autowired
    private ISchoolService schoolService;
	
	@Autowired
    private IAreaService areaService;
	
	
	private PageInfo<School> pageInfo;
	
	
	@Autowired
    private IUserService userService;
	
	/**
     * 学校查询
     * @param 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "school/schoolList";
    }
    
    /**
     * 学校查询Json
     * @param 
     * @return
     */
    @RequestMapping("/schoolJson")
    @ResponseBody
    public String schoolJson(School school,Integer pageNumber ,Integer itemsOnPage) {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm ");
    	//String username = (String)SecurityUtils.getSubject().getPrincipal();
    	//User user = userService.findByUsername(username);
    	//student.setSchoolId(user.getSchoolId());
    	 pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, schoolService,school);
    	 if(pageInfo.getList().size()>0){
	     		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	     		for (School c : pageInfo.getList()) {
	     			if(c.getUpdateTime()!=null){
	     				c.setTime(sdf.format(c.getUpdateTime()));
	     			}
	     			
	 			}
	     	}
     	String retru = Utility.createJsonStr(pageInfo);
 		return retru;
    }
    
    /**
     * 省份查询Json
     * @param 
     * @return
     */
    @RequestMapping("/provinceJson")
    @ResponseBody
    public String provinceJson() {
    	List<Area> arealist=areaService.findProvince();
    	String nodes = com.alibaba.fastjson.JSON.toJSONString(arealist);
 		return nodes;
    }
    
    /**
     * 城市查询Json
     * @param 
     * @return
     */
    @RequestMapping("/cityJson")
    @ResponseBody
    public String cityJson(Integer provinceCode ) {
    	List<Area> arealist=areaService.getCitysByProvinceId(provinceCode);
    	String nodes = com.alibaba.fastjson.JSON.toJSONString(arealist);
 		return nodes;
    }
    
    /**
     * 城市查询Json
     * @param 
     * @return
     */
    @RequestMapping("/areaJson")
    @ResponseBody
    public String areaJson(Integer cityCode ) {
    	List<Area> arealist=areaService.getAreasByCityId(cityCode);
    	String nodes = com.alibaba.fastjson.JSON.toJSONString(arealist);
 		return nodes;
    }
    
    
    @RequestMapping(value = "/save")
    @ResponseBody
    public String create(@ModelAttribute School school,String stime,String etime) throws IOException, ParseException{
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
        school.setUpdatename(user.getUsername());
        school.setUpdateTime(new Date());
    	Map<String,Object> map=new HashMap<String,Object>();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(school.getState().equals("0")){
    		school.setStarttime(sdf.parse(stime));
    		school.setEndtime(sdf.parse(etime));
    	}
    	if(school.getId()!=null){
    		schoolService.updateSchool(school);
    		map.put("result", "修改成功");
    	}else{
    		Integer maxId=schoolService.findMaxSchoolId(school.getAreaCode());
    		String id="";
    		if(maxId!=null){
    			maxId+=1;
    			id=String.valueOf(maxId);
            	if(id.length()<2){
            		id="00"+id;
            	}else if(id.length()<3&&id.length()>1){
            		id="0"+id;
            	}
    		}else{
    			id="001";
    		}
        	
            Long schoolId=Long.parseLong(school.getAreaCode()+id);
            school.setId(schoolId);
    		schoolService.insert(school);
        	map.put("result", "添加成功");
    		
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    
    /**
     * 批量删除草稿箱
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public String batchDelete(String ids) {
    	int num=0;
    	Map<String,Object> map=new HashMap<String,Object>();
    	if(ids!=null && !"".equals(ids)){
    		String[] id=ids.split(",");
    		num=id.length;
    		for (String s : id) {
    			schoolService.deleteSchool(Long.parseLong(s));
    			//scoreService.deleteByexamId(Long.parseLong(s));
			}
    		map.put("result", "删除成功");
    		map.put("num", num);
    	}else{
    		map.put("result", "请勾择删除项");
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    
    /**
     * 跳转到添加页面
     * @param 
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String receiveNotice(Model model) {
        return "school/schoolEdit";
    }
    
    /**
     * 跳到修改草稿页面
     * @param 
     * @return
     */
    @RequestMapping(value = "/updateschool.html", method = RequestMethod.GET)
    public String updateschool( Long id, Model model,String isAdd) {
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	School school=schoolService.findOne(id);
    	if(school!=null && school.getStarttime()!=null){
    		model.addAttribute("begtimeTemp", sdf.format(school.getStarttime()));
		}
    	if(school!=null && school.getEndtime()!=null){
    		model.addAttribute("endtimeTemp", sdf.format(school.getEndtime()));
		}
    	model.addAttribute("school", school);
    	//model.addAttribute("personlist", noticepersonService.noticePersonList(id));
    	
        return  "school/schoolEdit";
    }
    
    /**
     * 详细查询
     * @param 
     * @return
     */
    @RequestMapping(value ="/detail.html")
    public String detail( Long id,Model model) {
    	School school=schoolService.findDetailSchoolById(id);
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	if(school!=null && school.getStarttime()!=null){
    		model.addAttribute("begtimeTemp", sdf.format(school.getStarttime()));
		}
    	if(school!=null && school.getEndtime()!=null){
    		model.addAttribute("endtimeTemp", sdf.format(school.getEndtime()));
		}
    	model.addAttribute("school", school);
        return "school/detail";
    }
    /**
     * 删除
     */
    @RequestMapping(value ="/delete.html")
    @ResponseBody
    public String delete(Integer id){
    	Map<String,Object> map=new HashMap<String,Object>();
    	schoolService.deleteSchool((long)id);
    	map.put("result", true);
    	return Utility.createJsonStr(map);
    	
    }
}
