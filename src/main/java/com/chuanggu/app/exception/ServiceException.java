package com.chuanggu.app.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 2990891969629653517L;
	
	public ServiceException() {
	}

	public ServiceException(String arg0) {
		super(arg0);
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public ServiceException(String message, Exception exception) {
		super(message, exception);
	}
}
