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
//대댓글을 삭제하는 page
request.setCharacterEncoding("UTF-8");

commentDAO dao = new commentDAO();

int comment2Num = Integer.parseInt(request.getParameter("comment2Num"));
int postNum = Integer.parseInt(request.getParameter("postNum"));

if(dao.deleteComment2(comment2Num))
	out.println("<script> alert('댓글이 삭제되었습니다.'); location.href='showPost.jsp?postNum=t" +postNum+ "'; </script>");
else
	out.println("<script> alert('오류:: 잠시후 다시 시도해주세요.'); location.href='showPost.jsp?postNum=t" +postNum+ "'; </script>");
%>
</body>
</html>