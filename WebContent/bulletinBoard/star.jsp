<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bulletinBoard.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//추천 증가 or 감소 page
	request.setCharacterEncoding("UTF-8");
	bulletinBoardDAO dao = new bulletinBoardDAO();
	
	String id = (String)session.getAttribute("id");
	int postNum = Integer.parseInt(request.getParameter("postNum"));
	
	int result =dao.star(postNum,id);
	if(result == 1)	
		out.println("<script> alert('Star'); location.href='showPost.jsp?postNum=t" +postNum+ "'; </script>");
	else
		out.println("<script> alert('UnStar'); location.href='showPost.jsp?postNum=t" +postNum+ "'; </script>");
%>
</body>
</html>