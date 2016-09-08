package com.achieve3000.http.core;

public class ErrorResponse extends AbstractResponse<String> {
	
	private String errorMessage;

	@Override
	public String getResponseData() {
		return errorMessage;
	}

	@Override
	public void setResponseData(String data) {
		errorMessage=data;
		
	}

}
