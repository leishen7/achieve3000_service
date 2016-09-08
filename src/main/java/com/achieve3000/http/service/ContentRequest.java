package com.achieve3000.http.service;

import com.achieve3000.http.core.AbstractRequest;
import com.achieve3000.http.request.RequestItem;

public class ContentRequest extends AbstractRequest<String> implements RequestItem{

	private String requestParams;
	
	public ContentRequest(String operation, String requestId,String requestParams) {
		super();
		setOperation(operation);
		setRequestId(requestId);
		this.requestParams = requestParams;
	}

	@Override
	public String getRequestData() {
		// TODO Auto-generated method stub
		return requestParams;
	}

	@Override
	public void setRequestData(String data) {
		requestParams=data;
		
	}


}
