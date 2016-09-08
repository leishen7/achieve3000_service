package com.achieve3000.http.service;

import org.codehaus.jackson.map.ObjectMapper;

import com.achieve3000.http.core.RequestParser;

public abstract class AbstractJSONRequestParser<T> implements RequestParser<T> {
	
	public AbstractJSONRequestParser(Class<T> type) {
		this.type = type;
	}

	private Class<T> type;

	@Override
	public T parserRequest(String request) throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		return mapper.readValue(request, type);
	}

}
