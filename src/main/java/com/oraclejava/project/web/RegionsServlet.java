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
			System.out.println("드라이브 로드 성공^^b");
		} catch (ClassNotFoundException e) {
			throw new ServletException("드라이버 로드 실패", e);
		}
		
	}
	
	private Connection getConnection() throws ServletException {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr","hr");
		} catch (SQLException e) {
			throw new ServletException("접속오류발생",e);
		}
	}
	
	private void closeConnection(Connection con) {
		//4.언제나 커넥션을 종료한다.
		try {
			con.close();
			System.out.println("커넥션 종료!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() throws ServletException {
		//1. jdbc 드라이버 로드
		loadJdbcDriver();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//			System.out.println("오라클 드라이버를 로드했습니다...");
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버를 로드할 수 없습니다.");
//			e.printStackTrace();
//		}
//		
		Connection con = getConnection();
		System.out.println("커넥션 성공");
		
//		try {
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "hr", "hr");
//			System.out.println("커넥션 성공!");
//		} catch (SQLException e) {
//			throw new ServletException("커넥션 실패", e);
//		}
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='html/style.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> RegionsServlet 입니다. </h1>");
		
		// 3. 쿼리 실행
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT region_id,region_name from regions");
					//out.println("<table border=1 cellpadding=0 cellspacing=0 width=300>");
					out.println("<table>");
					out.println("<tr>");
					out.println("<th>지역 id</th>");
					out.println("<th>지역명</th>");
					out.println("</tr>");
					//out.println("<tr><td>지역 id</td><td>지역명</td></tr>");
					while (rs.next()) {
						out.println("<tr>");
						int region_id = rs.getInt("region_id");
						String region_name = rs.getString("region_name");
						out.println("<td>" + region_id  + "</td>");
						out.println("<td>" + region_name  + "</td>");
						out.println("</tr>");
					}
					out.println("</table>");
					System.out.println("쿼리 실행 완료");
					rs.close();
					stmt.close();
					
				} catch (SQLException e) {
					out.println(e);
					throw new ServletException("쿼리 실행 오류", e);
				
				} finally {
					closeConnection(con);
				}
				
				
				out.println("</body>");
				out.println("</html>");
				
				
			}

	
}






