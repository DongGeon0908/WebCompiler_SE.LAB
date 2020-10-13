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
//게시글을 삭제하는 page
request.setCharacterEncoding("utf-8");

bulletinBoardDAO dao = new bulletinBoardDAO();

if (dao.deletePost(Integer.parseInt( request.getParameter("postNum") ) )==true)
	out.println("<script>alert('삭제가 왼료되었습니다.'); parent.location.reload(); </script>");
else
	out.println("<script>alert('오류입니다. 다시 시도해주세요'); history.back();");

%>
</body>
</html>