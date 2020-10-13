<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="algorithm.*"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a, a:link, a:visited {
	text-decoration: none;
	color: black;
}

body {
	margin-top: 0;
}

/* 좌측 메뉴바 css */

.sidenav .list {
	transition: 1s;
	overflow: hidden;
	height: 0;
}

.list a {
	color: #818181;
	margin-left: 50px;
}

.list a:hover {
	color: white;
}

.sidenav {
	height: 100%;
	width: 0;
	position: fixed;
	z-index: 1;
	top: 0;
	left: 0;
	background-color: #111;
	overflow-x: hidden;
	transition: 0.5s;
	padding-top: 60px;
}

.sidenav .category {
	padding: 8px 8px 8px 32px;
	text-decoration: none;
	font-size: 25px;
	color: #818181;
	display: block;
	transition: 0.3s;
}

.sidenav a:hover {
	color: #f1f1f1;
}

.sidenav .closebtn {
	color: #818181; position : absolute;
	top: 0;
	right: 25px;
	font-size: 36px;
	margin-left: 50px;
	position: absolute;
}

@media screen and (max-height: 450px) {
	.sidenav {
		padding-top: 15px;
	}
	.sidenav a {
		font-size: 18px;
	}
}

/* 하단부 설명관련 css */

.title{
	margin-top : 10px;
	margin-button : 5px;
}

.explain{
	height : 150px;
}

.ex{
	padding-left:3px;
	padding-right:5px;
	width:100%;
	height : 150px;
	positon:absolute;
}

.exinput{
	position:relative;
	width : 48%;
	height : 100%;
	display: inline-block;
	border : 2px solid black;
	border-radius:2px;
}

.exoutput{
	position:relative;
	width:48%;
	height : 100%;
	display: inline-block;
	border : 2px solid black;
	border-radius:2px;
}

/* 소스관련 */

.toEditor{
	display:inline;
}

.source{
	display:none;
}
</style>
<script>
	var listACheck = 0;

	function category(id) {
		var box = document.getElementById(id);
		if (listACheck % 2 == 0)// display 상태가 block이 아니라면 block로 설정 == show
			box.style.height = "80px";
		else
			box.style.height = "0";

		listACheck++;
	}
	
	function showOrHide(id){
		var box = document.getElementById(id);
		if(box.style.display!='block')// display 상태가 block이 아니라면 block로 설정 == show
			box.style.display="block";
		else// block라면 none로 설정 == hide
			box.style.display="none";
	}
	
	function openNav() {
		document.getElementById("mySidenav").style.width = "500px";
	}

	function closeNav() {
		document.getElementById("mySidenav").style.width = "0";
	}
	function toIDE(num){
		 var form = document.createElement("form");//폼 생성
	   	  
		 form.setAttribute("charset", "UTF-8");//인코딩 타입
		 form.setAttribute("method", "Post");  //전송 방식
		 form.setAttribute("target", "editor"); //타겟
		 form.setAttribute("action", "editorForAlgorithm.jsp"); //요청 보낼 주소
		   	  
		 var hiddenField = document.createElement("input"); // input 버튼 생성
		 hiddenField.setAttribute("type", "hidden");
		 hiddenField.setAttribute("name", "algorithmNum");
		 hiddenField.setAttribute("value", num);
		 form.appendChild(hiddenField);// form에 추가
		   	  
		 document.body.appendChild(form);//form을 body에 생성
		 form.submit(); //submit
		
	}
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	String id = (String) session.getAttribute("id");
	algorithmDAO dao = new algorithmDAO();
	ArrayList<algorithmVO> algorithmListA = new ArrayList<algorithmVO>(dao.getAlgorithmList("test"));
	%>

	<div id="mySidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<a href="#" class="category" onclick="category('listA')">CategoryA</a>
		<div id="listA" class="list">
			<%
				for (int i = 0; i < algorithmListA.size(); i++) {//코드 리스트 출력
				int result = dao.doesUserTry(id, algorithmListA.get(i).getNum());//시도한적이 있는지 체크
			%>
			<form action="algorithmList.jsp" target="_self" id='list<%=algorithmListA.get(i).getNum()%>' method='post'>
				<input type="hidden" name="num" value='<%=algorithmListA.get(i).getNum()%>'> 
				<a href="#" onclick="document.getElementById('list<%=algorithmListA.get(i).getNum()%>').submit();"><%=algorithmListA.get(i).getName()%></a>
				<%
					if (result == 0)
					out.println("<span style='color:red;'>X</span>");
				else if (result == 1)
					out.println("<span style='color:green'>O</span>");
				%>

			</form>
			<%
				}
			%>
		</div>

		<a href="#" class="category">CategoryB</a> <a href="#"
			class="category">CategoryC</a> <a href="#" class="category">CategoryD</a>
	</div>

	<span style="font-size: 30px; cursor: pointer" onclick="openNav()">&#9776;
		open</span>

	<%
		int algorithmNum;

	if (request.getParameter("num") != null) {//코드 설명 input ouput 출력
		algorithmNum = Integer.parseInt(request.getParameter("num"));
		algorithmVO vo = new algorithmVO();
		vo = dao.getAlgorithm(algorithmNum);
	%>

<script>
window.onload=function(){ // 문제 클릭시 ide에 자동으로 문제번호 넘겨줌
	toIDE('<%=algorithmNum%>');
}
</script>
	<div class="main">

		<!-- 알고리즘 설명 div -->
		<div class="title">
			<h3> 제목 : <%=vo.getName()%></h3>
		</div>
		<hr>

		<div class="explain">
			<%=vo.getExplanation()%>
		</div>

		<div class="ex">
			<div class="exinput">
				<%=vo.getExInput()%>
			</div>
			<div class="exoutput">
				<%=vo.getExOutput()%>
			</div>
		</div>
	</div>

	<%
		if (dao.doesUserTry(id, algorithmNum) != -1) // user가 시도한적이 있다면 작성했었던 코드리스트 출력
	{
		ArrayList<userAlgorithmCodeVO> userCodeList = new ArrayList<userAlgorithmCodeVO>();
		userCodeList = dao.getAlgorithmCodeList(id, algorithmNum);
	%>
		<h2> 소스리스트 </h2>
		<hr>
		<div id="sourceList">
		
	<%	for (int i = 0; i < userCodeList.size(); i++) { %>

		<!-- 소스보기 옆에 결과를 같이 출력해줌 // 소스보기 클릭시 코드내용 출력 -->
		<div> 
		<%
		if (userCodeList.get(i).getResult() == 1)
			out.println("<span style = 'color: green; '> O</span>");
		else
			out.println("<span style = 'color: red; '> X</span>");
		%>
		<a href="#" onclick="showOrHide('c<%=userCodeList.get(i).getCodeNum()%>')">소스보기</a>
		<form action="editorForAlgorithm.jsp" target="editor" method="post" class = "toEditor">
			<input type="hidden" name="codeNum" value='<%=userCodeList.get(i).getCodeNum()%>'> 
			<input type="submit" value="에디터로 옮기기">
		</form>
		<div id='c<%=userCodeList.get(i).getCodeNum()%>' class="source">
			사용언어 : <%=userCodeList.get(i).getCodeType()%>
			<br>
			<%=userCodeList.get(i).getCode()%>
		</div>
		</div>
	<% } %>
		</div>
	<% }
	}
%>

</body>
</html>