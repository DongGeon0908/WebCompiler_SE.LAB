<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="algorithm.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
algorithmVO vo = new algorithmVO();
algorithmDAO dao = new algorithmDAO();

vo.setName(request.getParameter("title"));
vo.setCategory(request.getParameter("category"));
vo.setExplanation(request.getParameter("explanation"));
vo.setExInput(request.getParameter("exInput"));
vo.setExOutput(request.getParameter("exOutput"));
vo.setInput(request.getParameter("input"));
vo.setOutput(request.getParameter("output"));

 if(request.getParameter("algorithmNum")!=null)
	dao.updateAlgorithm(vo, Integer.parseInt(request.getParameter("algorithmNum")));//업데이트를 진행할경우
else
	dao.insertAlgorithm(vo);//추가할 경우
%>
<script>
window.onload = function () {// 저장하고 list로 돌아감
	 location.href="algorithmBoard.jsp";
	}
</script>
</body>
</html>