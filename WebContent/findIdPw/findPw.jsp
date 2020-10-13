<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pw 찾기</title>
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
				Find Pw
			</span> 
			<form action ="mailSend.jsp">
				 <table>
                <tr>
                <td id="title">Id</td>
                    <td>
                        <input type="text" id="id" name="id" class="input100" placeholder="Id">
                    </td>
                </tr>
                
				<tr>
                <td id="title">Email</td>
                    <td>
                        <input type="text" name="Email" class="input100" placeholder="Email@SE.com">
                    </td>
                </tr>
                </table>
                
				<br/>
				<span class="login100-form-title p-b-51">
           	 	<input type="submit" value="go Email" class="joinbtn"/>  
           	 	<input type="button" value="Cansle" onclick="goIndex()" class="join2btn"/>
            	</span>
			</form>
		</div>
	</div>
</body>
</html>