<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="member.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Inquiry</title>
<script>
	function inquiryclose(){
		window.close();
	}
	function selfclose(inquiryform){
		var frm = document.inquiryform;
		console.log(frm);
		frm.target = opener.inquiry;
		frm.submit();
		if(opener != null){
			opener.insert=null;
			self.close();
		} }
</script>
<style>
.inquirywritefrom{margin-left:10%; padding:20px;}
.h4id{float:right;padding-right:5%; font-size:15px;}
.h4body{padding-left:3%}
.tablehead{display:table; width:85%; border-bottom: 2px solid #444444;}
.tableid{display:table; width:85%; font-color: #eff1f1; margin-bottom:-35px;}
.spacebar{word-spacing: 35px;}
.tablebody{display:table; width:85%; border-bottom: 2px solid #eff1f1; padding-bottom:25px;}

.textform{width: 100%; height: 40px; padding: 10px; border: solid 2px #f0f2f2; border-radius: 5px; box-sizing: border-box;}
.textareaform{width: 100%; height: 200px; padding: 10px; box-sizing: border-box; border: solid 2px #f0f2f2; border-radius: 5px; resize: none;}
</style>
</head>
<body>
	<div class="inquirywritefrom" >
			<form action="writeInquiry.jsp" name="inquiryform" target="inquiry"> <!-- 문의하기 form -->
			<div class="tablehead">
				<h2 style="margin-left:15px;">문의 입력</h2>
			</div>
			<div class="tableid">
				<h4 class="h4id"><p class="spacebar">ID <%=(String)session.getAttribute("id") %></span></p></h4>
			</div>
			<div class="tablebody">
				<h4 class="h4body">제목</h4>
				<input type="text" name="title" class="textform">
			</div>
			<br/>
			<div class="tablebody">
				<h4 class="h4body">내용</h4>
				<textarea name ="text" name ="text" class="textareaform"></textarea>
			</div>
			<br/>
			<input type="submit" value="작성" onclick="inquiryclose()">
			<input type="button" value="취소" onclick="inquiryclose()">
			</form>

	</div>
</body>
</html>