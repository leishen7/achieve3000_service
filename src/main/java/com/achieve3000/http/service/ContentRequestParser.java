package com.achieve3000.http.service;

import javax.servlet.http.HttpServletRequest;

public interface ContentRequestParser {
	ContentRequest parseRequest(HttpServletRequest request) throws Exception;

}
