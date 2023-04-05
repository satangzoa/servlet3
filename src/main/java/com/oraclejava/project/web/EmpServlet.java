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
			System.out.println("드라이브 로드 성공!");
		} catch (ClassNotFoundException e) {
			throw new ServletException("드라이버 로드 실패", e);
		}
	
	}
	
	private Connection getConnection() throws ServletException {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		} catch (SQLException e) {
			throw new ServletException("접속오류발생",e);
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
//		Class.forName("oracle.jdbc.OracleDriver");
//		System.out.println("오라클 드라이버를 로드했습니다...");
//	} catch (ClassNotFoundException e) {
//		System.out.println("드라이버를 로드할 수 없습니다.");
//		e.printStackTrace();
//	}	
		//2. jdbc커넥션을 맺는다.
		Connection con = getConnection();
		
//		
//		try {
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "hr", "hr");
//			System.out.println("커넥션 성공!");
//		} catch (SQLException e) {
//			throw new ServletException("커넥션 실패!", e);
//		}
//		
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1> EmpServlet 입니다. </h1>");
		
		
		// 3. 쿼리 실행
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
			System.out.println("쿼리 실행 완료");
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			out.println(e);
			throw new ServletException("쿼리 실행 오류!", e);
			
		} finally {
			//4.언제나 커넥션을 종료한다.
			try {
				con.close();
				System.out.println("커넥션 종료!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

	
}









