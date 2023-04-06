<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>c:set 사용하기</title>
</head><!--  -->
<body>
	<%-- jsp 에서 스트립트릿을 되도록 안쓰기 위함 --%>
	<c:set var="msg"> 안녕하세요</c:set> <!-- 안녕하세요라는 변수 -->
	<h1><c:out value="${msg}"></c:out></h1>
	<h1>${msg}</h1>
	
	<%-- 자신의 이름을 변수에 저장한 후(c:set 사용) 출력--%> 
	<c:set var="name"> 박소망</c:set>
	이름: <c:out value="${name}"></c:out><br> <!-- 아래와 출력은 같다  -->
	이름: ${name}<br><br>
	
	<c:set var="price"> 1000</c:set>
	가격: $${price}<br>
	가격: $<c:out value="${price}"></c:out><br><br>
	
	<%-- jsp 에서 스트립트릿을 되도록 안쓰기 위함 --%>
	<c:set var="gage"> <i>짜장네 호프</i></c:set>
	<h1><c:out value="${gage}"/></h1><br><!-- <i>가 문자로 출력 -->
	<h1><c:out value="${gage}" escapeXml="false"/></h1><br><!-- escapeXml="false"하면 <i>가 문자로 나오지않고 태그적용된다 -->
	<h1>가게: ${gage}</h1><br><!-- <i>가 문자로 나오지않고 태그적용된다 -->
	
</body>
</html>














