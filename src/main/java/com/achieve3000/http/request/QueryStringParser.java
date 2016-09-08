package com.achieve3000.http.request;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import com.achieve3000.http.core.RequestParser;
import com.achieve3000.rest.main.Constants;

public class QueryStringParser implements RequestParser<QueryStringData> {

	@Override
	public QueryStringData parserRequest(String queryString) throws Exception {
		MultiMap<String> paramsMap =new MultiMap<String>();
		Map<String,String> paramKey2ValueMap=new HashMap<String, String>();
		UrlEncoded.decodeTo(queryString, paramsMap,Constants.UTF_8);
		
		for(String key:paramsMap.keySet()){
			paramKey2ValueMap.put(key, paramsMap.getString(key));				
		}		
		return new QueryStringData(paramKey2ValueMap);

	}

}
