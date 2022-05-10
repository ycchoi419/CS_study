# Process Synchronization

## 데이터의 접근

- 컴퓨터 시스템 안에서 데이터가 접근하는 패턴
- 어떤 경로에 있던지 데이터가 저장되어 있는 위치가 있고 읽어와서 연산하고 연산 결과를 다시 데이터가 있던 위치에 저장
- 하나의 메모리를 여러 개의 프로세스가 접근하려고 할 때 : Race condition(경쟁 상태)
- 멀티프로세서 시스템에서는 race condition의 문제가 발생할 수 있음 



## OS에서 race condition은 언제 발생하는가?

- kernel 수행 중 인터럽트 발생 시
  - 인터럽트를 disable / enable 하게 해서 해결. 작업이 끝난 다음에 인터럽트 가능하게 함. 
- process가 system call을 하여 kernel mode로 수행 중인데 context switch가 일어나는 경우
  - 두 프로세스의 adress space 간에는 data sharing이 없음 
  - 그러나 system call 을 하는 동안에는 kernel address space의 data를 access하게 됨(share)
  - 이 작업 중간에 CPU를 preempt해가면 race condition 발생
  - 해결책 : 커널 모드에서 수행 중일 때는 CPU를 preempt하지 않고 다시 사용자모드로 돌아갈 때 preempt되게 한다. 
- Multiprocessor에서 shared memory 내의 kernel data
  - multiprocessor의 경우 인터럽트 enable, disable로 해결되지 않음
  - 방법 1 : 한 번에 하나의 CPU만이 커널에 들어갈 수 있게 하는 방법(커널 전체를 lock 하는 것이라 비효율적)
  - 방법 2: 커널 내부에 있는 각 공유 데이터 에 접근할 때마다 그 데이터에 대한 lock/unlock을 하는 방법





## Process Synchronization 문제 

- 공유 데이터의 동시 접근은 데이터의 불일치 문제(inconsistency)를 발생시킬 수 있다. 
- 일관성(consistency) 유지를 위해서는 협력 프로세스(cooperating process) 간의 실행 순서를 정해주는 메커니즘이 필요하다. 
- **Race Condition**
  - 여러 프로세스들이 동시에 공유 데이터를 접근하는 상황
  - 데이터의 최종 연산 결과는 마지막에 그 데이터를 다룬 프로세스에 따라 달라짐
- race condition을 막기 위해서는 concurrent process는 동기화되어야 한다. 



## The Critical-Section Problem

- n개의 프로세스가 공유 데이터를 동시에 사용하기 원하는 경우
- 각 프로세스의 code segment에는 공유 데이터를 접근하는 코드인 critical section이 존재
- problem
  - 하나의 프로세스가 critical section에 있을 때 다른 모든 프로세스는 critical section에 들어갈 수 없어야 한다. 

- 프로세스가 수행되는 도중에 CPU를 빼앗길 수 있기 때문에 문제가 발생한다. 



### Critical Section Problem 해결법의 충족 조건

- Mutual Exclusion
  - 프로세스 Pi가 critical section 부분을 수행 중이면 다른 모든 프로세스들은 그들의 critical section에 들어가면 안된다. 
- Progress
  - 아무도 critical section에 있지 않은 상태에서 critical section에 들어가고자 하는 프로세스가 있으면 critical section에 들어가게 해 주어야 한다. 
- Bounded Waiting
  - 프로세스가 critical section에 들어가려고 요청한 후부터 그 요청이 허용될 때까지 다른 프로세스들이 critical section에 들어가는 횟수에 한계가 있어야 한다. 



### Algorithm 1

- 변수를 설정해서 누구 차례인지를 알려줌. 
- 자기 차례일 때가 아닐 때에는 while문을 계속 돌고 자기 차례가 오면 critical section에 들어감 
- 자기 차례가 끝나면 변수를 바꿔서 다른 프로세스의 차례로 바꾸어 줌 
- 문제점 : Progress 조건을 만족하지 못함. 제대로 동작하는 알고리즘이 아님



### Algorithm 2

- 각각의 프로세스에 대해 플래그 변수를 사용
- 상대방의 플래그 변수 상태를 모두 확인한 후 아무도 없으면 critical section으로 들어감.
- 나오면 자신의 플래그 변수를 다시 false로 바꿔줘서 다른 프로세스가 들어갈 수 있게 만듦.

- 문제점 : 플래그가 true인 상태에서 아직 critical section에 들어가지 않고 CPU를 뺏긴 경우 아무도 critical section에 들어가지 못함.

   

### Algorithm 3

- 잘 작동하는 알고리즘 
- 둘 다 critical section에 들어가고 싶은 경우에만 차례를 따지고 아니면 그냥 들어감. 
- Busy waiting (=spin lock)! (계속 CPU와 memory를 쓰면서 wait)



### Synchronization Hardware

- 데이터를 읽으면서 쓰는 것이 동시에 가능하다면 문제는 간단하게 해결됨 
- 하드웨어적으로 test & modify를 atomic 하게 수행할 수 있도록 지원하는 경우 앞의 문제는 간단히 해결
- Test_and_set(a) : a를 읽으면서 동시에 a의 값을 1로 할당함.



## Semaphore

- 앞의 방식들을 추상화시킴
- Semaphore S
  - integer variable
  - P연산, V 연산 두 가지 과정에 의해 접근 가능
    - P 연산 : 공유 데이터를 획득하는 과정
    - V 연산 :  다 쓰고 데이터를 반납하는 과정
- 추상적으로 연산을 정의하는 것
- 여기서도 busy waiting 문제가 발생함



## Block & Wakeup Implementation

- semaphore를 정수값(critical section에 접근할 수 있는 프로세스의 갯수)과 wait queue로 정의
- block과 wakeup을 다음과 같이 가정
  - block
    - 커널은 block을 호출한 프로세스를 suspend시킴 
    - 이 프로세스의 PCB를 semaphore에 대한 wait queue에 넣음
  - wakeup(P)
    - block된 프로세스 P를 wakeup 시킴
    - 이 프로세스의 PCB를 ready queue로 옮김
- Semaphore 연산이 다음과 같이 정의됨
  - P 연산
    -  S.value 1 빼줌(critical section에 접근할 수 있는 프로세스 갯수)
    - S.value < 0이 되면 해당 프로세스 block
  - V(S)
    - S.value 1더해줌
    - S.value <=0 이면(기다리는 프로세스가 있으면) 기다리는 프로세스를 ready queue에서 제거하고 wakeup 시킴

> 일반적으로 busy waiting보다 block and wakeup이 효율적이지만 critical section의 길이가 매우 짧은 경우는 반대가 되기도 한다. 



## Deadlock and Starvation

- Deadlock
  - 둘 이상의 프로세스가 서로 상대방에 의해 충족될 수 있는 event를 무한히 기다리는 현상
  - P0, P1 모두 S, Q 두 자원이 필요한데 서로 하나씩 가지고 있는 경우 나머지 하나를 무한히 기다림
  - 자원 얻는 순서를 맞추면 해결할 수 있음
- Starvation 
  - indefinite blocking. 프로세스가 suspend된 이유에 해당하는 semaphore queue에서 빠져나갈 수 없는 현상. 

