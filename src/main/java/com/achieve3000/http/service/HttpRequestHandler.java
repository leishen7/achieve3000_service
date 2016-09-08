package com.achieve3000.http.service;

import static com.achieve3000.rest.main.Constants.GET;
import static com.achieve3000.rest.main.Constants.OPERATION;
import static com.achieve3000.rest.main.Constants.REQUEST_ERROR;
import static com.achieve3000.rest.main.Constants.REQUEST_ID;
import static com.achieve3000.rest.main.Constants.UTF_8;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve3000.http.core.AbstractRequest;
import com.achieve3000.http.core.ContentType;
import com.achieve3000.rest.main.Constants;

public class HttpRequestHandler extends AbstractHandler {
	Logger log=LoggerFactory.getLogger(HttpRequestHandler.class);

	public HttpRequestHandler(OperationDelegator delegator,ContentParserFactory contentParserFactory) {
		super();
		this.contentParserFactory = contentParserFactory;
		this.delegator = delegator;
	
	}
	protected final ContentParserFactory contentParserFactory;
    protected final OperationDelegator delegator;
    //protected final XMLParser<XMLRequest> xmlParser;
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws IOException, ServletException {
		log.debug("Handle request "+baseRequest.getQueryString());
		ContentType contentType=ContentTypeManager.getContentType(httpRequest);
		if(contentType!=null){
			try{
				AbstractRequest parsedRequest=createRequest(contentType, target, baseRequest, httpRequest);
				ContentResponse contentResponse = new ContentResponse();
				contentResponse.setOperation(parsedRequest.getOperation());
				contentResponse.setRequestId(parsedRequest.getRequestId());
				delegator.handleRequest(parsedRequest, contentResponse);
				ContentResponseMarshaller responseMarshaller=contentParserFactory.getResponseMarshaller(contentType);
				httpResponse.setContentType(contentType.getContent());
				httpResponse.setCharacterEncoding(Constants.UTF_8);
				httpResponse.setHeader("Access-Control-Allow-Origin", "*");
				httpResponse.setHeader("allowedMethods", "GET,POST,HEAD");
				httpResponse.setHeader("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
				ServletOutputStream outputStream=httpResponse.getOutputStream();
				outputStream.write(responseMarshaller.marshallResponse(contentResponse));
				outputStream.flush();
				
			}catch (Exception e) {
				log.error("Failed to handle request "+e);
				handleError(httpRequest, httpResponse, REQUEST_ERROR, e.getMessage());
			}
		}
		

	}
	
	private AbstractRequest createRequest(ContentType contentType,String target, Request baseRequest, HttpServletRequest httpRequest) throws Exception{
		if(baseRequest.getMethod().equalsIgnoreCase(GET)){
			return createRequestFromGET(httpRequest);
		}else{
			return createRequestFromPOST(contentType, target, baseRequest, httpRequest);
		}
	}
	
	private AbstractRequest createRequestFromGET(HttpServletRequest httpRequest) throws UnsupportedEncodingException{
		log.debug("createRequestFromGET "+httpRequest.getQueryString());
		MultiMap<String> paramsMap =new MultiMap<String>();
		String queryString=URLDecoder.decode(httpRequest.getQueryString(),UTF_8);
		UrlEncoded.decodeTo(queryString, paramsMap,UTF_8);
		String operation=null;
		String requestId=null;
		for(Map.Entry<String, List<String>> entry:paramsMap.entrySet()){
			String paramName=entry.getKey();
			if(paramName.equalsIgnoreCase(OPERATION)){
				operation=entry.getValue().get(0).toLowerCase();
			}else if(paramName.equalsIgnoreCase(REQUEST_ID)){
				requestId=entry.getValue().get(0).toLowerCase();
			}
		}
		
		return new ContentRequest(operation, requestId, queryString);
	}
	
	private AbstractRequest createRequestFromPOST(ContentType contentType,String target, Request baseRequest, HttpServletRequest httpRequest) throws Exception{
		
		log.debug("createRequestFromPOST "+httpRequest.getQueryString());
		
		
		ContentRequestParser requestParser=contentParserFactory.getRequestParser(contentType);
	
		return requestParser.parseRequest(httpRequest);

	}
	
	private void handleError(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse,int errorCode,String message){
		try{
			//ErrorResponse errorResponse=new ErrorResponse();
			httpResponse.setStatus(errorCode);
			ServletOutputStream outputStream=httpResponse.getOutputStream();
			outputStream.write(message.getBytes());
			outputStream.flush();
			
		}catch(Exception e){
			log.error("Failed to handle Error"+e);
		}
	}

}
