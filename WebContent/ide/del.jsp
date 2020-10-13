<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="code.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	String id, codeName;
	id= ((String) session.getAttribute("id"));
	codeName= request.getParameter("codeName");

	//if(vo.getCode()==null)// ide에서 save.jsp가 두번 호출된다. 첫번째 호출을 무시하기 위한 부분
		//return;
	
	codeDAO dao= new codeDAO();
	boolean result = dao.deleteCode(id,codeName);
 %>
 <script>
window.onload = function () {// 페이지 로드 시 workSpace를 새로고침하고 페이지를 닫음
	 opener.reloadWorkSpaceList('<%=result%>');
	window.close();
}
 </script>
</body>
</html>