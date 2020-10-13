<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>회원가입 화면</title>
    <%@ page import="member.memberDAO" %> 
    
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="../css/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="../css/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="../css/util.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
    <script type="text/javascript">
    
        // 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
        function checkValue()
        {
            if(!document.userInfo.id.value){
                alert("아이디를 입력하세요.");
                return false;
            }
            if(document.userInfo.idDuplication.value != "idCheck"){
                alert("아이디 중복체크를 해주세요.");
                return false;
            }
            
            if(!document.userInfo.password.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            
            // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
            if(document.userInfo.password.value != document.userInfo.passwordcheck.value ){
                alert("비밀번호를 동일하게 입력하세요.");
                return false;
            }
        }
        
        // 취소 버튼 클릭시 로그인 화면으로 이동
        function goIndex() {
            location.href="../index.jsp";
        }
        
        function idCheck(){
        	 var id = document.userInfo.id.value;
        	 if(id.length<8){
        		 alert("아이디를 8글자 이상으로 입력하십시오");
        		 return false;
        	 }
        	 if(id.length=0 || id==null){
        	  alert("중복체크할 아이디를 입력하십시오");
        	  return false;
        	 }
        	 var url = "idCheck.jsp?id=" + id;
        	 window.open(url, "get", "height = 180, width = 300");
        	}

        function inputIdChk(){		// id 체크되지 않았음을 값에 저장
        	document.userInfo.idDuplication.value = "idUncheck"
        }

    </script>
</head>
<body>
		<div class="container-login100">
			<div class="wrap-Join p-t-50 p-b-90">
					<span class="login100-form-title p-b-51">
						Register
					</span> 
       		 <form method="post" action="joinPro.jsp" name="userInfo" 
                onsubmit="return checkValue()">
            <table>

                <tr>
                                <td>ID</td>
								<td><input type="text" name="id" id="id" onkeydown="inputIdChk()" class="joininput" style="text-decoration:underline;"></td>
								<input type="button" value="ID check" onclick="idCheck()" class="checkbtn" style="float:right;">
								<input type="hidden" name="idDuplication" value="idUncheck">
                </tr>
                <tr>
                    <td id="title">Password</td>
                    <td>
                        <input type="password" name="password" class="joininput" style="text-decoration:underline;">
                    </td>
                </tr>

                <tr>
                
                    <td id="title">Password again</td>
                    <td>
                        <input type="password" name="passwordcheck" class="joininput" style="text-decoration:underline;">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">Name</td>
                    <td>
                        <input type="text" name="name" class="joininput">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">Email</td>
                    <td>
                        <input type="text" name="mail" class="joininput">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">My Infomation</td>
                    <td>
                        <input type="text" name="info" class="joininput"/>
                    </td>
                </tr>
            </table>
            <br>
            <span class="login100-form-title p-b-51">
            <input type="submit" value="Join" class="joinbtn"/>  
            <input type="button" value="Cansle" onclick="goIndex()" class="join2btn"/>
            </span>
        </form>
    </div>
    </div>
</body>
</html>
