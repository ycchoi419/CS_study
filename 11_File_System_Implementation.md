# 11_File System Implementation

## Allocation of File Data in Disk

- contiguous Allocation
- Linked Allocation
- Indexed Allocation

직접 접근이 가능한 매체라고 하더라도 관리 방법에 따라서 순차 접근만 가능하기도 하고 직접 접근이 가능하기도 하다. 



## Contiguous Allocation(연속 할당)

- 하나의 파일이 디스크 상에 연속해서 저장되는 방법
- 디렉토리에는 저장된 파일의 이름, 시작 블록 위치, 블록 길이가 저장된다. 
- 블록이란 디스크를 같은 크기로 나누어 놓은 단위

- 단점
  - external fragmentation 
    - 중간중간에 내용이 들어있지 않은 블록들이 생긴다
  - FIle grow의 어려움
    - 파일의 크기가 중간에 변할 수 있음
    - 파일 생성 시 얼마나 큰 hole을 배당할 것인가
    - grow 가능 vs 낭비(internal fragmentation) - 파일의 크기가 커질 것을 대비해서 어느 정도의 공간을 할당하면 그 공간만큼이 낭비된다. 

- 장점
  - Fast I/O
    - 한 번의 seek/rotation으로 많은 바이트 transfer(그냥 연속적으로 읽으면 됨)
    - realtime file 용으로(deadline이 있고 빠른 I/O 필요), 또는 이미 run 중이던 process의 swapping 용
  - direct access(=random access) 가능 



## Linked Allocation

- directory에 파일의 이름, start 위치, end 위치가 저장됨 
- 각 블록마다 다음 블록의 위치 정보를 저장함
- 중간 위치의 내용을 보려면 앞에서부터 순서대로 다 봐야함
- 직접 접근이 불가능하고 순차 접근만 가능함

- 장점
  - External Fragmentation이 발생 안 함
- 단점
  - No random access
  - reliability 문제
    - 한 sector가 고장나 pointer가 유실되면 많은 부분을 잃음
  - pointer를 위한 공간이 block의 일부가 되어 공간 효율성을 떨어뜨림
    - 보통 512byte/sector 중 4byte가 pointer에 의해 사용됨
- 변형
  - File-Allocatoin table(FAT) 파일 시스템
    - 포인터를 별도의 위치에 보관하여 reliability와 공간 효율성 문제 해결



## Indexed Allocation

직접 접근이 가능하게 하기 위해서 directory에 파일의 위치 정보를 바로 저장하지 않고 index block을 저장하고 index block은 파일이 저장된 위치 정보를 저장한다. 

- 장점
  - External Fragmentation이 발생하지 않음
  - direct access 가능

- 단점

  - small file의 경우 공간 낭비(실제로 많은 file들이 small) : 블록이 최소 2개 필요함

  - too large file의 경우 하나의 block으로 index를 저장하기에 부족

    - 해결 방안

    1. linked scheme
    2. multi-level index



## UNIX 파일 시스템의 구조

- 유닉스 파일 시스템의 중요 개념
  - boot block
    - 부팅에 필요한 정보(bootstrap loader)
    - 0번 위치에 저장됨
  - superblock
    - 파일 시스템에 관한 총체적인 정보를 담고 있다.
    - 어디가 빈 블록이고 어디가 파일이 저장된(사용 중인) 블록인지에 대한 정보가 담겨있음
    - 어디까지 inode list인지 등의 정보도 저장되어 있음
  - inode list
    - 파일 하나 당 inode 하나가 할당이 됨
    - 파일의 메타데이터가 저장됨(파일의 이름은 inode에 있지 않고 디렉 토리에 저장됨)
    - indexed allocation을 사용하지만 크기가 큰 파일의 경우 indirect를 이용해서 포인터의 위치를 저장한 블록의 위치를 저장한다. 
    - inode에는 파일 이름을 제외한 파일의 모든 메타데이터를 저장
  - data block
    - 파일의 실제 내용을 보관
    - 디렉토리 파일은 각각의 파일 이름과 inode 위치를 저장



## FAT File System

- windows 기반
- Linked allocation에서 포인터 정보를 FAT이라는 공간에 저장함
- 총 데이터 블록의 갯수만큼 저장 공간이 필요함. 
- FAT만 살펴보면 중간에 있는 블록도 확인 가능하므로 직접접근이 가능하다. 
- linked allocation의 단점을 모두 해결
- FAT은 중요한 정보이므로 여러 개의 copy를 두어 혹시 bad sector가 발생하더라도 문제가 없다. 



## Free-Space Management

- 파일이 저장되지 않은 비어있는 블록은 어떻게 관리하는가?
- Bit map or bit vector
  - 각각의 블록 별로 번호를 별도의 블록에 사용중인지 아닌지 비트(0또는 1) 정보로 저장한다.
  - 부가적인 공간을 필요로 함
  - 연속적인 n 개의 free block 을 찾는 데 효과적임

- Linked list
  - 모든 free block들을 링크로 연결(free list)
  - 연속적인 가용공간을 찾는 것은 쉽지 않다
  - 공간의 낭비가 없다. 
- Grouping
  - linked list의 변형
  - 첫 번째 free block이 n개의 pointer를 가짐
    - n-1 pointer는 free data block을 가리킴
    - 마지막 pointer가 가리키는 block은 또 다시 n pointer를 가짐
- Counting
  - 프로그램들이 종종 여러 개의 연속적인 block을 할당하고 반납한다는 성질에 착안
  - first free block, number of contiguous free blocks 정보를 저장



## Directory Implementation

- Linear list
  - <file name, file의 metadata>의 list
  - 구현이 간단
  - 디렉토리 내에 파일이 있는지 찾기 위해서는 linear serach 필요 (time consuming)
- Hash Table
  - linear list + hashing
  - Hash table은 file name을 이 파일의 linear list의 위치로 바꾸어줌
  - search time을 없앰
  - Collision 발생 가능
  - 해시 함수에 파일 이름을 넣으면 엔트리가 결과값으로 나옴

- File의 metadata의 보관 위치
  - 디렉토리 내에 직접 보관
  - 디렉토리에는 포인터를 두고 다른 곳에 보관
    - inode, FAT 등

- Loing file name의 지원
  - <file name, file의 metadata>의 리스트에서 각 entry는 일반적으로 고정 크기
  - file name이 고정 크기의 entry 길이보다 길어지는 경우 entry의 마지막 부분에 이름의 뒷부분이 위치한 곳의 포인터를 두는 방법
  - 이름의 나머지 부분은 동일한 directory file의 일부에 존재



## VFS and NFS

- virtual File System (VFS)
  - 서로 다른 다양한 file system에 대해 동일한 시스템 콜 인터페이스(API)를 통해 접근할 수 있게 해주는 OS의 layer
  - 로컬 컴퓨터의 파일 시스템 뿐만 아니라 원격 서버의 파일 시스템에도 접근 가능(NFS)
- Network File System (NFS)
  - 서버와 클라이언트 모두 NFS 모듈이 있어야 지원됨
  -  분산 시스템에서는 네트워크를 통해 파일이 공유될 수 있음
  - NFS는 분산 환경에서 대표적인 파일 공유 방법임



## Page cache and Buffer Cache

- Page Cache
  - Virtual memory의 paging system에서 사용하는 page frame을 caching의 관점에서 설명하는 용어 
  - Memory-Mapped I/O를 쓰는 경우 file의 I/O에서도 page cache 사용
- Memory-Mapped I/O
  - File의 일부를 virtural memory에 mapping 시킴
  - 매핑시킨 영역에 대한 메모리 접근 연산은 파일의 입출력을 수행하게 함
- Buffer Cache
  - 파일 시스템을 통한 I/O 연산은 메모리의 특정 영역인 buffer cache 사용
  - File 사용의 locality 활용
    - 한번 읽어온 block에 대한 후속 요청시 buffer cache에서 즉시 전달
  - 모든 프로세스가 공용으로 사용
  - Replacement algorithm 필요(LRU, LFU 등)
- Unified Buffer Cache
  - 최근의 OS에서는 기존의 buffer cache가 page cache에 통합됨
  - 리눅스의 경우 구현이 되어 있음
  - 512byte의 block 단위가 아닌 4kB 단위의 페이지를 사용함

- 기존의 방식
  - 첫번째 방법 : read, write  시스템 콜을 사용하여 I/O를 함. 항상 운영체제에 요청을 해서 받아와야 함.
  - memory mapped I/O : 읽어온 파일 내용을 buffer cache에서 page cache에 copy 해 놓음(오버헤드 발생). 일단 page cache에 카피된 내용은 시스템 콜을 하지 않고 메모리에 접근하여 사용 가능.
- Unified Buffer cache를 이용한 File I/O
  - 시스템 콜을 하는 경우 : 운영체제가 디스크 파일 시스템에서 읽어와서 카피해서 사용자 프로그램에 전달
  - memory mapped I/O : 자신의 주소 영역 중 일부를 파일에 mapping 하는 단계를 지나고 나면 buffer cache에서 직접 읽고 쓰고 할 수 있음
