package com.achieve3000.http.core;

import com.achieve3000.http.operation.ServiceResponse;
import com.achieve3000.http.request.RequestItem;

public abstract class AbstractOperator<T extends RequestItem, S extends ServiceResponse> implements Operator<String, S> {
	public AbstractOperator(RequestParser<T> requestParser) {
		super();
		this.requestParser = requestParser;
	}

	private final RequestParser<T> requestParser;
	
	protected abstract boolean validateRequest(T requestObj) throws RequestParamException;

	protected abstract S processRequest(T requestObj);

	@Override
	public void handleOperation(AbstractRequest<String> request, AbstractResponse<S> response)
			throws Exception {
		T requestObj=requestParser.parserRequest(request.getRequestData());
		if(validateRequest(requestObj)){
			S responseObj=processRequest(requestObj);
			response.setResponseData(responseObj);
		}
		
	}

}
