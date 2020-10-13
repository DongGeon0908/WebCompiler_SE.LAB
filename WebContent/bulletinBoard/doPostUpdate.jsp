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
//게시글의 내용을 변경하는 page
request.setCharacterEncoding("utf-8");

bulletinBoardDAO dao = new bulletinBoardDAO();

int postNum= Integer.parseInt(request.getParameter("postNum"));

boolean result = dao.updatePost(postNum, (String)request.getParameter("text"));
if(result==true)
	out.println("<script> alert('수정되었습니다.'); location.href='showPost.jsp?postNum=t" +postNum+ "'; </script>");
else
	out.println("<script> alert('오류:: 잠시후 다시 사도해주세요'); location.href='bulletinBoard.jsp'; </script>");
%>
</body>
</html>