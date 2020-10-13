<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mail.*" %>
<%@ page import="member.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//입력받은값과 DB의 값이 같다면 해당 주소로 session이 저장된 링크를 전송해준다.
	request.setCharacterEncoding("utf-8");
	memberVO vo = new memberVO();
	vo.setId((String) request.getParameter("id"));
	vo.setMail((String) request.getParameter("Email"));
	
	memberDAO dao = new memberDAO();
	
	if(dao.checkIdnEmail(vo)==true)// DB의 데이터와 입력값이 같다면 mail로 url전송
		{
		mailSender.sendMail(vo);
		out.println("<script>alert('메일이 전송되었습니다.'); location.href='index.jsp'</script>");
		}
	else
		out.println("<script>alert('입력한 정보와 일치하는 id가 없습니다.'); history.back();</script>");
%>
</body>
</html>