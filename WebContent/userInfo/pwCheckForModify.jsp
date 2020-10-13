<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pw Check</title>
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
</head>
<body>
<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90">
					<span class="login100-form-title p-b-51">
						Pw Check
					</span>
			<form action="modifyForm.jsp" target="_self" method ="post">
			<table>
                <tr>
                <td id="title">Input Password</td>
                    <td>
                        <input type="password" name="pw" class="input100">
                    </td>
                </tr>
			</table>
				<br/>
				<span class="login100-form-title p-b-51">
           	 	<input type="submit" value="Check" class="joinbtn"/>  
           	 	<input type="button" value="Cansle" onclick="goIndex()" class="join2btn"/>
            	</span>
</form>
</body>
</html>