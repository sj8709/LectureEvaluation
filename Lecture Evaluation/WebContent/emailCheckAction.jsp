<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="util.SHA256" %>
<%@ page import="java.io.PrintWriter" %>

<%
	request.setCharacterEncoding("UTF-8");
	String code = null;
	if(request.getParameter("code") != null) {
		code = request.getParameter("code");
	}
	UserDAO userDAO = new UserDAO();
	String userID = null;
	//로그인 한 상태일때
	if(session.getAttribute("userID") != null) {
		//userID 변수 초기화
		//session 값은 object 형식으로 반환되기에 string으로 형변환 해줘야함
		userID = (String) session.getAttribute("userID");
	}
	if(userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.');");
		script.println("location.href = 'userLogin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	//등록된 사용자의 이메일을 가져옴
	String userEmail = userDAO.getUserEmail(userID);
	//하이퍼링크로 받은 주소가 해쉬값을 적용한 사용자의 이메일과 같은지 비교
	boolean isRight = (new SHA256().getSHA256(userEmail).equals(code)) ? true : false;
	//사용자가 보낸 값이 같을 경우 인증에 성공
	if(isRight == true) {
		userDAO.setUserEmailChecked(userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('인증에 성공했습니다.');");
		script.println("location.href = 'index.jsp'");
		script.println("</script>");
		script.close();
		return;
	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 코드입니다.');");
		script.println("location.href = 'index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
%>