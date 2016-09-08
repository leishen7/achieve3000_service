package com.achieve3000.http.service;


import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class RestfulService {

	private static final Logger log=LoggerFactory.getLogger(RestfulService.class);

	private final int httpPort;
	private final int maxThreads;
	private HttpRequestHandler  requestHandler;

	
	private Server httpServer;
	
	
	public RestfulService(int httpPort, int maxThreads,HttpRequestHandler requestHandler) {
		this.httpPort = httpPort;
		this.maxThreads = maxThreads;
		this.requestHandler = requestHandler;
	}
	
	public void init(){
		try{
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");
			startServer();
			log.info("Restful Service has been started at port "+httpPort);

		}catch(Exception e){
			log.error("Failed to start restful service at "+httpPort+" :"+e);
			//throw new RuntimeException(e);
		}
		
	}
	
	public void monitorServerStats(Server server){
		Boolean isSreverRuuning=server.isRunning();
		Runtime runtime=Runtime.getRuntime();
		long maxMem=runtime.maxMemory();
		long allocatedMem=runtime.totalMemory();
		long freeMem=runtime.freeMemory();
		
		//System.out.println("allocatedMem="+allocatedMem);
		log.info("Server is running:"+isSreverRuuning+",Max Memory"+maxMem+", Allocated Memery:"+allocatedMem+", Free memory:"+freeMem);
	}
	
	protected void startServer() throws Exception{
		QueuedThreadPool threadPool=new QueuedThreadPool();
		threadPool.setMaxThreads(maxThreads);
		httpServer= new Server(threadPool);
			httpServer.setHandler(requestHandler);
		
		
		ServerConnector connector = new ServerConnector(httpServer, new HttpConnectionFactory());
		connector.setPort(httpPort);
		httpServer.addConnector(connector);
		httpServer.start();
	}


}
