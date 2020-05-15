<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="evaluation.EvaluationDAO" %>
<%@ page import="likey.LikeyDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%!
	//현재 접속한 사용자의 ip주소를 데이터베이스에 기록
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip==null || ip.length()==0) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip==null || ip.length()==0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip==null || ip.length()==0) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
%>
<%
	String userID = null;
	// 현재 사용자가 로그인한 상태(세션값이 유용한 상태) 경우 userID에 해당 세션값을 넣음
	if(session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	//사용자가 로그인하지 않은 상태
	if(userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.');");
		script.println("lcoation.href = 'userLogin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	request.setCharacterEncoding("UTF-8");
	String evaluationID  = request.getParameter("evaluationID");;
	if(request.getParameter("evaluationID") != null) {
		evaluationID = request.getParameter("evaluationID");
	}
	EvaluationDAO evaluationDAO = new EvaluationDAO();
	LikeyDAO likeyDAO = new LikeyDAO();
	int result = likeyDAO.like(userID, evaluationID, getClientIP(request));
	if(result == 1) {
		System.out.println("여기");
		System.out.println(result);
		result = evaluationDAO.like(evaluationID);
		if(result == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('추천이 완료되었습니다.');");
			script.println("location.href='index.jsp'");
			script.println("</script>");
			script.close();
			return;
		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.');");
			script.println("history.back()");
			script.println("</script>");
			script.close();
			return;
		}
	} else  {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 추천을 누른 글입니다.');");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		return;
	}
%>