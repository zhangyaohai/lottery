package com.lottery.common.exception;

public class PredictException extends RuntimeException {
	private	int code =200;
	private	 String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PredictException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public PredictException(String message) {
		super();
		this.message = message;
	}
	
	
}
