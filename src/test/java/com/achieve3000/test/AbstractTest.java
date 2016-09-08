package com.achieve3000.test;

import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;

public abstract class AbstractTest {
	
	protected ContentResponse getRequest(String httpURL){
		HttpClient client=new HttpClient();
		
		try{
			client.start();
			//ContentProvider content=
			return client.GET(httpURL);

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	protected void postRequest(String jsonData,String httpURL){
		HttpClient client=new HttpClient();
		final CountDownLatch countDown = new CountDownLatch(1);
		try{
			client.start();
			
			client.POST(httpURL).param("json", jsonData).send(new BufferingResponseListener() {
				
				@Override
				public void onComplete(Result result) {
					System.out.println(result.getResponse().getStatus());
					countDown.countDown();
					
				}
				
				@Override
                public void onFailure(Response response, Throwable failure) {
                    countDown.countDown();
                }
			});
			
			countDown.await();
		
			System.out.println("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
