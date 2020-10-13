<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="inquiry.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input[type="submit"]{font-family:FontAwesome;}
header{
		background-image:url("../images/bonobono.jpg"); border: 0px solid #000000; margin: 0 auto; padding-top: 20px; padding-right:15%;
		height: 90px; background-color: #303030; color: #ffffff; text-align:right; font-size: 25pt; font-family: fantasy;
	}
	
.left_bx{float: left; width:201px;height:900px;background:right top repeat-y #f9fafa; z-index:2; margin-top:40px;}
.left_bx .qeustion{text-decoration:none; display:block;height:43px;padding-left:22px;background:center no-repeat #4e545d;color:#fff;font-size:14px;line-height:43px}

.left_bx .my_menu{margin-left:5px; width:190px; position:relative;height:44px;border-bottom:1px solid #ebebeb;background:#f0f2f2;font-size:0;z-index:3; padding:0}
.left_bx .my_menu li{position:relative;height:44px;border-bottom:1px solid #ebebeb;background:#f0f2f2;font-size:0}
.left_bx .my_menu li a{display:block; text-decoration:none; padding-left:35px;color:#4e545d;font-size:14px;line-height:44px}

.bodymargin{margin:15px; margin-left:225px; margin-bottom:40px;}

.question{margin-left:15px; background-color:#8080c0; height: 30px; font-size:14pt;}
.inquiryline{padding-top:5px; background-color: #444444; maring-top:15px;margin-bottom:30px;}
.inquirytable{width: 97%; column-span: 1; margin:10px;}
.inquiry_head{display:table; height:25px; border:0 none; padding:10px; padding-left: 30px;}
.inquiry_body{border-top: 1px solid #444444; display:table-cell;  resize: none; padding-top:18px; padding-bottom:60px; font-family: "Nanum Gothic", sans-serif; font-size:10pt;}

.img_icon{width: 15px; height: 15px;}

.h2class{margin:40px;text-align:right}
.inquiryBox>div{
display:none;
}

</style>
<script>

function showOrHide(id){
	var box = document.getElementById(id);
	if(box.style.display!='block')// display 상태가 block이 아니라면 block로 설정 == show
		box.style.display="block";
	else// block라면 none로 설정 == hide
		box.style.display="none";
}
function inquiry(){
	window.name="inquiry"
	 window.open("../inquiry/inquriywriteform.jsp", "inquirywrite", "height = 700, width = 600");
}

</script>
</head>
<body>
<header>
문의 하기
</header>
 <div class="left_bx">
        <a href="../inquiry/inquriy.jsp" class="qeustion" id="onlineConsult">내문의내역</a>
        <ul class="left_menu">
        </ul>
        <ul class="my_menu"> 
                    <li>
                        <a href="" onclick="inquiry()">문의하기</a>
                    </li>
                    <li>
                        <a href="../index.jsp">HOME</a>
                    </li>
        </ul>
</div>
<div class="bodymargin">
<h2 class="h2class">My 문의 사항 내역</h2>

<hr class="inquiryline"/>

<%
	//문의하기 페이지 자신이 문의한 내용 및 답변보기 , 문의하기 기능이 있다
	request.setCharacterEncoding("UTF-8");
	
	String id = (String)session.getAttribute("id");
	inquiryDAO dao = new inquiryDAO();
	inquiryCommentVO vo = new inquiryCommentVO();
	
	ArrayList <inquiryVO> inquiryList= new ArrayList<inquiryVO>(dao.getInquiryList(id));
	
	for(int i=0; i<inquiryList.size(); i++){//본인이 문의한 질문들 출력%>
		<div class ="inquiryBox" onclick="showOrHide('<%=inquiryList.get(i).getNum() %>')">
		<hr/>
			<table class="inquirytable">
			<tr class="inquiry_head">
			<th> 
				문의 내역 : <%=inquiryList.get(i).getTitle() %> 
			<% if(inquiryList.get(i).getComment()==1) //답변이 있다면 제목옆에 답변완료라고 출력
				out.print("답변완료");
			%>
			</th>
			</tr>
			</table>

			<div id='<%=inquiryList.get(i).getNum() %>'>
			<table class="inquirytable">
			<tr class="inquiry_body">
			<td>
				<img src="../images/arrow.svg" class="img_icon"> &nbsp; <%=inquiryList.get(i).getText() %> <!-- 문의내용 출력 -->
			</td>
			</tr>
			</table>
			
				<% if(inquiryList.get(i).getComment()==1){// 답변이 있다면
					vo=dao.getInquiryComment(inquiryList.get(i).getNum());
				%>
				<table class="inquirytable">
				<tr class="inquiry_head">
				<th>
					<%=vo.getTitle() %> || id: <%= vo.getId() %> <!-- 답변제목과 답변자의 id출력 -->
				</th>
				</tr>
				</table>
					<hr>
				<table class="inquirytable">
				<tr class="inquiry_body">
				<td>	
					<img src="../images/arrow.svg" class="img_icon"/> &nbsp; <%= vo.getText() %><!-- 답변내용 출력 -->
				</td>
				</tr>
				</table>
				<p></p>
				 <%}%>
			</div>
		</div>
		<hr>
		<br/>
<%} %>
</div>
</body>
</html>