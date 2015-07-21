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
import com.cmcc.edu.entity.Supply;
import com.cmcc.edu.entity.SupplyType;
import com.cmcc.edu.entity.User;
import com.cmcc.edu.entity.UserGroup;
import com.cmcc.edu.service.IAreaService;
import com.cmcc.edu.service.ISchoolService;
import com.cmcc.edu.service.IStudentService;
import com.cmcc.edu.service.ISupplyService;
import com.cmcc.edu.service.ISupplyTypeService;
import com.cmcc.edu.service.IUserService;
import com.cmcc.edu.util.Utility;
import com.cmcc.edu.util.pagehelper.PageInfo;


@Controller
@RequestMapping("/supply")
public class SupplyController extends PageController<Supply>{

	
	@Autowired
    private ISupplyService supplyService;
	
	@Autowired
    private IAreaService areaService;
	
	
	private PageInfo<Supply> pageInfo;
	
	
	@Autowired
    private IUserService userService;
	
	@Autowired
    private ISupplyTypeService supplytypeService;
	
	/**
     * 学校查询
     * @param 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "supply/supplyList";
    }
    
    /**
     * 学校查询Json
     * @param 
     * @return
     */
    @RequestMapping("/supplyJson")
    @ResponseBody
    public String schoolJson(Supply supply,Integer pageNumber ,Integer itemsOnPage) {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm ");
    	//String username = (String)SecurityUtils.getSubject().getPrincipal();
    	//User user = userService.findByUsername(username);
    	//student.setSchoolId(user.getSchoolId());
    	 pageInfo=this.getByObject(pageInfo, pageNumber, itemsOnPage, supplyService,supply);
    	 if(pageInfo.getList().size()>0){
	     		//SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
	     		for (Supply c : pageInfo.getList()) {
	     			if(c.getUpdateTime()!=null){
	     				c.setTime(sdf.format(c.getUpdateTime()));
	     			}
	     			
	 			}
	     }
     	String retru = Utility.createJsonStr(pageInfo);
 		return retru;
    }
    
    
   
    
    
    
    /**
     * 跳转到添加页面
     * @param 
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String addSupply(Model model) {
        return "supply/supplyEdit";
    }
    
    /**
     * 城市查询Json
     * @param 
     * @return
     */
    @RequestMapping("/parentSupply")
    @ResponseBody
    public String parentSupply(Long type) {
    	Supply supply=new Supply();
    	supply.setSupplytype(type);
    	List<Supply> supplylist=supplyService.findParentSupply(supply);
    	String nodes = com.alibaba.fastjson.JSON.toJSONString(supplylist);
 		return nodes;
    }
    
    /**
     * 机构类型Json
     * @param 
     * @return
     */
    @RequestMapping("/supplytype")
    @ResponseBody
    public String supplytype(Integer type) {
    	List<SupplyType> supplytypelist=supplytypeService.findTypeBySupply(type);
    	String nodes = com.alibaba.fastjson.JSON.toJSONString(supplytypelist);
 		return nodes;
    }
    
    
    @RequestMapping(value = "/save")
    @ResponseBody
    public String create(@ModelAttribute Supply supply) throws IOException, ParseException{
    	String username = (String)SecurityUtils.getSubject().getPrincipal();
    	User user = userService.findByUsername(username);
    	supply.setUpdatename(user.getUsername());
    	supply.setUpdateTime(new Date());
    	Map<String,Object> map=new HashMap<String,Object>();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(supply.getId()!=null){
    		supplyService.updateSupply(supply);
    		map.put("result", "修改成功");
    	}else{
    		
            //school.setId(schoolId);
    		supplyService.insert(supply);
        	map.put("result", "添加成功");
    		
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    
    /**
     * 跳到修改机构
     * @param 
     * @return
     */
    @RequestMapping(value = "/updatesupply.html", method = RequestMethod.GET)
    public String updateschool( Long id, Model model,String isAdd) {
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Supply supply=supplyService.findOne(id);
    	
    	Supply parentsupply=supplyService.findOne(supply.getParentId());
    	model.addAttribute("supply", supply);
    	if(parentsupply.getType()!=null){
    		model.addAttribute("parentType", parentsupply.getType());
    	}else{
    		model.addAttribute("parentType", 0);
    	}
    	
    	//model.addAttribute("personlist", noticepersonService.noticePersonList(id));
    	
        return  "supply/supplyEdit";
    }
    
    /**
     * 批量删除草机构
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
    			supplyService.deleteSupply(Long.parseLong(s));
    			//scoreService.deleteByexamId(Long.parseLong(s));
			}
    		map.put("result", "删除成功");
    		map.put("num", num);
    	}else{
    		map.put("result", "请勾择删除项");
    	}
    	
    	return Utility.createJsonStr(map) ;
    }
    
}
