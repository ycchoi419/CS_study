# Memory Management

## Logical vs Physical Address

- Memory
  - 주소를 통해 접근하는 매체
- Logical Address(=Virtual Address)
  - 프로세스마다 독립적으로 가지는 주소 공간
  - 각 프로세스마다 0번지부터 시작
  - CPU가 보는 주소는 logical address임
- Physical address
  - 메모리에 실제 올라가는 위치
- 주소 바인딩 : 주소를 결정하는 것
  - Symbolic address -> Logical address -> Physical address
  - logical address가 physical address로 변환되는 시점이 언제인가?



## 주소 바인딩(Address Binding)

- Compile time binding 
  - 물리적 메모리 주소(physical address)가 컴파일 시 알려짐
  - 시작 위치 변경시 재컴파일
  - 컴파일러는 절대 코드(absolute code) 생성 (logical address를 그대로 physical address로 사용)
- Load time binding
  - load될 때(실행 될 때) physical address 생성
  - Loader의 책임 하에 물리적 메모리 주소 부여
  - logical address에 숫자 얼마를 더해서 physical address 생성
  - 컴파일러가 재배치가능코드(relocatable code)를 생성한 경우 가능
- Execution time binding (=Runtime binding)
  - 수행이 시작된 이후에도 프로세스의 메모리 상 위치를 옮길 수 있음
  - CPU가 주소를 참조할 때마다 binding을 점검(address mapping table)
  - 하드웨어적인 지원이 필요 (e.g., base and limit register, MMU)



### Memory-Management Unit (MMU)

-  MMU (Memory-Management Unit)
  - Logical Address를 Physical Address로 매핑해주는 hardware device
- MMU Scheme
  - 사용자 프로세스가 CPU에서 수행되며 생성해내는 모든 주소값에 대해 base register (=relocation register)의 값을 더한다
- User Program
  - logical address만을 다룬다
  - 실제 physical address를 볼 수 없으며 알 필요가 없다. 



### Dynamic Relocation 

- relocation register와 limit register 두 개를 이용해서 주소 변환을 함
- physical memory에서의 프로세스 시작 위치와 특정 프로세스의 logical memory를 더한 값이 physical address가 된다. 
- limit register는 해당 프로그램의 메모리 크기를 나타내며 다른 프로세스의 메모리를 요구하는 것을 방지함



### Hardware Support for Address Translation

- 운영체제 및 사용자 프로세스 간의 메모리 보호를 위해 사용하는 레지스터
  - Relocation register: 접근할 수 있는 물리적 메모리 주소의 최소값(base register)
  - Limit register: 논리적 주소의 범위 

- Limit register를 벗어나는 메모리를 요청한 경우 trap(software interrupt)이 걸리며 CPU 제어권이 운영체제한테 넘어가게 되며 프로세스를 강제 abort 시키는 등의 처리가 이루어짐 



## Some Terminologies(용어 설명)

### Dynamic Loading

- 프로세스 전체를 메모리에 미리 다 올리는 것이 아니라 해당 루틴이 불려질 때 메모리에 load 하는 것
- memory utilization의 향상 (일반적인 프로그램의 경우 거의 사용되지 않는 메모리가 많이 포함되어 있음(예외 처리를 위한 code))
- 가끔씩 사용되는 많은 양의 코드의 경우 유용(예 : 오류 처리 루틴)
- 운영체제의 특별한 지원 없이 프로그램 자체에서 구현 가능(OS는 라이브러리를 통해 지원 가능)
- 우리가 사용하는 운영체제의 경우도 필요한 부분만 메모리에 올려 사용하지만 일반적으로 dynamic loading에 의한 것이 아니라 paging system에 의한 것.(운영체제가 직접 지원)



### Dynamic Linking

- Linking을 실행 시간(execution time)까지 미루는 것

- linking: 여러 곳에 존재하던 컴파일된 파일을 묶어 실행 파일로 만드는 과정

- Static Linking

  - 라이브러리가 프로그램의 실행 파일 코드에 포함됨
  - 실행 파일의 크기가 커짐
  - 동일한 라이브러리를 각각의 프로세스가 메모리에 올리므로 메모리 낭비(e.g. printf 함수의 라이브러리 코드)

  

### Dynamic linking

- 라이브러리가 실행시 연결(link)됨
- 라이브러리 호출 부분에 라이브러리 루틴의 위치를 찾기 위한 stub이라는 작은 코드를 둠
- 라이브러리가 이미 메모리에 있으면 그 루틴의 주소로 가고 없으면 디스크에서 읽어옴
- 운영체제의 도움이 필요 



### Overlays

- 메모리에 프로세스의 부분 중 실제 필요한 정보만을 올림
- 프로세스의 크기가 메모리보다 클 때 사용
- 운영체제의 지원 없이 사용자에 의해 구현
- 작은 공간의 메모리를 사용하던 초창기 시스템에서 수작업으로 프로그래머가 구현(Dynamic loading과 다른점)
  - Manual Overlay
  - 프로그래밍이 매우 복잡



### Swapping

- 프로세스를 일시적으로 메모리에서 backing store로 쫓아내는 것
- Backing store(=swap area)
  - 디스크 : 많은 사용자의 프로세스 이미지를 담을 만큼 충분히 빠르고 큰 저장 공간
- Swap in/ Swap out
  - swap in : 디스크에 저장된 데이터를 메모리로 불러들이는 것
  - swap out: 메모리에 저장된 데이터를 디스크롤 쫓아 내는 것
  - 일반적으로 중기 스케줄러(swapper)에 의해 swap out 시킬 프로세스 선정
  - priority-based CPU scheduling algorithm
    - priority가 낮은 프로세스를 swapped out 시킴
    - priority가 높은 프로세스를 메모리에 올려 놓음
  - Compile time 혹은 load time binding에서는 원래 메모리 위치로 swap in 해야 함
  - Execution time binding에서는 추후 빈 메모리 영역 아무 곳에나 올릴 수 있음
  - swap time은 대부분 transfer time(swap되는 양에 비례하는 시간)임



## Allocation of Physical Memory

> 물리적인 메모리를 어떻게 관리할 것인가

- 메모리는 일반적으로 두 영역으로 나뉘어 사용
  - OS 상주 영역
    - interrupt vector와 함께 낮은 주소 영역 사용
  - 사용자 프로세스 영역
    - 높은 주소 영역 사용
- 사용자 프로세스 영역의 할당 방법 
  - Contiguous allocation : 각각의 프로세스가 메모리의 연속적인 공간에 적재되도록 하는 것
    - fixed partition allocation
    - variable partition allocation
  - Noncontiguous allocation : 하나의 프로세스가 메모리의 여러 영역에 분산되어 올라갈 수 있음
    - paging
    - segmentation
    - paged segmentation



### Contiguous Allocation

- 고정 분할(fixed partition) 방식
  - 물리적 메모리를 몇 개의 영구적 분할(partition)로 나눔
  - 분할의 크기가 모두 동일한 방식과 서로 다른 방식이 존재
  - 분할 당 하나의 프로그램 적재
  - 융통성이 없음
    - 동시에 메모리에 load되는 프로그램의 수가 고정됨
    - 최대 수행 가능 프로그램 크기 제한
  - internal fragmentation 발생(external fragmentation도 발생)
  - 외부 조각 : 분할 크기가 프로그램보다 작아서 사용되지 않음
  - 내부 조각 : 분할 크기그 프로그램보다 커서 사용되지 않는 부분
- 가변분할(variable partition) 방식
  - 프로그램의 크기를 고려해서 할당 
  - 분할의 크기, 개수가 동적으로 변함
  - 기술적 관리 기법 필요
  - external fragmentation 발생
- Hole
  - 가용 메모리 공간
  - 다양한 크기의 hole 들이 메모리 여러 곳에 흩어져 있음
  - 프로세스가 도착하면 수용 가능한 hole을 할당
  - 운영체제는 다음의 정보를 유지
    - 할당 공간 : 사용되고 있는 공간
    - 가용 공간(hole) : 사용되지 않고 있는 공간 
- Dynamic Storage-Allocation Problem : 가변 분할 방식에서 size n인 요청을 만족하는 가장 적절한 hole을 찾는 문제
  - First-fit
    - size가 n 이상인 것 중 최초로 찾아지는 hole 할당
  - best -fit
    - size가 n 이상인 가장 작은 hole을 찾아서 할당
    - hole들의 리스트가 크기순으로 정렬되지 않은 경우 모든 hole의 리스트를 탐색해야 함. 
    - 많은 수의 아주 작은 hole들이 생성됨 
  - worst-fit
    - 가장 큰 hole에 할당
    - 역시 모든 리스트를 탐색해야 함 
    - 상대적으로 아주 큰 hole들이 생성됨 
  - first-fit과 best-fit이 worst-fit보다 속도와 공간 이용률 측면에서 효과적인 것으로 알려짐(실험적인 결과)

- compaction 
  - external fragmentation 문제를 해결하는 한 가지 방법
  - 사용 중인 메모리 영역을 한 군데로 몰고 hole들을 다른 한 곳으로 몰아 큰 block을 만드는 것
  - 매우 비용이 많이 드는 방법임
  - 최소한의 메모리 이동으로 compaction 하는 방법(매우 복잡한 문제)
  - compaction은 프로세스의 주소가 실행 시간에 동적으로 재배치 가능한 경우에만 수행될 수 있다. 



### Noncontiguous allocation

- 실제 현대 컴퓨터에서 사용하는 방법
- Paging
  - 프로세스의 virtual memory를 동일한 사이즈의 page 단위로 나눔
  - virtual memory의 내용이 page 단위로 noncontiguous하게 저장됨
  - 일부는 backing storage에, 일부는 physical memory에 저장
  - Basic method
    - physical memory를 동일한 크기의 frame으로 나눔
    - logical memory를 동일 크기의 page로 나눔 (frame과 같은 크기)
    - 모든 가용 frame들을 관리
    - page table을 사용하여 logical address를 physical address로 변환
    - external fragmentation 발생 안함
    - internal fragmentation 발생 가능
    - page 단위로 나누다 보면 마지막에 자투리가 생기게 됨