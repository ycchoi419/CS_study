# 프로세스 (Process)

## 프로세스의 개념

- Process is a program in execution 
- 실행 중 프로그램을 의미함 
- 프로세스의 문맥(context)
  - 현재 프로세스가 어느 상태까지 진행되고 있는지 
  - CPU 수행 상태를 나타내는 하드웨어 문맥
    - Program Counter
    - 각종 register
  - 프로세스의 주소 공간
    - code, data, stack에 어떤 내용이 들어있는지
    - 메모리와 관련된 부분 
  - 프로세스 관련 커널 자료 구조
    - 운영체제가 프로세스를 관리하는 역할을 하므로 프로세스가 하나 추가될 때마다 PCB를 하나씩 만들어서 관리함
    - PCB(Process Control Block)
    - Kernel stack

- 현대의 컴퓨터에서는 time sharing, multitasking을 통해서 여러 개의 프로세스가 동시에 진행됨. 따라서 프로세스의 문맥을 파악하는 것이 필요해짐. 



## 프로세스의 상태

- 프로세스는 상태(state)가 변경되며 수행된다. 

  - Running
    - CPU를 잡고 instruction을 수행중인 상태 
  - Ready
    - CPU를 기다리는 상태(메모리 등 다른 조건은 모두 만족하고 CPU만 잡으면 수행할 수 있음)
  - Blocked (wait, sleep)
    - CPU를 주어도 당장 instruction을 수행할 수 없는 상태
    - Process 자신이 요청한 event(e.g. I/O)가 즉시 만족되지 않아 이를 기다리는 상태
    - e.g. 디스크에서 파일을 읽어와야 하는 경우
  - Suspended (stopped)
    - 외부적인 이유로 프로세스의 수행이 정지된 상태
    - 프로세스는 통쨰로 디스크에 swap out 된다(by 중기 스케줄러)
    - 예) 사용자가 프로그램을 일시 정지시킨 경우 시스템이 여러 이유로 프로세스를 잠시 중단시킴 (메모리에 너무 많은 프로세스가 올라와 있을 때)

  경우에 따라서 아래 상태를 추가

  - New : 프로세스가 생성중인 상태
  - Terminated : 수행이 끝난 상태

> Blocked : 자신이 요청한 event가 만족되면 Ready
>
> Suspended : 외부에서 resume 해주어야 Active



## Process Control Block (PCB)

- 운영체제가 각 프로세스를 관리하기 위해 프로세스당 유지하는 정보
- 다음의 구성요소를 가짐(구조체로 유지)
  - OS가 관리상 사용하는 정보
    - process state, process ID
    - scheduling information, priority
  - CPU 수행 관련 하드웨어 값
    - program counter, registers
  - 메모리 관련
    - code, data, stack의 위치 정보
  - 파일 관련
    - open file descriptors ...



## 문맥 교환 (Context Switch)

- CPU를 한 프로세스에서 다른 프로세스로 넘겨주는 과정
- CPU가 다른 프로세스에게 넘어갈 때 운영체제는 다음을 수행
  - CPU를 내어주는 프로세스의 상태를 그 프로세스의 PCB에 저장
  - CPU를 새롭게 얻는 프로세스의 상태를 PCB에서 읽어옴 

- System call 이나 Interrupt 발생 시 반드시 context switch가 일어나는 것은 아님 
  - interrupt 또는 system call 발생 후 제어권이 운영체제에 넘어가고, 커널 함수가 종료된 후 보통은 다시 실행 중이던 프로세스로 돌아감(문맥교환 발생 X)
  - timer interrupt 또는 I/O 요청 system call 발생 시 이는 프로세스를 넘기기 위해 발생시킨 interrupt 이므로 문맥교환이 발생함. 

- 문맥 교환이 발생하지 않는 system call 이나 interrupt의 경우에도 CPU 수행정보 등 데이터를 PCB에 저장해야 하지만 문맥교환을 한느 경우 그 부담이 훨씬 큼(e.g. cache memory flush : 문맥교환이 일어나면 프로세스에서 사용하던 캐시 메모리를 모두 삭제해야함)



## 프로세스를 스케줄링하기 위한 큐

- Job queue 
  - 현재 시스템 내에 있는 모든 프로세스의 집합(ready queue + device queue + ...)
- ready queue
  - 현재 메모리 내에 있으면서 CPU를 잡아서 실행되기를 기다리는 프로세스의 집합
- Device queue
  - I/O device 의 처리를 기다리는 프로세스의 집합
- 프로세스들은 각 큐들을 오가며 수행된다. 



## 스케줄러 (Scheduler)

- 각 프로세스 별로 어떤 일을 하고, 얼만큼의 시간동안 할지 정하는 것
- Long-term scheduler (장기 스케줄러 or job scheduler)
  - 시작 프로세스 중 어떤 것들을 ready queue로 보낼지 결정
  - **프로세스에 memory(및 각종 자원)을 주는 문제**
  - degree of Multiprogramming을 제어
  - time sharing system에는 보통 장기 스케줄러가 없음 (무조건 ready)
- Short-term scheduler (단기 스케줄러 or CPU scheduler)
  - 어떤 프로세스를 다음번에 실행시킬지 결정
  - **프로세스에 CPU를 주는 문제**
  - 충분히 빨라야 함 (ms 단위)
- Medium-Term Scheduler(중기 스케줄러 or Swapper)
  - 실제로는 장기 스케줄러가 없기 때문에 메모리는 일단 주고 그걸 조절하기 위해 중기 스케줄러 사용
  - 여유 공간 마련을 위해 프로세스를 통째로 메모리에서 디스크로 쫓아냄
  - 프로세스에게서 memory를 뺏는 문제
  - ㅇegree of Multiprogramming을 제어

