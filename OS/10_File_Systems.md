# File Systems

## File and File Systems

- File
  
  - 이름을 통해 접근하는 정보 단위
  
  - A named collection of related information
  
  - 일반적으로 비휘발성의 보조기억장치에 저장
  
  - 운영체제는 다양한 저장 장치를 file이라는 동일한 논리적 단위로 볼 수 있게 해줌
  
  - Operation
    - create, read, write, reposition (lseek), delete, open(메타데이터를 올려놓음), close 등
  
    > reposition: 파일이 크기 때문에 필요에 따라서 파일의 어느 위치를 읽거나 쓰는지가 중요한데 그 위치를 수정해주는 연산이 lposition(또는 reposition)이라고 한다. 
  
- File attribute ( 혹은 파일의 metadata)
  - 파일 자체의 내용이 아니라 파일을 관리하기 위한 각종 정보들
    - 파일 이름, 유형, 저장된 위치, 파일 사이즈
    - 접근 권한 (읽기/쓰기/실행), 시간(생성/변경/사용), 소유자 등

- File system
  - 운영체제에서 파일을 관리하는 부분
  - 파일 및 파일의 메타데이터, 디렉토리 정보 등을 관리
  - 파일의 저장 방법 결정 
  - 파일 보호 등



## Directory and Logical Disk

- Directory
  - 파일의 메타 데이터 중 일부를 보관하고 있는 일종의 특별한 파일
  - 그 디렉토리에 속한 파일 이름 및 파일 attribute 등
  - operation
    - search for a file, create a file, delete a file
    - list a directory, rename a file, traverse the file system
  - Partition(=Logical Disk)
    - 논리적 디스크
    - 하나의 (물리적) 디스크 안에 여러 파티션을 두는게 일반적
    - 여러 개의 물리적인 디스크를 하나의 파티션으로 구성하기도 함
    - (물리적) 디스크를 파티션으로 구성한 뒤 각각의 파티션에 **file system**을 깔거나 **swapping** 등 다른 용도로 사용할 수 있음



## open()

- 파일의 메타데이터를 메인 메모리 상에 올려 놓는 것
- open("/a/b")
  - 디스크로부터 파일 c의 메타데이터를 메모리로 가지고 옴
  - 이를 위하여 directory path를 search
    - 루트 디렉토리 "/"를 open하고 
  - 사용자 메모리 영역에서 open()을 위한 system call을 한다. (CPU 제어권이 운영체제로 넘어감)
    - root의 metadata가 커널 메모리 영역에 올라감(메타데이터를 통해 root의 content에 접근할 수 있음)
    - root의 content(디렉토리 내 파일의 메타데이터가 저장되어 있음)를 통해 a의 metadata에 접근하고 a의 메타데이터는 커널 메모리 영역에 올라감
    - 같은 방식으로 b의 메타데이터가 커널 메모리에 올라감
    - b파일을 오픈하게 되면 커널 메모리 영역의 Process A의 PCB에 b의 file descriptor가 저장됨
    - 사용자 메모리에서 파일 내용을 직접 읽는 것이 아니라 운영체제가 내용을 저장해놓고 copy해서 사용자 메모리에 전달함. 그렇기 때문에 같은 데이터를 또 요청할 시 디스크에 접근하지 않고 커널 메모리(버퍼 캐시)에서 바로 넘겨줄 수 있다. 
    - process의 PCB : per-process file descriptor table
    - Open file table : system-wide open file table



## File Protection

- 각 파일에 대해 누구에게 어떤 유형의 접근(read/write/execution)을 허락할 것인가?
- Access Control 방법
  - Access control Matrix
    - 어떤 사용자가 어떤 파일에 대해 어떤 권한이 있는지 행렬로 표시
    - Access control list : 파일별로 누구에게 어떤 접근 권한이 있는지 표시
    - Capability: 사용자별로 자신이 접근 권한을 가진 파일 및 해당 권한 표시
    - 위 두 방법은 linked-list 구조를 이용해서 matrix에 비해 메모리 이득
  - Grouping
    - 매트릭스를 이용하면 부가적인 오버헤드가 너무 큼
    - 전체 user를 owner, group, public의 세 그룹으로 구분
    - 각 파일에 대해 세 그룹의 접근 권한을 3비트씩으로 표시
    - 예) UNIX
  - Password
    - 파일마다 password를 두는 방법 (디렉토리 파일에 두는 방법도 가능)
    - 모든 접근 권한에 대해 하나의 password: all-or-nothing
    - 접근 권한별 password를 따로 둬야 하는데 이를 암기하고 관리하는 문제가 발생함



## File System의 Mounting

- 각각의 논리적 디스크에는 파일 시스템을 설치해서 사용할 수 있음
- 만약 다른 디스크의 파일 시스템에 접근해야 한다면?
- Mounting
  - root file system의 파일 이름에다가 또 다른 파티션에 있는 파일 시스템을 마운트 해주면 다른 파티션의 파일 시스템에 접근할 수 있음 



## Access Methods

- 파일에 접근하는 방법
- 시스템이 제공하는 파일 정보의 접근 방식
  - 순차 접근(sequential access)
    - 카세트 테이프를 사용하는 방식처럼 접근
    - 읽거나 쓰면 offset은 자동적으로 증가
    - 중간의 데이터를 거쳐서 다른 데이터로 접근할 수 있음
  - 직접 접근(direct access, random access)
    - LP 레코드 판과 같이 접근하도록 함
    - 파일을 구성하는 레코드를 임의의 순서로 접근할 수 있음