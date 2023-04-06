package com.oraclejava.project.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener //�̺�Ʈ�ڵ鷯
public class TestListener implements ServletContextListener{

	// context�� �ǹ� => ���� ����ǰ� �ִ� ��Ĺ
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = 
				sce.getServletContext();//set �����ϴ� get ��������
		String email = context.getInitParameter("webmaster");//web.xml�� �ִ� webmaster
		context.setAttribute("webmaster", email);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
	
	

}
