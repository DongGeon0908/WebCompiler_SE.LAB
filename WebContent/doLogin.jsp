<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% request.setCharacterEncoding("utf-8"); %>
	<%
	memberVO vo = new memberVO();
	memberDAO dao = new memberDAO();
	String loginResult;
	
	String id=request.getParameter("id");
	String pw=request.getParameter("pw");
	
	vo.setId(id);
	vo.setPw(pw);

	loginResult = dao.dologin(vo);
	
	if("user".equals(loginResult)){
		session.setAttribute("id", vo.getId());
		response.sendRedirect("index.jsp");
	}else if("master".equals(loginResult)){
		session.setAttribute("id", vo.getId());
		session.setAttribute("authority", loginResult);
		response.sendRedirect("index.jsp");
	}
	else if("pwMiss".equals(loginResult)){
		out.println("<script>alert('비밀번호가 틀렸습니다.'); history.back();</script>");
	}else if("idMiss".equals(loginResult)){
		out.println("<script>alert('아이디가 틀렸습니다.'); history.back();</script>");
	}
%>
</body>
</html>