# LINUX 서버 필요 코드

### C언어 컴파일러
  ##### 실행구조
  1) 사용자가 코드를 입력 -> 입력된 코드는 서버에 미리 구현된 c파일의 명령문을 통해 복사되어 컴파일 진행 ->
       컴파일 도중 에러 발생한 경우 cError.txt에 기록 -> 정상적으로 컴파일이 수행되면 cResult.txt에 표준출력 기록
  ##### 필요파일    
  1) [코드 명령어 in Linux](https://github.com/DongGeon0908/WebCompiler_SE.LAB/blob/master/linux/src/cMid.c)
  2) 그외 C언어 컴파일할때 필요한 파일
      - cTest.txt
      - cTest.c
      - cResult.txt
      - cError.txt
