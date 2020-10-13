<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V10</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");

 %>
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90">
					<span class="login100-form-title p-b-51">
						Login
					</span>
					
					<form method="post" action="doLogin.jsp">
						<div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
								<input class="input100" type="text" name="id" id="id" placeholder="ID" required />
								<span class="focus-input100"></span>
						</div>
							<div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
								<input class="input100" type="password" name="pw" id="pw" placeholder="pw" required />
								<span class="focus-input100"></span>
							</div>
						<div class="container-login100-form-btn m-t-17">
							<input class="login100-form-btn" type="submit" value="Login">
						</div>
					</form>
					<div class="sign">
					<form method="post">
					<br/>
						<span class="txt1">Let's</span> &nbsp; <a class="txt1" href="join/join.jsp">Sign up</a>          
						<a class="txt2" href='findIdPw/findPw.jsp'> PW</a>
						<a class="txt2" href='findIdPw/findId.jsp'>forget? ID /</a>
					</form>
					</div>
					
			</div>
		</div>

</body>
</html>