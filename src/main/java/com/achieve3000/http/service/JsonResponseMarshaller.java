package com.achieve3000.http.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achieve3000.http.core.AbstractResponse;

public class JsonResponseMarshaller implements ContentResponseMarshaller {
	Logger log=LoggerFactory.getLogger(JsonResponseMarshaller.class);
	@Override
	public byte[] marshallResponse(AbstractResponse<Object> response)
			throws IOException, JAXBException {
		ObjectMapper objectMap=new ObjectMapper();
		final DateFormat df= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:");
		objectMap.setDateFormat(df);
		log.debug("Send response:"+response.getResponseData());
		return objectMap.writeValueAsBytes(response.getResponseData());
	}

}
