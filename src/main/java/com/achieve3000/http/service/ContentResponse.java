package com.achieve3000.http.service;

import com.achieve3000.http.core.AbstractResponse;

public class ContentResponse extends AbstractResponse<Object> {
 private Object responseObj;
	@Override
	public Object getResponseData() {
		return responseObj;
	}

	@Override
	public void setResponseData(Object data) {
		responseObj=data;
		
	}

}
