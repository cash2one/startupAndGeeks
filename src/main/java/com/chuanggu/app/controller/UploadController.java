package com.chuanggu.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chuanggu.app.constant.Constant;
import com.chuanggu.app.dto.ResponseMessage;
import com.chuanggu.app.dto.UploadFileAndPicture;
import com.chuanggu.app.util.FileWriteUtil;


@RestController
@RequestMapping(value = "/upload")
public class UploadController {
	
	@RequestMapping(value="/document", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage uploadFileToServer(HttpSession session,@RequestBody UploadFileAndPicture uploadFile){
		ResponseMessage responseMessage = new ResponseMessage();
//		System.out.println("appid===>" + appid);
//		StartMessageDto startMessageDto = startMessgeService.getStartMessage(appid);
//		responseMessage.setCode(HttpStatus.OK.value());
//		responseMessage.setMessage("");
//		responseMessage.setData(startMessageDto);
		
		if (uploadFile.getFile() == null) {
			responseMessage.setCode(HttpStatus.NO_CONTENT.value());
			return responseMessage;
		}
		// 判断文件类型.
		String extName = Constant.IMG_FILE_CONTENT_TYPE.get("" + uploadFile.getFile().getContentType());
		if (extName == null) {
			responseMessage.setCode(HttpStatus.NO_CONTENT.value());
			return responseMessage;
		}
		// 判断文件加大小
//		if (param.getFile().getSize() >= Config.getAsLong(Constant.UPLOAD_SHOTPIC_FILE_SIZE)) {
//			return this.getPageView("member/app/uploadshotpic").addObject(Constant.KCODE, -2);
//		}
		// 放到tmp文件夹
		String rslt = FileWriteUtil.writetTmpFile(uploadFile.getFile(), session, Constant.FILE_APP_DOC_PATH, extName);
		if ("-".equals(rslt.charAt(0) + "")) {
			responseMessage.setCode(HttpStatus.NO_CONTENT.value());
			return responseMessage;
		}
		
		return responseMessage;
	}
	
	@RequestMapping(value="/picture", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage uploadPicToServer(HttpSession session,@RequestBody UploadFileAndPicture uploadFile){
		ResponseMessage responseMessage = new ResponseMessage();
//		System.out.println("appid===>" + appid);
//		StartMessageDto startMessageDto = startMessgeService.getStartMessage(appid);
//		responseMessage.setCode(HttpStatus.OK.value());
//		responseMessage.setMessage("");
//		responseMessage.setData(startMessageDto);
		
		if (uploadFile.getFile() == null) {
			responseMessage.setCode(HttpStatus.NO_CONTENT.value());
			return responseMessage;
		}
		// 判断文件类型.
		String extName = Constant.IMG_FILE_CONTENT_TYPE.get("" + uploadFile.getFile().getContentType());
		if (extName == null) {
			responseMessage.setCode(HttpStatus.NO_CONTENT.value());
			return responseMessage;
		}
		// 判断文件加大小
//		if (param.getFile().getSize() >= Config.getAsLong(Constant.UPLOAD_SHOTPIC_FILE_SIZE)) {
//			return this.getPageView("member/app/uploadshotpic").addObject(Constant.KCODE, -2);
//		}
		// 放到tmp文件夹
		String rslt = FileWriteUtil.writetTmpFile(uploadFile.getFile(), session, Constant.FILE_APP_IMG_PATH, extName);
		if ("-".equals(rslt.charAt(0) + "")) {
			responseMessage.setCode(HttpStatus.NO_CONTENT.value());
			return responseMessage;
		}
		
		return responseMessage;
	}
}
