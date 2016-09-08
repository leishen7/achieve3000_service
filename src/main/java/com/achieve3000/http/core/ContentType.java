package com.achieve3000.http.core;

public enum ContentType {
	JSON("application/json","json"),
	XML("application/xml","xml"),
	PROTOBUF("application/x-protobuf","proto");
	
	private ContentType(String content, String endPoint) {
		this.content = content;
		this.endPoint = endPoint;
	}
	private final String content;
	private final String endPoint;
	public String getContent() {
		return content;
	}
	public String getEndPoint() {
		return endPoint;
	}
}
