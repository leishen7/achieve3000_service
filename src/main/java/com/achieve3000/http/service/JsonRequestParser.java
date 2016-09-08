package com.achieve3000.http.service;


import static com.achieve3000.rest.main.Constants.OPERATION;
import static com.achieve3000.rest.main.Constants.REQUEST_ID;
import static com.achieve3000.rest.main.Constants.UTF_8;

import java.io.BufferedReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonRequestParser implements ContentRequestParser{
	Logger log=LoggerFactory.getLogger(JsonRequestParser.class);
	@Override
	public ContentRequest parseRequest(HttpServletRequest httpRequest)
			throws Exception {
		
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = httpRequest.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

log.debug("JSON Content:"+jb.toString());

		MultiMap<String> paramsMap =new MultiMap<String>();
		String queryString=URLDecoder.decode(httpRequest.getQueryString(),UTF_8);

		UrlEncoded.decodeTo(queryString, paramsMap,UTF_8);
		Map<String,String> jsonStringMap=new HashMap<String,String>();
		String operation=null;
		String requestId=null;
		
		for(Map.Entry<String, List<String>> entry:paramsMap.entrySet()){
			String paramName=entry.getKey();
			if(paramName.equalsIgnoreCase(OPERATION)){
				operation=entry.getValue().get(0).toLowerCase();
			}else if(paramName.equalsIgnoreCase(REQUEST_ID)){
				requestId=entry.getValue().get(0).toLowerCase();
			}else{
				jsonStringMap.put("\""+paramName+"\"","\""+entry.getValue().get(0)+"\"");
			}
		}
		
		//String jsonString=StringUtils.replace(jsonStringMap.toString(), "=", ":");
		
		return new ContentRequest(operation, requestId, jb.toString());
	}


}
