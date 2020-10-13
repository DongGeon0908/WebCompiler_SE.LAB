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
	//게시글 수정 form page
	request.setCharacterEncoding("utf-8");
	int postNum= Integer.parseInt( (String)request.getParameter("postNum").substring(0) );
	
	bulletinBoardVO vo = new bulletinBoardVO();
	bulletinBoardDAO dao = new bulletinBoardDAO();
	
	vo= dao.getPost(postNum);
	
%>

<%= request.getParameter("postNum") %>
<form action="doPostUpdate.jsp" method="post">
<input type="text" value='<%=vo.getName() %>'>
<input type="hidden" name="postNum" value='<%=vo.getNum() %>'>
<br>
<textarea rows="8" cols="40" name="text"><%=vo.getText() %></textarea>
<br>
<input type="submit" value="수정">
</form>
</body>
</html>