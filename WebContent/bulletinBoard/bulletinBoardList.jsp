<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bulletinBoard.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a, a:link, a:visited{
text-decoration:none;
color:black;
}
input[type="submit"]{font-family:FontAwesome;}

.btnwrite{
  background: transparent;
  color: black;
  border: 1px solid black;
  font-size: 9px;
  letter-spacing: 2px;
  padding: 5px 10px;
  text-transform: uppercase;
  cursor: pointer;
  display: inline-block;
  margin: 5px 10px;
  -webkit-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}
.btnwrite:hover{
  background-color: #5ecc71;
  color: white;
  -webkit-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}

.homebtn{
	display:inline-block;
	padding:4px;
	width:50px !important;
	color:#000000;
	background: url( "../images/home.svg" ) no-repeat;
	border:0px solid #f0efee;
	text-decoration:none;
	margin-left:18px;
	margin-top:18px;
}

</style>
<script>
	function goWrite(){
		location.href="write.jsp";
	}
	
	function goindex(){
		location.href="../index.jsp"
	}
	
</script>
<link rel="stylesheet" href="../css/main.css" />
</head>
<body>
	<input type="button" style="float=:left" onclick="goindex()" class="homebtn">
		<div class="aaa">
 		 <div class="bbb" style="background:#f9f9f9;">
			<form action= "bulletinBoardList.jsp" method="post" target="_self">
					<select name="search" class="sel">
						<option value="title">제목</option>
						<option value="id">ID</option>
					</select>
					&nbsp;
					<input type="text" name="text" placeholder="Search.." style="width:260px; background-color:#f9f9f9;">
					&nbsp;
					<input type="submit" value="&#128269;" style="background-color:#f9f9f9;float:right; margin-right:5px;">
			</form>
 			</div>
 			</div>
<%
	request.setCharacterEncoding("utf-8");
	int pageNum=1;
	if(request.getParameter("pageNum")!=null)
		pageNum=Integer.parseInt(request.getParameter("pageNum"));
	
	bulletinBoardDAO dao = new bulletinBoardDAO();
	ArrayList<forPostList> postList	= new ArrayList <forPostList> ();
	
	if(request.getParameter("text")==null)
		postList=dao.getPostList();
	else if( "id".equals(request.getParameter("search") ) )
		postList=dao.getPostList(request.getParameter("text"),1);
	else 
		postList=dao.getPostList(request.getParameter("text"));
%>

	<table class="sub_news" border="0" cellspacing="0" summary="게시판의 글제목 리스트">
			<colgroup>
				<col width="12">
				<col width="130">
				<col width="20">
				<col width="25">
			</colgroup>
			<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">글쓴이</th>
				<th scope="col">날짜</th>
			</tr>
			</thead>
		<tbody>
	<% 
	int i= pageNum*10-10;
	for(; i<pageNum*10; i++){
		if(i==postList.size())
			break;
	%>
		<tr>
		<td><%=postList.get(i).getNum() %></td>
		<td class="title"> 
		<form method="post" action="showPost.jsp" id='<%="t"+postList.get(i).getNum()%>'> <!-- postName별로 form 생성 form id는 postNum으로 지정하여 유니크값 으로 생성  앞에 t를 붙여주는 이유는 아래의 pageNum과 구분하기 위함-->
		<input type="hidden" name="postNum" value=<%="t"+postList.get(i).getNum()%>> <!-- postNum의 value를 저장할 부분을 생성하나 사이트상에 표시하지는 않음 -->
		</form>
		<a href="#" onclick="document.getElementById('<%="t"+postList.get(i).getNum()%>').submit();"><%=postList.get(i).getName() %> </a></td> <!-- 클릭시 page 이동하여 text 출력 -->
		<td><%=postList.get(i).getId() %></td>
		<td><%=postList.get(i).getTime() %></td>
	<% } %>
	</tr>
	</table>

	<br/>
	
	<input type="button" value="글쓰기" onclick="goWrite()" style="float:right; margin:0px 20px 0px 0px;" class="btnwrite">
	
	<div style="padding-top:30px; padding-left:50%;">
	
	<ul class="ulcss">
	<% 
	int j=pageNum;
	if(j<5)// j=pageNum이면 pageNum보다 작은 page로는 돌아갈 수 없기때문에 j를 조절한다.
		j=1;
	else
		j-=4;
	for(; j<pageNum+10; j++){ // 글의 갯수만큼만 pageNum생성
		if(j-2>=postList.size()/10)
			break;
	%>
		<form method="post" action="bulletinBoardList.jsp" id='<%=j %>'> <!-- postName별로 form 생성 form id는 postName으로 지정하여 유니크값 으로 생성 -->
		<input type="hidden" name="pageNum" value=<%=j %>> <!-- postName의 value를 저장할 부분을 생성하나 사이트상에 표시하지는 않음 -->
		</form>
		<li class="licss"><a href="#" class="pagebtn" onclick="document.getElementById('<%=j %>').submit();"><%=j %></a></li> <!-- 클릭시 page 이동하여 text 출력 -->
		
	<% } %>
	</ul>
	</div>
	
	
</body>
</html>