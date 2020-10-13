<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="code.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
*{
color : white;
}
</style>
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	codeVO vo = new codeVO();
	vo.setUser_id((String) session.getAttribute("id"));
	vo.setCodeName(request.getParameter("codeName"));
	vo.setCodeType(request.getParameter("codeType"));
	vo.setCode(request.getParameter("code"));
	
	%>
	Hello World
</body>
</html>