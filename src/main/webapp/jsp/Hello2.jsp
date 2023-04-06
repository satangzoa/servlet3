<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int sNo = 1;
	String name= "이자바";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	학번: <%= sNo %> <br>
	이름: <%= name %>
	<%@ include file="Hello2b.jsp" %>
</body>
</html>