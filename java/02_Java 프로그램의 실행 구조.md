# 02_Java 프로그램의 실행 구조

### 2-1 환경변수 설정

javac.exe, java.exe를 다른 디렉토리에서도 실행할 수 있도록 하기 위해 환경 변수(Path)에 bin 경로를 등록한다.



### 2-2 Java 컴파일러와 JVM

java 소스 파일(.java)을 자바 컴파일러(javac.exe)가 바이트코드 파일(.class)로 컴파일하고 JVM(java.exe)을 통해 기계어로 바뀌고 실행된다. 

메모리 로딩 / 실행 준비 / 실행 결정 / 초기화 과정을 LINK라고 한다. 

이클립스와 같은 IDE를 사용하면 이 모든 과정을 알아서 해주며 컴파일, 디버깅, 실행까지 쉽게 할 수 있다. 



### 2-3 가비지 컬렉터(Garbage Collector)

프로그램 실행에 필요한 메모리를 Garbage Collector가 자동으로 관리한다. 