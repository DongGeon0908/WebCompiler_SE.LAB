<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function checkValue()
{    
    if(!document.pwForm.password.value){
        alert("비밀번호를 입력하세요.");
        return false;
    }
    
    // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
    if(document.pwForm.password.value != document.pwForm.passwordcheck.value ){
        alert("비밀번호를 동일하게 입력하세요.");
        return false;
    }
}
</script>
</head>
<body>
<%
	session.setMaxInactiveInterval(150);//세션의 저장시간은 2분30초
	session.setAttribute("id", request.getParameter("id")); // 페이지가 열리면 session 저장
%>
	<form action="changePw.jsp" name="pwForm" onsubmit="return checkValue()">
		<table>
			<tr>
				<td id="title">비밀번호</td>
				<td><input type="password" name="password" maxlength="50">
				</td>
			</tr>

			<tr>
				<td id="title">비밀번호 확인</td>
				<td><input type="password" name="passwordcheck" maxlength="50">
				</td>
			</tr>
		</table>
		<input type="submit" value="비밀번호 변경">
	</form>
</body>
</html>