<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String userName=(String) session.getAttribute("username");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요</h1>
	<%
		//스크립트릿 영역 : java 코드 작성 -> 앞으로 사용 x
		Date now=new Date();
	%>
	<!-- 표현식 -->
	<%= now %>
	
	<br>
	
	<h3>
	현재 접속 중인 사용자의 이름 : <%= userName %>
	</h3>
	
	<%
		session.setAttribute("uesrname","KING");
	%>
	<br>
	<a href="session/sessionView01.jsp">sessionView</a>
	
</body>
</html>