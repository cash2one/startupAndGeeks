package com.chuanggu.app.util;

public enum HttpStatus {
	// 1xx Informational

	CONTINUE(100, "Continue"),

	SWITCHING_PROTOCOLS(101, "Switching Protocols"),

	PROCESSING(102, "Processing"),

	CHECKPOINT(103, "Checkpoint"),

	// 2xx Success

	OK(200, "OK"),
	NOT_LOGIN(210, "User Not Login"),
	WRONG_PASSWORD(211, "Wrong Password"),
	NO_SUCH_USER(212, "No Such User"),
	
	USER_TOKEN_EXPIRE(213, "User Token Expire"),
	USER_TOKEN_ILLEAGAL(214, "Illeagal User Token"),
	NOT_SAME_IP(215, "Not Same Ip Request"),
	EXIST_USER(216,"Exist Username"),
	WRONG_USER(217,"Wrong User Name"),
	WRONG_CODE(218,"Verification code is not correct "),
	TIMEOUT_CODE(219,"Verification code is timeout"),
	INCORRECT_PWD(220,"Password incorrect"),


	DATABASE_EXCEPTION(300,"database exception"),
	INTERNAL_SERVER_ERROR(500, "Internal server error"),

	FORM_VALIDATE_ERROR(400, "Form field validate error");
	
	private final int value;

	private final String reasonPhrase;


	private HttpStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}

}
