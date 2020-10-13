<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="algorithm.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");

	if((String)session.getAttribute("authority")==null)//관리자가 아닐경우 index로 돌아감
		 response.sendRedirect("../index.jsp");
	
	algorithmVO vo = new algorithmVO();
	algorithmDAO dao = new algorithmDAO();
	int algorithmNum;
	vo.setName("제목");//default 값
	vo.setExplanation("설명");
	vo.setExInput("exinput");
	vo.setExOutput("exOutpout");
	vo.setInput("realInput");
	vo.setOutput("realOutput");
	%>
	
	<form action="insertAlgorithm.jsp" method="post">
	
	<% if(request.getParameter("algorithmNum")!=null)
		{
			algorithmNum = Integer.parseInt(request.getParameter("algorithmNum"));
			vo= dao.getAlgorithm(algorithmNum); %> <!-- 수정하기를 클릭하였을 경우 번호도 넘겨줌 -->
			<input type ="hidden" name ="algorithmNum" value='<%=algorithmNum %>'>
		<%}%>

<input type="text" name="title" value='<%=vo.getName() %>'>
<select name="category">
	<option value="test">test</option>
	<option value="test2">test2</option>
</select>
<hr>
<textarea name ="explanation" cols="40" rows="8"><%=vo.getExplanation() %></textarea>
<hr>
<textarea name ="exInput" cols="40" rows="4"><%= vo.getExInput()%></textarea>
<textarea name ="exOutput" cols="40" rows="4"><%= vo.getExOutput()%></textarea>
<hr>
<textarea name ="input" cols="40" rows="8"><%= vo.getInput()%></textarea>
<textarea name ="output" cols="40" rows="8"><%= vo.getOutput()%></textarea>
<input type ="submit" value="저장">
</form>

</body>
</html>