package com.oraclejava.project.web;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {

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
		try {
			con.close();
			System.out.println("커넥션 종료!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() throws ServletException {
		loadJdbcDriver();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection con = getConnection();
		System.out.println("커넥션 성공");
		
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html >");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> 영화 목록 출력 입니다. </h1>");
		
		//  쿼리 실행
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * from movie");
					
					out.println("<table border=1 cellpadding=0 cellspacing=0 width=500>");
					out.println("<tr>");
					out.println("<th>영화 id</th>");
					out.println("<th>제목</th>");
					out.println("<th>가격</th>");
					out.println("</tr>");
					
					while (rs.next()) {
						out.println("<tr>");
						int MOVIE_ID = rs.getInt("MOVIE_ID");
						String TITLE = rs.getString("TITLE");
						int PRICE = rs.getInt("PRICE");
						out.println("<td>" + MOVIE_ID  + "</td>");
						out.println("<td>" + TITLE  + "</td>");
						out.println("<td>" + PRICE  + "</td>");
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

