<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="member.memberDAO" %> 

<%
 memberDAO dao = new memberDAO();
 int rst = 0;
 String id = (String)request.getParameter("id");
 rst = dao.idCheck(id);
 
%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
    	 // 취소 버튼 클릭시 로그인 화면으로 이동
        function sendId() {
    		opener.document.userInfo.idDuplication.value="idCheck";
			window.close();
        }    
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디 중복 확인</title>
</head>
<body>
<%
if(rst == 1){
%>
<h3>아이디 존재</h3>
<input type='BUTTON' value="확인" onClick='window.close()'>
<!-- 아이디가 이미 존재할때 이미지 (현재 없음) -->
<%}else{ %>
<h3>아이디 사용가능</h3>
<input type='BUTTON' value="사용" onClick='sendId()'>
<input type='BUTTON' value="취소" onClick='window.close()'>
<!-- 아이디가 존재하지 않을 때 이미지 (현재 없음)-->
<%} %>
</body>
</html>