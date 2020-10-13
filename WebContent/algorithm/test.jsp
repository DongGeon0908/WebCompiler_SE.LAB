<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	
	int num = 0;
	if(request.getParameter("algorithmNum")!=null)
		num=Integer.parseInt(request.getParameter("algorithmNum"));
	out.println(num);
	out.println(request.getParameter("code"));
	out.println(request.getParameter("codeType"));
%>
</body>
</html>