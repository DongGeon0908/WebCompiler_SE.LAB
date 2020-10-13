<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="member.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ page import="member.memberDAO" %> 
    
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="../fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="../css/util.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
   
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	memberVO vo = new memberVO();
	vo.setName(request.getParameter("name"));
	vo.setMail(request.getParameter("email"));
	
	memberDAO dao = new memberDAO();
	String id = dao.findId(vo);
	
	if("fail".equals(id))
		out.println("<script>alert('입력하신 정보와 일치하는 id가 없습니다.'); history.back();</script>"); // 입력한 정보와 같은 id가 없으면 알림창 출력 후 되돌아감
	else{
		id=id.substring(0,id.length()-3);
		out.println("<script>alert('입력하신 정보와 일치하는 id 는  "+id+"*** 입니다.'); location.href='../loginForm.jsp';</script>");
		} %>
</body>
</html>