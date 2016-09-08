package com.achieve3000.http.request;

import java.util.Map;

public class QueryStringData implements RequestItem{
	public QueryStringData(Map<String, String> requestParamMap) {
		super();
		this.requestParamMap = requestParamMap;
	}

	private final Map<String, String> requestParamMap;

	public Map<String, String> getRequestParamMap() {
		return requestParamMap;
	}

	public String getValue(String key){
		return requestParamMap.get(key);
	}

}
