package com.achieve3000.http.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.achieve3000.http.core.AbstractResponse;

public interface ContentResponseMarshaller {
	byte[] marshallResponse(AbstractResponse<Object> response) throws IOException,JAXBException;
}
