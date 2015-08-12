package com.chuanggu.app.util;


import com.chuanggu.app.dto.ResponseMessage;

public class ResponseMsgUtil {
	public static ResponseMessage getResponseMsg(Object o){
		ResponseMessage responseMsg = new ResponseMessage();
		responseMsg.setCode(HttpStatus.OK.value());
		responseMsg.setData(o);
		return responseMsg;
	}
	
	public static ResponseMessage getDefaultSuccessMsg(){
		ResponseMessage responseMsg = new ResponseMessage();
		responseMsg.setCode(HttpStatus.OK.value());
		return responseMsg;
	}
	
	public static ResponseMessage getDefaultFailMsg(Object o){
		ResponseMessage responseMsg = new ResponseMessage();
		responseMsg.setCode(HttpStatus.FORM_VALIDATE_ERROR.value());
		responseMsg.setData(o);
		return responseMsg;
	}
	
	public static ResponseMessage getSpecificCodeMsg(int code){
		ResponseMessage responseMsg = new ResponseMessage();
		responseMsg.setCode(code);
		return responseMsg;
	}
}
