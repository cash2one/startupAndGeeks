package com.chuanggu.app.controller.user;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chuanggu.app.dto.RegisterUser;
import com.chuanggu.app.dto.ResponseMessage;
import com.chuanggu.app.exception.ServiceException;
import com.chuanggu.app.service.UserService;

@RestController
@RequestMapping("/user")
public class RegisterController {
	
	@Resource(shareable = true)
	private UserService userService;
	/**
	 * 注册新用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register")
	public ResponseMessage add(@Valid @ModelAttribute("user") RegisterUser user) {
		ResponseMessage respMesg=new ResponseMessage();
		try {
//			 json=cus.put(record);
			 respMesg.setCode(HttpStatus.OK.value());
		} catch (Exception e) {
			respMesg.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return respMesg;
	}
	
	/**
	 * Ajax请求校验loginName是否唯一。
	 * @throws ServiceException 
	 * @throws com.chuanggu.app.exception.ServiceException 
	 */
	@RequestMapping(value = "/checkLoginName",method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage checkLoginName(@RequestParam("loginName") String loginName) throws ServiceException {
		ResponseMessage respMesg=new ResponseMessage();
		try {
			if (userService.findUserByLoginName(loginName) == null) {
				respMesg.setCode(HttpStatus.OK.value());
			} else {
				respMesg.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			}
		} catch (com.chuanggu.app.exception.ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respMesg;
	}
	
	/**
	 * Ajax请求校验email是否唯一。
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/checkEmail",method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage checkEmail(@RequestParam("email") String email) throws ServiceException {
		ResponseMessage respMesg=new ResponseMessage();
		try {
			if (userService.findUserByEmail(email) == null) {
				respMesg.setCode(HttpStatus.OK.value());
			} else {
				respMesg.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			}
		} catch (com.chuanggu.app.exception.ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respMesg;
	}
}
