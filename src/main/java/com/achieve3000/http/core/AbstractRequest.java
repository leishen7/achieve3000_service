package com.achieve3000.http.core;

public abstract class AbstractRequest<T> {
	private String requestId;
	private String operation;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public abstract T getRequestData();
	public abstract void setRequestData(T data);

}
