package com.achieve3000.http.operation;

import org.apache.commons.lang.StringUtils;

public class ServiceResponse {
	public enum Status{OK,ERROR};
	Status responseStatus;
	ErrorCode errorCode;
	Object content;
	String errorDescription;
	
	private ServiceResponse(){
		
	}
	
	private ServiceResponse(ResponseBuilder responseBuilder){
		this.responseStatus=responseBuilder.responseStatus;
		this.errorCode=responseBuilder.errorCode;
		this.content=responseBuilder.content;
		this.errorDescription=responseBuilder.errorDescription;
	
	}
	
	public static ResponseBuilder Ok(){
		return new ServiceResponse.ResponseBuilder(Status.OK);
	}
	
	public static ResponseBuilder Error(){
		return new ServiceResponse.ResponseBuilder(Status.ERROR);
	}
	
	public static class ResponseBuilder{
		Status responseStatus;
		ErrorCode errorCode;
		Object content;
		String errorDescription;
		
		public ResponseBuilder(Status responseStatus){
			this.responseStatus=responseStatus;
		}
		
		public ResponseBuilder errorCode(ErrorCode errorCode) {
			this.errorCode=errorCode;
			return this;
		}
		
		public ResponseBuilder errorDescription(String errorDescription) {
			this.errorDescription=errorDescription;
			return this;
		}
		
		public ResponseBuilder content(Object content) {
			this.content=content;
			return this;
		}
		
		public ServiceResponse build() {
			return new ServiceResponse(this);
		}
	}
	
	public Status getResponseStatus() {
		return responseStatus;
	}
	public Integer getErrorCode() {
		if(errorCode!=null)
			return errorCode.getCode();
		else
			return null;
	}
	public Object getContent() {
		return content;
	}
	public String getErrorDescription() {
		if(errorCode!=null){
			if(StringUtils.isBlank(errorDescription)){		
				return errorCode.getDesciption();
			}else{
				return errorDescription;
			}
		}else{
			return null;
		}
	
	}

}
