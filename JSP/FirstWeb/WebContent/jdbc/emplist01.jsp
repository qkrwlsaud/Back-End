<%@page import="dao.EmpDao"%>
<%@page import="jdbc.ConnectionProvider"%>
<%@page import="model.Emp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emp DB</title>
<style>
	table{
		width: 80%;
	}
	table>td{
		padding 10px;		
		text-align: center;
	}
</style>

</head>
<body>

<%
	//1. 드라이버 로드
	//2. Connection 생성
	//3.Statement 
	//4. ResultSet
	//5. close()
	
	//1. 데이터 베이스 로드
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	List<Emp> empList=new ArrayList<>();
	
 	String dbUrl="jdbc:oracle:thin:@localhost:1522:orcl";
	String user="scott";
	String pw="tiger"; 
	
 	try{
		//2. Connection 생성
		conn=DriverManager.getConnection(dbUrl, user, pw);
		
		//3.Statement 생성
 		stmt=conn.createStatement();
		String sql="select * from emp order by ename desc";
		
		//select의 결과 Resultset 객체를 담는다
		rs=stmt.executeQuery(sql);

		//결과 출력
		while(rs.next()){
		
			empList.add(
					new Emp(
						rs.getInt("empno"),
						rs.getString("ename"),
						rs.getInt("sal"),
						rs.getString("job")
					));
		
		}
		
		
	}catch(SQLException ex){
		//예외처리 -> 오류 페이지로 이동? redirect? 
	
				
	}
 	finally{
	 	 rs.close();
		stmt.close();
		conn.close();  
		}  
 	
 	
	request.setAttribute("empList", empList);
%>
	
	<jsp:forward page="empList_view.jsp"/>
	

</body>
</html>