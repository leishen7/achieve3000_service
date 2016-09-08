package com.achieve3000.http.core;

public interface Operator<T,S> {
	
	public void handleOperation(AbstractRequest<T> request,AbstractResponse<S> response) throws Exception;
	
	public void cancelOperation(AbstractRequest<T> request);

}
