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
<%
	memberVO vo = new memberVO();
	vo.setId((String)session.getAttribute("id"));
	vo.setName(request.getParameter("name"));
	vo.setMail(request.getParameter("mail"));
	vo.setInfo(request.getParameter("info"));
	vo.setPw(request.getParameter("password"));
	
	memberDAO dao = new memberDAO();
	boolean result = dao.modifyUserInfo(vo);
	
	if(result==true)
			out.println("<script>alert('회원정보가 수정되었습니다.'); location.href='../index.jsp';</script>");
	else
			out.println("<script>alert('회원정보 수정에 실패하였습니다'); history.back();</script>");
%>
</body>
</html>