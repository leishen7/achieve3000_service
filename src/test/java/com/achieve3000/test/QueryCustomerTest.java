package com.achieve3000.test;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class QueryCustomerTest extends AbstractTest{
	


	private final String httpURL="http://localhost:8089/json?operation=";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetCustomerByOrderNum() {
		String operation="getCustomerByOrderNum&ordernum=";
		
		System.out.println("OrderNum=0 Response="+getRequest(httpURL+operation+"0").getContentAsString());
		System.out.println("OrderNum=1 Response="+getRequest(httpURL+operation+"1").getContentAsString());
		System.out.println("OrderNum=2 Response="+getRequest(httpURL+operation+"2").getContentAsString());
		System.out.println("OrderNum=3 Response="+getRequest(httpURL+operation+"3").getContentAsString());
		System.out.println("OrderNum=4 Response="+getRequest(httpURL+operation+"4").getContentAsString());
	}
	
	@Test
	public void testGetHighValueCustomer() {
		String operation="getHighValueCustomer";
		
		System.out.println("getHighValueCustomer Response="+getRequest(httpURL+operation).getContentAsString());

	}
	
}
