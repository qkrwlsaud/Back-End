<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/css/default.css"/>">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>

	<div>
		<h1 class="subtitle">로그인</h1>
		<hr>

		<form action="memberLogin.do" method="post">
			<table>
				<tr>
					<td>아이디 (이메일)</td>
					<td><input type="text" name="uid"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="upw"></td>
				</tr>
			</table>

			<input type="submit" value="로그인">
			
		</form>

	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>