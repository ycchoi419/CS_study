# 12 Disk Management  and Scheduling

## Disk Structure

- logical block
  - 디스크의 외부에서 보는 디스크의 단위 정보 저장 공간들
  - 주소를 가진 1차원 배열처럼 취급
  - 정보를 전송하는 최소 단위
- Sector
  - logical block이 물리적인 디스크에 매핑된 위치
  - Sector 0은 최외각 실린더의 첫 트랙에 있는 첫 번째 섹터이다. 
  - 디스크를 관리하는 최소 단위 
  - 디스크 내부에서 보는 디스크의 최소 단위 



## Disk Scheduling

- Access time 의 구성
  - Seek time
    - 헤드를 해당 실린더로 움직이는데 걸리는 시간
    - 가장 오래 걸림
  - Rotationary latency
    - 헤드가 원하는 섹터에 도달하기까지 걸리는 회전지연시간
    - seek time의 약 1/10 정도
  - Transfer time
    - 실제 데이터의 전송 시간
- Disk bandwidth
  - 단위 시간 당 전송된 바이트의 수
- Disk Scheduling
  - seek time을 최소화하는 것이 목표
  - Seek time ~ seek distance



## Disk Management

- Physical formatting (low-level formatting)
  - 디스크를 컨트롤러가 읽고 쓸 수 있도록 섹터들로 나누는 과정
  - 각 섹터는 header + 실제 data(보통 512 byte) + trailer로 구성
  - header와 trailer는 sector number, ECC(error-correcting code) 등의 정보가 저장되며 controller가 직접 접근 및 운영
- Partitioning
  - 디스크를 하나 이상의 실린더 그룹으로 나누는 과정
  - OS는 이것을 독립적 disk로 취급 (logical disk)
- Logical formatting
  - 파일 시스템을 만드는 것
  - FAT, inode, free space 등의 구조 포함
- booting
  - ROM에 있는 "small bootstrap loader"의 실행
    - ROM : 전원이 나가도 메모리가 저장되어 있음 
  - sector 0(boot block)을 load 하여 실행
  - sector 0은 "full Bootstrap loader program"
  - OS를 디스크에서 load하여 실행



## Disk Scheduling Algorithm

- FCFS(First Come First Service)
  - 그냥 먼저 들어온 순서대로 처리하는 알고리즘
  - dick head 가 많이 움직이게 되어서 비효율적
- SSTF (Shortest Seek Time First)
  - 현재 헤드 위치에서 가장 가까운 요청을 먼저 처리함
  - Starvation 문제가 발생함
- SCAN
  - disk arm이 디스크의 한쪽 끝에서 다른쪽 끝으로 이동하며 가는 길목에 있는 모든 요청을 처리함
  - 다른 한쪽 끝에 도달하면 역방향으로 이동하며 오는 길목에 있는 모든 요청을 처리하며 다시 반대쪽 끝으로 이동한다
  - 실린더 위치에 따라 대기 시간이 달라진다는 문제점
- C-SCAN
  - circular scan
  - 헤드가 한쪽 끝에서 다른쪽 끝으로 이동하며 가는 길목에 있는 모든 요청을 처리함
  - 다른쪽 끝에 도달했으면 요청을 처리하지 않고 곧바로 출발점으로 다시 이동
  - SCAN보다 균일한 대기 시간

- N-SCAN
  - SCAN의 변형 알고리즘
  - 일단 arm이 한 방향으로 움직이기 시작하면 그 시점 이후에 도착한 job은 되돌아올 때 service
  - SCAN의 경우에 가는 도중에 들어오면 움직이는 방향에 있는 경우 처리함
- LOOK and C-LOOK
  - SCAN, C-SCAN의 경우 무조건 처음부터 끝까지 디스크 헤드가 가는데 LOOK, C-LOOK의 경우 진행방향에 더 이상 처리할 게 없으면 그때 바로 방향을 turn



## Disk-Scheduling Algorithm의 결정

- SCAN, C-SCAN 및 그 응용 알고리즘은 LOOK, C-LOOK 등이 일반적으로 디스크 입출력이 많은 시스템에서 효율적인 것으로 알려져 있음
- File 의 할당 방법에 따라 디스크 요청이 영향을 받음
- 디스크 스케줄링 알고리즘은 필요할 경우 다른 알고리즘으로 쉽게 교체할 수 있도록 OS와 별도로 모듈로 작성되는 것이 바람직하다. 



## Swap-Space Management

- Disk를 사용하는 두 가지 이유
  - memory의 volatile한 특성 : file system
  - 프로그램 실행을 위한 memory 공간 부족 : swap space (swap area)
- Swap-space
  - Virtual memory system에서는 디스크를 memory의 연장 공간으로 사용
  - 파일시스템 내부에 둘 수도 있으나 별도 partition 사용이 일반적
    - 공간 효율성보다는 속도 효율성이 우선
    - 일반 파일보다 훨씬 짧은 시간만 존재하고 자주 참조됨
    - 따라서, block의 크기 및 저장 방식이 일반 파일시스템과 다름

