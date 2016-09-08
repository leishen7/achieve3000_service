package com.achieve3000.http.core;

public interface RequestParser<T> {
	T parserRequest(String request) throws Exception;

}
