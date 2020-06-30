package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//응답 처리 
//1. 서블릿 클래스를 만들기 위해서는 HttpServelt 클래스를 반드시 상속
public class NowServlet extends HttpServlet {


	//2. 요청에 맞는 처리를 위한 메서드를 선택 오버라이딩
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//응답 처리 : HTML 문서를 만든다.
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("\t<head>");
		out.println("\t\t<title>Servlet Test</title>");
		out.println("\t<head>");
		out.println("\t<body>");
		out.println("\t\t<h1>안녕하세요 Servlet 클래스로 응답 처리 된 페이지 입니다.</h1>");
		out.println("\t\t<p>현재 시간은 : + "+new Date()+"</p>");
		out.println("\t</body>");
		out.println("</html>");
	
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	}
	
	
}
