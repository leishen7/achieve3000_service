package com.achieve3000.http.service;

import java.util.Map;

import com.achieve3000.http.core.ContentType;

public class ContentParserFactory<R extends ContentRequestParser,M extends ContentResponseMarshaller> {
	private final Map<ContentType, R> requestParsers;
	private final Map<ContentType, M> responseMarshallers;
	
	public ContentParserFactory(Map<ContentType, R> requestParsers,Map<ContentType, M> responseMarshallers) {
		super();
		this.requestParsers = requestParsers;
		this.responseMarshallers = responseMarshallers;
	}

	
	public ContentRequestParser getRequestParser(ContentType contentType) throws Exception{
		ContentRequestParser requestParser=requestParsers==null?null:requestParsers.get(contentType);
		if(requestParser==null){
			throw new Exception("No request parser for "+contentType.getEndPoint());
		}else{
			return requestParser;
		}
	}
	
	public ContentResponseMarshaller getResponseMarshaller(ContentType contentType) throws Exception{
		ContentResponseMarshaller responseMarshaller=responseMarshallers==null?null:responseMarshallers.get(contentType);
		if(responseMarshaller==null){
			throw new Exception("No response marshaller for "+contentType.getEndPoint());
		}else{
			return responseMarshaller;
		}
	}

}
