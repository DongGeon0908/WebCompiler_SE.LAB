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
	//게시글을 저장하는 page
	request.setCharacterEncoding("utf-8");

	bulletinBoardVO vo = new bulletinBoardVO();
	vo.setId( (String)session.getAttribute("id") );
	vo.setName( request.getParameter("name") );
	vo.setText( request.getParameter("text") );
	
	bulletinBoardDAO dao = new bulletinBoardDAO();
	
	boolean result = dao.insertPost(vo);
	
	if(result==true)
		out.println("<script>alert('작성이 완료되었습니다.'); location.href='bulletinBoardList.jsp';</script>");
else
		out.println("<script>alert('오류::작성실패 잠시 후 다시 시도하세요'); history.back();</script>");
%>
</body>
</html>