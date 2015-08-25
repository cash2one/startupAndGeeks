package com.chuanggu.app.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chuanggu.app.dto.ResponseMessage;
import com.chuanggu.app.entity.ProjectWithBLOBs;
import com.chuanggu.app.service.UserService;
import com.chuanggu.app.util.ResponseMsgUtil;


@RestController
@RequestMapping("/user")
public class UserController {

	@Resource(shareable = true)
	private UserService us;
	
	@RequestMapping(value="/smart/list", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage getSmartList(@Valid @RequestBody ProjectWithBLOBs project){
		Map<String,String> param= new HashMap<String,String>();
		us.getSmartList(param);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	@RequestMapping(value="/smart/detail", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage getSmartDetail(@Valid @RequestBody ProjectWithBLOBs project){
        String id=null;
		us.getSmartDetail(id);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	@RequestMapping(value="/maker/list", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage getMakerList(@Valid @RequestBody ProjectWithBLOBs project){
		Map<String,String> param= new HashMap<String,String>();
		us.getMakerList(param);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	@RequestMapping(value="/maker/detail", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage getMakerDetail(@Valid @RequestBody ProjectWithBLOBs project){
		String id=null;
		us.getMakerDetail(id);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	@RequestMapping(value="/investor/list", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage getInvestorList(@Valid @RequestBody ProjectWithBLOBs project){
		Map<String,String> param= new HashMap<String,String>();
		us.getInvestorList(param);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
	
	@RequestMapping(value="/investor/detail", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage getInvestorDetail(@Valid @RequestBody ProjectWithBLOBs project){
		String id=null;
		us.getInvestorDetail(id);
		return ResponseMsgUtil.getDefaultSuccessMsg();
	}
}
