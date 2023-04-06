package com.oraclejava.project.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener //이벤트핸들러
public class TestListener implements ServletContextListener{

	// context의 의미 => 현재 실행되고 있는 톰캣
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = 
				sce.getServletContext();//set 설정하다 get 가져오다
		String email = context.getInitParameter("webmaster");//web.xml에 있는 webmaster
		context.setAttribute("webmaster", email);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
	
	

}
