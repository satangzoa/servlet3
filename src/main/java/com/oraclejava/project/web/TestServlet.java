package com.oraclejava.project.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet") //��û ���� servlet
public class TestServlet extends HttpServlet{
	// context�� �ǹ� => ���� ����ǰ� �ִ� ��Ĺ

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>TestServlet</title></head>");
		out.println("<body>");
		out.println("<h2>Test ���Դϴ�</h2>");	
		ServletContext context = getServletContext();
		String webmaster = (String)context.getAttribute("webmaster");
		out.println("<footer><a href='mailto:" + webmaster + " '>"
				+ webmaster + "</a></footer>");
		out.println("</body>");
		out.println("</html>");
	
	}

	
}
