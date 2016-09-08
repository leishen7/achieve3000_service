package com.achieve3000.http.service;

import com.achieve3000.http.core.AbstractRequest;
import com.achieve3000.http.core.OperationFactory;

import com.achieve3000.http.core.Operator;

public class OperationDelegator {
	public OperationDelegator(OperationFactory operationFactory) {
		super();
		this.operationFactory = operationFactory;
	}

	private final OperationFactory operationFactory;
	
	public void handleRequest(AbstractRequest contentRequest,ContentResponse contentResponse) throws Exception{
		Operator operator=operationFactory.getOperator(contentRequest.getOperation());
		if(operator!=null){
			operator.handleOperation(contentRequest, contentResponse);
		}else{
			throw new javax.naming.OperationNotSupportedException("Operation not supported:"+contentRequest.getOperation());
		}
	}

}
