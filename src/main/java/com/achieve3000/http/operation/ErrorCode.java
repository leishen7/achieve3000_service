package com.achieve3000.http.operation;

public enum ErrorCode {
	REQUEST_EMPTY(101,"Request is empty"),
	SYSTEM_ERROR(102,"System error"),
	
	NULL_ORDER_NUMBER(201,"The order number is empty"),
	INVALID_ORDER_NUMBER(202,"The order number is empty");
	private ErrorCode(int code, String desciption) {
		this.code = code;
		this.desciption = desciption;
	}
	private int code;
	private String desciption;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

}
