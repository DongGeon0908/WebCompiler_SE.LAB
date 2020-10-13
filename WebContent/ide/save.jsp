<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="code.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");

	codeVO vo = new codeVO();
	vo.setUser_id((String) session.getAttribute("id"));
	vo.setCodeName(request.getParameter("codeName"));
	vo.setCodeType(request.getParameter("codeType"));
	vo.setCode(request.getParameter("code"));
	
	if(vo.getCode()==null)// ide에서 save.jsp가 두번 호출된다. 첫번째 호출을 무시하기 위한 부분
		return;
	
	codeDAO dao= new codeDAO();
	boolean result;
	String checkResult= dao.codeNameCheck(vo.getUser_id(), vo.getCodeName());
	if(checkResult.equals("true"))//중복되는 코드이름이 없다면 새로 저장
		result = dao.insertCode(vo);
	else// 중복되는 코드 이름이 있다면 내용 변경
		result = dao.updateCode(vo);
 %>
 <script>
 window.onload = function () {// 페이지 로드 시 workSpace를 새로고침하고 페이지를 닫음
	 opener.reloadWorkSpaceList('<%= result %>');
	 window.close();
	}
 </script>
<%-- 	<%=vo.getUser_id()%>
	<%=vo.getCodeName()%>
	<%=vo.getCodeType()%>
	<%
		String tmpCode = vo.getCode();
	String splitCode = "SE_uuugi_jjang_jjang,|SE_uuugi_jjang_jjang";// 이 코드를 기점으로 split 실행
	String[] afterSplitCode = tmpCode.split(splitCode);

 	 for(String c : afterSplitCode)
	System.out.println(c);// 코드 확인용
	   
	%> --%>

</body>
</html>