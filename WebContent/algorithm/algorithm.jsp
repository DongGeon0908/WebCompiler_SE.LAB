<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {

	margin: 0;
	padding: 0;
	border: 1;
}

body {
	background-color: #1e1e1e;
	width: 100%;
	height: 100%;
}

iframe {
	margin: 0;
	padding: 0;
	border-width: thin;
	border-radius: 1px;
}

#algorithmList {
	background-color:white;
	width: 35vw;
	height: 98vh;
	float: left;
	display: inline-block;
}

#editor {
	background-color: #1e1e1e;
	width: 64vw;
	height: 98vh;
	display: inline-block;
}

#frame {
	position: absolute;
}

#top {
	position: relative;
	width: 100vw;
	height: 98vh;
}

</style>
</head>
<body>
	<div id="top">
		<iframe src="algorithmList.jsp" id="algorithmList" name="algorithmList"></iframe>
		<iframe src="editorForAlgorithm.jsp" id="editor" name="editor"></iframe>
	</div>

</body>
</html>