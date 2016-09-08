package com.achieve3000.http.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achieve3000.dao.OrderDAO;
import com.achieve3000.http.core.AbstractOperator;
import com.achieve3000.http.core.AbstractRequest;
import com.achieve3000.http.core.RequestParamException;
import com.achieve3000.http.core.RequestParser;
import com.achieve3000.http.request.QueryStringData;

@Service
public class GetHighValueCustomer extends AbstractOperator<QueryStringData, ServiceResponse> {
	Logger log=LoggerFactory.getLogger(GetHighValueCustomer.class);
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	public GetHighValueCustomer(RequestParser requestParser) {
		super(requestParser);

	}

	@Override
	public void cancelOperation(AbstractRequest<String> request) {

		
	}

	@Override
	protected boolean validateRequest(QueryStringData requestObj)
			throws RequestParamException {
		return true;
	}

	@Override
	protected ServiceResponse processRequest(QueryStringData requestObj) {
		
		try{
			return ServiceResponse.Ok().content(orderDAO.getHighValueCustomerList()).build();
		}catch(Exception e){
			log.error("Failed to get high value customer:"+requestObj);
			return ServiceResponse.Error().errorCode(ErrorCode.SYSTEM_ERROR).build();
		}
	}

}
