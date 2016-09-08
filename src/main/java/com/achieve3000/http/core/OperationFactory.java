package com.achieve3000.http.core;

import java.util.Map;

public class OperationFactory<T,S> {
	public OperationFactory(Map<String, Operator<T, S>> operationOperatorMap) {
		super();
		this.operationOperatorMap = operationOperatorMap;
	}

	private Map<String, Operator<T, S>> operationOperatorMap;
	
	public Operator<T, S> getOperator(String operation){
		return operationOperatorMap.get(operation);
	}

}
