<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<%@ page import="code.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.button1 {
	color: black;
}

* {
	color: white;
}

.listTitle{
	margin-left:0vw;
}
.listDiv {
	display: none;
}

a, a:link, a:visited {
	text-decoration: none;
	margin-left:5vw;
}

</style>
<link rel="stylesheet" href="../css/ide.css" />
<script>
	function showOrHide(codeList) {
		if (codeList.style.display != 'block')// display 상태가 block이 아니라면 block로 설정 == show
			codeList.style.display = "block";
		else
			// block라면 none로 설정 == hide
			codeList.style.display = "none";
	}

	function del(codeName) {

		var newWinForSave = window.open("del.jsp", "PopUpWin","width=200,height=200");//저장완료 창을 위한 새창 만들기
		var form = document.createElement("form");
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method", "Post"); //Post 방식
		form.setAttribute("target", "PopUpWin");//저장이되면 새창 출력
		form.setAttribute("action", "del.jsp"); //요청 보낼 주소

		var hiddenField = document.createElement("input"); // codeName 넘기기 위해 form 생성
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "codeName");
		hiddenField.setAttribute("value", codeName);
		form.appendChild(hiddenField);

		document.body.appendChild(form);
		form.submit();

	}
	function reloadWorkSpaceList(value)// 삭제시에만 사용되는 function workSpaceList 새로고친다.
	{
		location.reload();
		if (value == 'true')
			alert("삭제완료");
		else
			alert("error:: 관리자에게 문의주세요");
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
	String user_id = (String) session.getAttribute("id");

	codeDAO dao = new codeDAO();
	ArrayList<String> codeListC = new ArrayList<String>(dao.getCodeList(user_id, "c")); // 언어별로 codeList 생성
	ArrayList<String> codeListJava = new ArrayList<String>(dao.getCodeList(user_id, "java"));
	ArrayList<String> codeListPython = new ArrayList<String>(dao.getCodeList(user_id, "python"));
	ArrayList<String> codeListJavascript = new ArrayList<String>(dao.getCodeList(user_id, "javascript"));
	%>
	<div class="listTitle" onclick="showOrHide(codeListC)">C</div>
	<!-- list 제목을 나타내는 부분 클릭시 list hide or show 실행 -->
	<div class="listDiv" id="codeListC">
		<table>
			<%
				for (String i : codeListC) {
			%>
			<tr>
				<td id = "td1">
					<form method="post" action="editor.jsp" id='<%=i%>' target="editor">
						<!-- codeName별로 form 생성 form id는 CodeName으로 지정하여 유니크값 으로 생성 -->
						<input type="hidden" name="codeName" value=<%=i%>>
						<!-- codeName의 value를 저장할 부분을 생성하나 사이트상에 표시하지는 않음 -->
					</form> <a href="#" onclick="document.getElementById('<%=i%>').submit();"><%=i%>
				</a>
				</td>
				<!-- 클릭 시 editor로 코드 이름을 전송하여 editor에 코드 불러오기 -->
				<td id = "td2"><input class="button1" type="button" value="del" onclick="del('<%=i%>')"></td>
			</tr>
			<!-- 클릭시 del.jsp로 전송하여 DB에서 코드 삭제 -->
			<!-- codeName을 web에 띄워줌과 동시에 링크로 생성해줌 -->
			<%
				}
			%>
		</table>
	</div>

	<hr>
	<div class="listTitle" onclick="showOrHide(codeListJava)">java</div>
	<div class="listDiv" id="codeListJava">
		<table>
			<%
				for (String i : codeListJava) {
			%>
			<tr>
				<td>
					<form method="post" action="editor.jsp" id='<%=i%>'
						target="editor">
						<input type="hidden" name="codeName" value=<%=i%>>
					</form> <a href="#" onclick="document.getElementById('<%=i%>').submit();"><%=i%>
				</a>
				</td>
				<td><input class="button1" type="button" value="del"
					onclick="del('<%=i%>')"></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>

	<hr>
	<div class="listTitle" onclick="showOrHide(codeListPython)">python</div>
	<div class="listDiv" id="codeListPython">
		<table>
			<%
				for (String i : codeListPython) {
			%>
			<tr>
				<td>
					<form method="post" action="editor.jsp" id='<%=i%>'
						target="editor">
						<input type="hidden" name="codeName" value=<%=i%>>
					</form> <a href="#" onclick="document.getElementById('<%=i%>').submit();"><%=i%>
				</a>
				</td>
				<td><input class="button1" type="button" value="del"
					onclick="del('<%=i%>')"></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>

	<hr>
	<div class="listTitle" onclick="showOrHide(codeListJavascript)">javaScript</div>
	<div class="listDiv" id="codeListJavascript">
		<table>
			<%
				for (String i : codeListJavascript) {
			%>
			<tr>
				<td>
					<form method="post" action="editor.jsp" id='<%=i%>'
						target="editor">
						<input type="hidden" name="codeName" value=<%=i%>>
					</form> <a href="#" onclick="document.getElementById('<%=i%>').submit();"><%=i%>
				</a>
				</td>
				<td><input class="button1" type="button" value="del"
					onclick="del('<%=i%>')"></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>