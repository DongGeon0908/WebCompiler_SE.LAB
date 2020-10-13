<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(session.getAttribute("id")==null)//세션이 만료되었을 경우 DB에 접근 불가 창을 닫음
		out.println("<script>alert('세션이 만료되었습니다. '); window.close(); </script>");
	memberVO vo = new memberVO();
	vo.setId((String)session.getAttribute("id"));
	vo.setPw(request.getParameter("password"));
	
	memberDAO dao = new memberDAO();
	
	boolean result= dao.changePw(vo);
	if(result==true)
	{
		out.println("<script>alert('비밀번호가 변경되었습니다. 다시 로그인 해주세요'); location.href='index.jsp'</script>");
		session.invalidate();// session을 초기화 하고 메인페이지로 이동
	}
	else
	{
		out.println("<script>alert('비밀번호 변경 오류입니다. 다시 시도해주세요.'); history.back();</script>");
	}
	
%>
</body>
</html>