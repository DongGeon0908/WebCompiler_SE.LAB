<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="bulletinBoard.*" %>
<%@ page import ="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
   margin-left:10%;
   margin-right:10%;
   margin-top:5%;
}
.listbtn{
   width:30px;
   color:#000000;
   background: url( "../images/list.svg" ) no-repeat;
   border:0px solid #f0efee;
   margin-left:10px;
}

.heartbtn{
   width:30px;
   color:#000000;
   background: url( "../images/heart_before.svg" ) no-repeat;
   border:0px solid #f0efee;

}

.heartbtn2{
   width:30px;
   color:#000000;
   background: url( "../images/heart-after.svg" ) no-repeat;
   border:0px solid #f0efee;
}

.arrow{
   width:30px;
   color:#f9f9f9;
   background: url( "../images/arrow.svg" ) no-repeat;
   border:0px solid #f0efee;
   margin-left:20px;
}

.right{
   float:right;
   margin-right:18px;
   margin-bottom:18px;
}


#comments{
   margin-left:30px;
   background-color: #f9f9f9;
   -webkit-border-radius: 8px;
   -moz-border-radius: 8px; 
   border-radius: 8px;
}

.cocomment{
   width: 80%; height: 50px; padding:10px; background: #f9f9f9; border: none; font-size: 15pt; color: #63717f; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;
}

.cococomment{
   width: 80%; height: 30px; padding:10px; background: #f9f9f9; border: none; font-size: 15pt; color: #63717f; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;
}

.commentwrite{
   width: 80px; height: 70px; margin-left:10px; padding:10px; background: #f9f9f9; border: none; font-size: 12pt; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; position:absolute;
}
.cocommentwrite{
   width: 80px; height: 50px; margin-left:10px; padding:10px; background: #f9f9f9; border: none; font-size: 12pt; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; position:absolute;
}

.combtn{
   background: #ffffff; border: none; font-size:10pt;
}
</style>
<script>
   function backboard(){
      location.href="bulletinBoardList.jsp";
   }
   

    function createForm(url, postNum){
         var form = document.createElement("form");//폼 생성
         
              form.setAttribute("charset", "UTF-8");//인코딩 타입
              form.setAttribute("method", "Post");  //전송 방식
              form.setAttribute("target", "_self");//타겟의 이름
              form.setAttribute("action", url); //요청 보낼 주소
              
              var hiddenField = document.createElement("input"); // input 버튼 생성
              hiddenField.setAttribute("type", "hidden");
              hiddenField.setAttribute("name", "postNum");
              hiddenField.setAttribute("value", postNum);
              form.appendChild(hiddenField);// form에 추가
              
              document.body.appendChild(form);//form을 body에 생성
              form.submit(); //submit
      }
      function del(postNum)
      {
         createForm("postDel.jsp", postNum);
      }
      
      function update(postNum){
         createForm("postUpdate.jsp", postNum);
      }
      
      function showOrHide(id){
         var box = document.getElementById(id);
         if(box.style.display!='block')// display 상태가 block이 아니라면 block로 설정 == show
            box.style.display="block";
         else// block라면 none로 설정 == hide
            box.style.display="none";
      }
</script>
</head>
<body>
<%
   request.setCharacterEncoding("utf-8");
   bulletinBoardVO vo = new bulletinBoardVO();
   bulletinBoardDAO dao = new bulletinBoardDAO();
   
   int postNum= Integer.parseInt( (String)request.getParameter("postNum").substring(1) );
   String id = (String)session.getAttribute("id");
   vo = dao.getPost(postNum);
   
   int star = dao.checkStar(postNum, id);
%>
<input type="button" class="listbtn" onclick="backboard()">

<div style="margin-top:70px; margin-bottom:40px;">
<form action="star.jsp" method="post">
<input type="hidden" name="postNum" value='<%=postNum %>'>
<%if(star==-1){ %>
<div class="right">
<input type="submit" class="heartbtn" value="">  <%= vo.getStar() %>
</div>
<%} else {%>
<div class="right">
<input type="submit" class="heartbtn2" value=""> <%= vo.getStar() %> 
</div>
<% }%>
</form>
<h2><%= vo.getName() %></h2>
</div>

<span style="float:right;">
작성자: <%= vo.getId() %> 
 글 번호:<%= vo.getNum() %>
<%
   if(id!=null)
      if(id.equals( vo.getId() ) ){
%>
   <input type="button" value = "수정" onclick="update('<%=vo.getNum()%>')">
   <input type="button" value = "삭제" onclick="del('<%=vo.getNum()%>')">
   
<%} %>
</span>
<br/>
<hr>

<div style="padding-top:50px; padding-bottom:30px;">
<%= vo.getText() %>
</div>

<br/>
<hr>
<%if(id!=null){%>
   <form action="comment.jsp" method="post">
   <div><input type="text" name ="comment" class="cocomment" placeholder="댓글을 입력하세요."></input>
   <input type="hidden" name="postNum" value='<%=postNum %>'> 
   <input type="submit" value="작성" class="commentwrite">
   </div>
   </form>
<% }%>
<hr>
<%
   commentDAO cdao = new commentDAO();
   ArrayList<forCommentList> commentList = new ArrayList<forCommentList>(cdao.getCommentList(postNum));
   
   for(int i=0; i<commentList.size(); i++){//댓글 출력 loop
      out.print(commentList.get(i).getId());//id출력
      if(id.equals(commentList.get(i).getId()))//댓글 작성자만 삭제버튼 생성
      {%>
         <form action="delComment.jsp" method="post" style="display:inline">
            <input type="hidden" name ="commentNum" value='<%=commentList.get(i).getCommentNum() %>'>
            <input type="hidden" name="postNum" value='<%=postNum %>'>
            <input type="submit" value="삭제" style="margin-left:200px; border:0px solid #f0efee;">
         </form>
      <%} %>
   <br>
   <input type="button" class="arrow" value=""><%=commentList.get(i).getText() %> <!-- 댓글 내용 출력 -->
   <br>
   <%
       for(int j=0; j<commentList.get(i).getComment2List().size(); j++) // 대댓글이 있을경우
       {%>
         <div id="comments">
         <img src="../images/arrow2.png" style="width:20px; float:20px;"></img>
         
          <%=commentList.get(i).getComment2List().get(j).getId() %><!-- 대댓글 작성자 id 출력 -->
         <% if(id.equals(commentList.get(i).getComment2List().get(j).getId())) { // 대댓글 작성자만 삭제버튼 생성 %>
         <form action="delComment2.jsp" method="post" style="display:inline">
            <input type="hidden" name ="comment2Num" value='<%=commentList.get(i).getComment2List().get(j).getComment2Num() %>'>
            <input type="hidden" name="postNum" value='<%=postNum %>'>
            <input type="submit" value="삭제" style="margin-left:200px; border:0px solid #f0efee;">
         </form>
            <%}%>
         <br>
         <input type="button" class="arrow" value=""><%=commentList.get(i).getComment2List().get(j).getText() %> <!-- 대댓글 내용 출력 -->
         </div>
      <%} %>
      <!-- 대댓글 달기 버튼 -->
      <img src="../images/arrow2.png" style="width:12px; float:12px;"></img>
      <input type="button" class="combtn" value="대댓글달기" onclick="showOrHide('<%=commentList.get(i).getCommentNum() %>')">
         <br><br>
         <div id ='<%=commentList.get(i).getCommentNum()%>' style="display:none">
         <form action="comment2.jsp" method="post">
            <input type="text" name ="comment2" class="cococomment" placeholder="대댓글을 입력하세요."></input>
            <input type="hidden" name ="commentNum" value='<%=commentList.get(i).getCommentNum() %>'>
            <input type="hidden" name="postNum" value='<%=postNum %>'>
            <input type="submit" value="작성" class="cocommentwrite">
         </form>
      </div>
<%} %>
</body>
</html>