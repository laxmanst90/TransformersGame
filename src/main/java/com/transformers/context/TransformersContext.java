package com.transformers.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.transformers.database.DatabaseClass;
import com.transformers.model.Transformers;


public class TransformersContext implements ServletContextListener{

	private static ConcurrentHashMap<Integer, Transformers> transformers = DatabaseClass.getTransformers();
	private static ServletContext servletContext;
	
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("I am starting");
		servletContext =  servletContextEvent.getServletContext();
		servletContext.setAttribute("org.eclipse.jetty.annotations.maxWait", 300);
		transformers.put(1, new Transformers(1,6,6,7,9,5,2,9,7,"Autobots","Bluestreak",false));
		transformers.put(2, new Transformers(2,8,9,2,6,7,5,6,10,"Decepticons","Soundwave",false));
		transformers.put(3, new Transformers(3,6,6,6,6,8,8,8,8,"Autobots","Bumble Bee",false));
		servletContext.setAttribute("transformersMap", transformers);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		servletContext = null;
		transformers = null;
		System.out.println("I am destroyed");
	}
	
	public static ServletContext getApplicationContext(){
		if(servletContext != null)
		return servletContext;
		else return null;
	}

}
