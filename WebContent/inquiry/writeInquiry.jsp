<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="inquiry.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//문의가 DB에 저장되는 page
request.setCharacterEncoding("UTF-8");
String id = (String) session.getAttribute("id");
String title = request.getParameter("title");
String text = request.getParameter("text");
inquiryDAO dao = new inquiryDAO();
if(dao.insertInquiry(id, title, text))
	out.println("<script>alert('작성이 완료되었습니다.'); location.href='inquiry.jsp';</script>");
else
	out.println("<script>alert('작성이 완료되었습니다.'); location.href='inquiry.jsp';</script>");
%>
</body>
</html>