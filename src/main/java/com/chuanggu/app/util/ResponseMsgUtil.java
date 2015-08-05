package com.chuanggu.app.util;

import org.springframework.http.HttpStatus;

import com.chuanggu.app.dto.ResponseMessage;

public class ResponseMsgUtil {
	public static ResponseMessage getResponseMsg(Object o){
		ResponseMessage responseMsg = new ResponseMessage();
		responseMsg.setCode(HttpStatus.OK.value());
		responseMsg.setMessage(HttpStatus.OK.getReasonPhrase());
		responseMsg.setData(o);
		return responseMsg;
	}
	public static ResponseMessage getDefaultSuccessMsg(){
		ResponseMessage responseMsg = new ResponseMessage();
		responseMsg.setCode(HttpStatus.OK.value());
		responseMsg.setMessage(HttpStatus.OK.getReasonPhrase());
		return responseMsg;
	}
}
