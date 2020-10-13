# SE_editor

SE_editor의 front/back 제작

* 개발환경
    - JAVA : JDK-14.0.1
    - mySQL : mySQL-8.0
    - Tomcat 9.0
    - JSP 
    - JS, JAVA

* DB 
    - 구조
        user -> workSpaceUserData -> workSpace
    
    - 제작일지   
        기본정보를 저장하는 user table을 생성하고 user_id 를 pk로 지정하였다.   
        workSpaceUserData table의 workSpaceId를 pk 및 fk로 지정하여 user table과 연결하였다.   
        workSpaceUserData의 workSpace table의 user_id 값을 fk로 하여 workSpaceId와 연결하였다.   
        user -> workSpaceUserData-> workSpace 세개의 테이블이 계층적으로 연결된 구조이다.   
        중간 table이 없이도 동일한 동작을 하는 DB를 구성 할 수 있으나 추후 추가될 기능에 필요할 수 있을것 같아 구조를 변경하지 않았다.

* index
    - 구성
        login, logout, join, openIDE
        + login   
            DB의 정보와 일치하는지 확인 한 후 session에 저장하는 방식을 이용하였다.   
            보안성에 문제가 있는지 추후 검토 예정이다.
        + logout   
            session을 초기화하는 방식을 선택하였다.   
        + join   
            js를 이용하여 pw값 일치하는지 확인   
            중복확인 클릭시 idCheck.jsp에서 DBIdlist와 사용자가 입력한 id가 중복되는지 확인   
            Email 등 기타 정보들을 마저 입력받고 joinPro.jsp를 통해 입력값을 한번 더 확인한 후 회원가입
        + openIDE   
            새 탭에서 IDE를 엶
        + modify   
            회원정보 수정기능 index -> doModify(비밀번호 확인) -> 회원정보 수정창 노출

* IDE
    - 구성
        editor, run, workSpaceList 3개의 iframe으로 이루어짐
        + editor   
            open source인 monaco editor을 이용함   
            c, java, python, javascript 네가지의 언어를 지원함  
            언어별로 div와 editor을 미리 생성하여 사용자가 언어를 선택할 시 div display값을 변경하는것으로 함   
            workSpaceList 에서 code를 불러왔을 경우 언어에 맞게 editor div를 변경함   
            save클릭시 save.jsp로 이동하여 DB에 code저장 후 알림창 출력, codeName이 중복될 경우 자동으로 update진행, opener.function() 을 이용하여 workSpaceList 새로고침   
        + run   
            server에서 컴파일 결과를 출력하기 위한 form을 제작   
            컴파일부분은 본인이 작업하지 않음
        + workSpaceList   
            DB에 저장된 사용자의 코드들을 언어별로 전부 출력   
            언어를 클릭할 경우 list출력, 재클릭시 list 사라짐   
            codeName 클릭할 경우 editor에 코드를 불러옴   
            del 버튼 클릭시 del.jsp로 data를 전송하여 DB에서 code 삭제 후 알림창 출력, openr.function()을 이용하여 workSpaceList 새로고침