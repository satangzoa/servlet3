package com.oraclejava.project.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now")
public class NowServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//자신의 이름을 저장하는 변수 name 작성
		String name = " 박소망";
		
		//현재 시간을 저장하는 변수 date 작성
		 Date date = new Date();
	      
		 resp.setContentType("text/html; charset=utf-8");
		 PrintWriter out = resp.getWriter();
		 out.println("<html>");
		 out.println("<body>");
		 out.println("<h1>이름: name</h1>");
		 out.println("<h1>이름:" + name +"</h1>");
		 out.println("<h1>현재시간:" + date +"</h1>");
		 out.println("</body>");
		 out.println("</html>");
		 
		
	}

	
	
}
