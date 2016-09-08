package com.achieve3000.http.service;

import javax.servlet.http.HttpServletRequest;

import com.achieve3000.http.core.ContentType;

public class ContentTypeManager {
	public static ContentType getContentType(HttpServletRequest request){
		if(request.getRequestURI()!=null){
			for(ContentType contentType:ContentType.values()){
				if(request.getRequestURI().endsWith(contentType.getEndPoint())){
					return contentType;
				}
			}
		}
		
		if(request.getContentType()!=null){
			for(ContentType contentType:ContentType.values()){
				if(request.getContentType().equals(contentType.getEndPoint())){
					return contentType;
				}
			}
		}
		
		//return ContentType.PROTOBUF;
		return null;
	}

}
