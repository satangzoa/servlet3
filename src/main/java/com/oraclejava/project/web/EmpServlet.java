package com.oraclejava.project.web;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {

	private void loadJdbcDriver() throws ServletException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("����̺� �ε� ����!");
		} catch (ClassNotFoundException e) {
			throw new ServletException("����̹� �ε� ����", e);
		}
	
	}
	
	private Connection getConnection() throws ServletException {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		} catch (SQLException e) {
			throw new ServletException("���ӿ����߻�",e);
		} 
	}
	
	@Override
	public void init() throws ServletException {
		//1. jdbc ����̹� �ε�
		loadJdbcDriver();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		try {
//		Class.forName("oracle.jdbc.OracleDriver");
//		System.out.println("����Ŭ ����̹��� �ε��߽��ϴ�...");
//	} catch (ClassNotFoundException e) {
//		System.out.println("����̹��� �ε��� �� �����ϴ�.");
//		e.printStackTrace();
//	}	
		//2. jdbcĿ�ؼ��� �δ´�.
		Connection con = getConnection();
		
//		
//		try {
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "hr", "hr");
//			System.out.println("Ŀ�ؼ� ����!");
//		} catch (SQLException e) {
//			throw new ServletException("Ŀ�ؼ� ����!", e);
//		}
//		
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1> EmpServlet �Դϴ�. </h1>");
		
		
		// 3. ���� ����
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT employee_id, first_name, last_name from employees");
			out.println("<ul>");
					
			while (rs.next()) {
				int emp_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				out.println("<li>" + emp_id + " " + first_name + " "
						+last_name+ "</li>");
				
				out.println();
				out.println();
			}
			out.println("</ul>");
			System.out.println("���� ���� �Ϸ�");
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			out.println(e);
			throw new ServletException("���� ���� ����!", e);
			
		} finally {
			//4.������ Ŀ�ؼ��� �����Ѵ�.
			try {
				con.close();
				System.out.println("Ŀ�ؼ� ����!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

	
}









