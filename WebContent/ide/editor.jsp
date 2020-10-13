<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="code.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editor For C</title>
<link rel="stylesheet" href="cssForIde.css" />
<style>
header {
	width: 50vw;
	color: white;
	background-color: #1e1e1e;
	padding: 1vh;
}

body {
	margin: 0;
}
</style>

<script>
   var editor;
    
    function createEdtior(ocr, ocrCode){
        var language=getCodeType(); // codeType 가져오기 codeType에 따라 editor의 언어 설정이 바뀜
        if(codeName=='codeName')
           {
           if(language=='c')
              code= "//SE Edtior\n//제목에 공백을 입력하지 말아주세요 \n#include <stdio.h>\n\nint main(void)\n{\n   printf(\"Hello World\");\n   return 0;\n}"; 
           else if(language=='java')
              code= "// SE Editor\n//제목에 공백을 입력하지 말아주세요 \n//class의 이름을 SELAB으로만 가능\n// 밑의 코드가 Default Code\nclass SELAB {\n\n   public static void main(String[] args) {\n      System.out.println(\"Hello World\");\n\n   }\n}";
           else if(language=='python')
              code= "#SE Edtior\n#제목에 공백을 입력하지 말아주세요 \nprint('Hello World')";
           else if(language=='javascript')
              code= "//SE Editor\n//제목에 공백을 입력하지 말아주세요 \nconsol.log(\"Hello World\")";
           }
        if(ocr == 1)//ocr을 이용해서 코드를 불러왔을 경우  코드를 불러옴
           code = ocrCode;
      var remove = document.getElementById('editorDiv');
      if(remove!=null)// 만약 editor div가 있다면 삭제
         document.body.removeChild(remove);
      
          var newDiv = document.createElement('div');
          newDiv.style.height= '90vh';
          newDiv.id= "editorDiv";
          document.body.appendChild(newDiv);// editor div를 동적으로 생성
          // editor
        require.config({ paths: { 'vs': 'https://cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.16.2/min/vs' }});
        require(['vs/editor/editor.main'], function() {
          editor = monaco.editor.create(document.getElementById('editorDiv'), {
            theme: 'vs-dark',
            fontFamily: 'Nanum Gothic Coding',
            automaticLayout: true,
            language: language,
            value: [
              code
            ].join('\n')
          });
        });
    }
    
    function createEdtiorInSelect(){// 셀렉트에서 editor 바꿀경우 바뀌지 않는 버그 수정을 위해 codeType을 초기화 시켜주는 함수를 하나 더 만듬
       codeType=null;
       createEdtior();
    }
   function createForm(targetName, actionURL, save, newFile){//targetName, actionURL, save버튼을 통한 호출인지, newFile버튼을 통한 호출인지 입력받고 form 생성 및 submit
        var form = document.createElement("form");//폼 생성
        
        if(save==true)// save 버튼을 통해서 호출햇을 경우 save유무를 위한 popUp창 생성
           var newWinForSave = window.open("save.jsp", "PopUpWin", "width=200,height=200");//저장완료 창을 위한 새창 만들기
        form.setAttribute("charset", "UTF-8");//인코딩 타입
        form.setAttribute("method", "Post");  //전송 방식
        form.setAttribute("target", targetName);//타겟의 이름
        form.setAttribute("action", actionURL); //요청 보낼 주소
        
        var codeName= document.getElementById("codeName").value;// text창에 있는 value값 가져오기
        if(newFile == true)// newFile 버튼을 통해 이 함수를 호출했을 경우 newFile code인 SE_uuuugi_jjang을 codeName으로 저장
           codeName= 'SE_uuuugi_jjang';
        
        var hiddenField = document.createElement("input"); // input 버튼 생성
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "codeName");
        hiddenField.setAttribute("value", codeName);
        form.appendChild(hiddenField);// form에 추가
        
        if(newFile!=true){// newFile 버튼을 통해 호출하지 않았을 경우(save, run) 폼 추가 생성하여 codeType과 code를 저장
            codeType=getCodeType();
           var hiddenField = document.createElement("input");
           hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", "codeType");
           hiddenField.setAttribute("value", codeType);
           form.appendChild(hiddenField);
        
           if(save!=true){
               form.setAttribute("action", "../shellCompile_basic"); //요청 보낼 주소
           }
           code=getCode();
           var hiddenField = document.createElement("input");
           hiddenField.setAttribute("type", "hidden");
           hiddenField.setAttribute("name", "code");
           hiddenField.setAttribute("value", code);
           form.appendChild(hiddenField);
           }
        
        document.body.appendChild(form);//form을 body에 생성
        form.submit(); //submit
      }
   function newFile(){
      createForm('editor', 'editor.jsp', false, true)
   }
   function save(){
      createForm('PopUpWin', 'save.jsp', true, false);
   }
   function play(){
      createForm('run', 'run.jsp', false, false);
   }
   function getCodeType(){  //select box에 있는 codeType을 가져오는 함수
      if(codeType==null){
         var target = document.getElementById("selectCodeType");
         codeType= target.options[target.selectedIndex].text;
      }
       return codeType;
   }
   function getCode(){ // 코드를 배열에 저장해서 반환함
      var code = new Array();
      var splitCode="D3EA7KG44QW1ER0458";// splitcode 이것을 기점으로 나눌예정
      
      var line= editor.getModel().getLineCount();//라인수
           for(var i=0; i<line; i++)
            code[i]=editor.getModel().getLineContent(i+1)+splitCode;// 라인별 text에 splitcode 더해서 저장 
      return code;
   }
   function reloadWorkSpaceList(value){   //workSpaceList 새로고침
      parent.frames.workSpaceList.location.reload();
      if(value=='true')
         alert("저장 완료");
      else
         alert("error :: 관리자에게 문의주세요");
   }
   </script>

</head>
<body>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/require.js/2.3.6/require.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.16.2/min/vs/loader.js"></script>

	<%
		request.setCharacterEncoding("utf-8");
	String codeName = (String) request.getParameter("codeName");
	if ("SE_uuuugi_jjang".equals(codeName)) /* se_uuuugi_jjang는 newFile()을 실행시켰을 경우에 코드이름으로 지정된다 */
		codeName = null;

	int ocr = 0;
	String ocrCode = "";
	if (request.getParameter("ocr") != null) {
		ocr = Integer.parseInt(request.getParameter("ocr"));
		ocrCode = request.getParameter("ocrCode");
	}
	%>

	<header>
		<input type="button" name="new" value="new" onclick="newFile()" /> <input
			type="button" name="save" value="save" onclick="save()" /> <input
			type="button" name="run" value="Run" onclick="play()" />

		<%
			if (codeName == null) { //codeName이 없을경우 == editor를 새로 열었을때
		%>
		<script type="text/javascript">
      var codeName= 'codeName';
      var code;
      var codeType=null;
      var ocr = '<%=ocr%>';
      var ocrCode = '<%=ocrCode%>';
      
      window.onload = function() {// defualt editor 생성 or OCR을 이용해서 코드를 불러왔을 경우 출력해줌
         createEdtior(ocr,ocrCode);
         };
      </script>

		<input type="text" id="codeName" name="codeName" value="codeName" />

		<select name="selectCodeType" id="selectCodeType"
			onchange="createEdtiorInSelect()">
			<option value="c">c</option>
			<option value="java">java</option>
			<option value="python">python</option>
			<option value="javascript">javascript</option>
		</select>

		<%
			} else {// codeName이 있을경우 == workSpaceList를 이용하여 코드를 불러왔을때
		String user_id = (String) session.getAttribute("id");
		codeDAO dao = new codeDAO();
		String codeType = dao.getCodeType(user_id, codeName);
		String code = dao.getCode(user_id, codeName);// code 불러오기
		%>

		<script>
         var code='<%=code%>';
         var codeType= '<%=codeType%>
			';
			window.onload = function() {// 불러온 언어에 맞게 editor변경
				createEdtior(0, "");
			};
		</script>
		<input type="text" id="codeName" name="codeName" value='<%=codeName%>' />
		<%=codeType%>
		<%
			}
		%>
	</header>
</body>
</html>