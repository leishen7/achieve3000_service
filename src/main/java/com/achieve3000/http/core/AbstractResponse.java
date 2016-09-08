package com.achieve3000.http.core;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractResponse<T> {
	private String requestId;
	private String operation;
	
	private BlockingQueue<T> queue;
	
	public BlockingQueue<T> getQueue() {
		return queue;
	}
	public void setQueue(BlockingQueue<T> queue) {
		this.queue = queue;
	}
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
	
	public abstract T getResponseData();
	public abstract void setResponseData(T data);

}
