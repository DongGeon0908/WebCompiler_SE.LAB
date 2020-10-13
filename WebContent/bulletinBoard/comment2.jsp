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
	//대댓글을 저장하는 page
	request.setCharacterEncoding("UTF-8");
	commentDAO dao = new commentDAO();

	String id = (String)session.getAttribute("id");
	String text = request.getParameter("comment2");
	int commentNum = Integer.parseInt(request.getParameter("commentNum"));
	int postNum = Integer.parseInt(request.getParameter("postNum"));
	
	if(dao.insertComment2(id, text, commentNum))
		out.println("<script> alert('댓글이 작성되었습니다.'); location.href='showPost.jsp?postNum=t" +postNum+ "'; </script>");
	else
		out.println("<script> alert('오류::잠시후 다시 시도해주세요.'); location.href='showPost.jsp?postNum=t" +postNum+ "'; </script>");
%>
</body>
</html>