package com.oraclejava.project.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TestListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("나 태어났어.. 응애응애...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("나 죽어... ^^b");
	}
	
	

}
