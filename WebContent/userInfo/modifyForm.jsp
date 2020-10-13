<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="member.*"%>
<!DOCTYPE html>
<html>
<head>
<script>
function pwCheck(){
	if(document.userInfo.password.value != document.userInfo.passwordcheck.value ){
    alert("비밀번호를 동일하게 입력하세요.");
    return false;
}
}
</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
 <script type="text/javascript">
 function goIndex() {
     location.href="../index.jsp";
 }
 </script>
<title>Insert title here</title>
</head>
<body>
	<%
	memberVO vo = new memberVO();
	memberDAO dao = new memberDAO();
	vo.setId((String) session.getAttribute("id"));
	vo.setPw((String) request.getParameter("pw"));
	String loginResult = dao.dologin(vo);
	if ("pwMiss".equals(loginResult)) {
	%>
	out.println("<script>alert('비밀번호가 틀렸습니다.'); history.back();</script>");
	<%
		} else if("idMiss".equals(loginResult)){
			out.println("<script>alert('아이디가 틀렸습니다.'); history.back();</script>");
		}
		else{
	vo = dao.getMemberVO(vo.getId());
	%>
	<div class="container-login100">
		<div class="wrap-Join p-t-50 p-b-90">
			<span class="login100-form-title p-b-51">Modifycation</span> 
			<form action="doModify.jsp" method="post" name="userInfo" onsubmit="return pwCheck()" >
				<table>
					<tr>
						<td>이름</td>
						<td><input type="text" name ="name" value="<%=vo.getName()%>" class="joininput"></td>
					</tr>
					<tr>
						<td>메일주소</td>
						<td><input type="text" name="mail" value="<%=vo.getMail()%>" class="joininput"></td>
					</tr>
					<tr>
						<td>자기소개</td>
						<td><input type="text" name="info" value="<%=vo.getInfo()%>" class="joininput"></td>
					</tr>
					<tr>
						<td>비번변경</td> 
						<td><input type="password" name="password" value="" class="joininput"></td>
					</tr>
					<tr>
						<td>비번확인</td>
						<td><input type="password" name="passwordcheck" value="" class="joininput"></td>
					</tr>
				</table>
					
				<span class="login100-form-title p-b-51">
	           	<input type="submit" value="Join" class="joinbtn"/>  
	            <input type="button" value="Cansle" onclick="goIndex()" class="join2btn"/>
	            </span>
			</form>
		</div>
	</div>
	<%	}
	%>
</body>
</html>