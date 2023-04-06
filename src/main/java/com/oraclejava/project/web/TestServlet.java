package com.oraclejava.project.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet") //요청 응답 servlet
public class TestServlet extends HttpServlet{
	// context의 의미 => 현재 실행되고 있는 톰캣

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>TestServlet</title></head>");
		out.println("<body>");
		out.println("<h2>Test 중입니다</h2>");	
		ServletContext context = getServletContext();
		String webmaster = (String)context.getAttribute("webmaster");
		out.println("<footer><a href='mailto:" + webmaster + " '>"
				+ webmaster + "</a></footer>");
		out.println("</body>");
		out.println("</html>");
	
	}

	
}
