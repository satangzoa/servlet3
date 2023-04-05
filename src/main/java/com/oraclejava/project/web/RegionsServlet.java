package com.oraclejava.project.web;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegionsServlet")
public class RegionsServlet extends HelloServlet{
	
	private void loadJdbcDriver() throws ServletException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("����̺� �ε� ����^^b");
		} catch (ClassNotFoundException e) {
			throw new ServletException("����̹� �ε� ����", e);
		}
		
	}
	
	private Connection getConnection() throws ServletException {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr","hr");
		} catch (SQLException e) {
			throw new ServletException("���ӿ����߻�",e);
		}
	}
	
	private void closeConnection(Connection con) {
		//4.������ Ŀ�ؼ��� �����Ѵ�.
		try {
			con.close();
			System.out.println("Ŀ�ؼ� ����!");
		} catch (SQLException e) {
			e.printStackTrace();
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
//			Class.forName("oracle.jdbc.OracleDriver");
//			System.out.println("����Ŭ ����̹��� �ε��߽��ϴ�...");
//		} catch (ClassNotFoundException e) {
//			System.out.println("����̹��� �ε��� �� �����ϴ�.");
//			e.printStackTrace();
//		}
//		
		Connection con = getConnection();
		System.out.println("Ŀ�ؼ� ����");
		
//		try {
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "hr", "hr");
//			System.out.println("Ŀ�ؼ� ����!");
//		} catch (SQLException e) {
//			throw new ServletException("Ŀ�ؼ� ����", e);
//		}
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='html/style.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> RegionsServlet �Դϴ�. </h1>");
		
		// 3. ���� ����
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT region_id,region_name from regions");
					//out.println("<table border=1 cellpadding=0 cellspacing=0 width=300>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<th>���� id</th>");
					out.println("<th>������</th>");
					out.println("</tr>");
					//out.println("<tr><td>���� id</td><td>������</td></tr>");
					while (rs.next()) {
						out.println("<tr>");
						int region_id = rs.getInt("region_id");
						String region_name = rs.getString("region_name");
						out.println("<td>" + region_id  + "</td>");
						out.println("<td>" + region_name  + "</td>");
						out.println("</tr>");
					}
					out.println("</table>");
					System.out.println("���� ���� �Ϸ�");
					rs.close();
					stmt.close();
					
				} catch (SQLException e) {
					out.println(e);
					throw new ServletException("���� ���� ����", e);
				
				} finally {
					closeConnection(con);
				}
				
				
				out.println("</body>");
				out.println("</html>");
				
				
			}

	
}






