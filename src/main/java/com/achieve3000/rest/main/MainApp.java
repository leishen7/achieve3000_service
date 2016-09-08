package com.achieve3000.rest.main;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {
	private static final Logger log=LoggerFactory.getLogger(MainApp.class);
	
	private ConfigurableApplicationContext context;
	public void start(String[] args) {
		try{
			context = new ClassPathXmlApplicationContext("main-context.xml");
			log.info("Spring context initilized");
			context.registerShutdownHook();
			context.start();
			log.info("Spring context started");
		}catch(Exception e){
			log.error("Can't start application "+e);
		}
	}
	
	public void destroy(){
		if(context!=null){
			context.stop();
			log.info("Spring context stopped");
		}
	}

	
	
   public static void main(String[] args) {
	   try{
			new MainApp().start(args);
		}catch(Throwable t){
			log.error(ExceptionUtils.getFullStackTrace(t));
		}
   }

public ConfigurableApplicationContext getContext() {
	return context;
}
}
