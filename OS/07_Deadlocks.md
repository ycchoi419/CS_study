# Deadlocks

## Deadlock(교착상태)

- 일련의 프로세스들이 서로가 가진 자원을 기다리며 block 된 상태
- Resource(자원)
  - 하드웨어, 소프트웨어 등을 포함하는 개념
  - e.g. I/O device, CPU cycle, memory space, semaphore 등
  - 프로세스가 자원을 사용하는 절차
    - request, allocate, use, release



## Deadlock 발생의 4가지 조건

- 4가지 조건을 모두 만족해야 deadlock 발생함.
- Mutual exclusion (상호 배제)
  - 매 순간 하나의 프로세스만이 자원을 사용할 수 있음 
  - 자원을 얻었으면 독점적으로 사용함
- No preemption (비선점)
  - 프로세스는 자원을 스스로 내어놓을 뿐 빼앗기지 않음
- Holt and wait (보유 대기)
  - 자원을 가진 프로세스가 다른 자원ㅇ르 기다릴 때 보유 자원을 놓지 않고 가지고 있음
- Circular wait (순환 대기)
  - 자원을 기다리는 프로세스간에 사이클이 형성되어야 함
  - 프로세스 P0, P1, ..., Pn이 있을 때 
    - P0은 P1이 가진 자원을 기다림
    - Pk는 Pk+1이 가진 자원을 기다림
    - Pn은 P0이 가진 자원을 기다림



### Resource-Allocation Graph(자원할당그래프)

- Vertex

  - process P
  - resource R

- Edge

  - request dege P -> R

    assignment edge R -> P

- 그래프에서 cycle이 없으면 deadlock이 아니다. 
- 그래프에 cycle이 있으면
  - if only one instance per resource type, then deadlock
  - if several instances per resource type, possiblility of deadlock



## Deadlock의 처리 방법

- Deadlock Prevention
  - 자원 할당 시 deadlock의 4가지 필요 조건 중 어느 하나가 만족되지 않도록 하는 것
- Deadlock Avoidance
  - 자원 요청에 대한 부가적인 정보를 이용해서 deadlock의 가능성이 없는 경우에만 자원을 할당
  - 시스템 state가 원래 state로 돌아올 수 있는 경우에만 자원 할당
- Deadlock Detection and recovery
  - Deadlock 발생은 허용하되 그에 대한 detection 루틴을 두어 deadlock 발견시 recover
- Deadlock Ignorance
  - deadlock을 시스템이 책임지지 않음
  - UNIX를 포함한 대부분의 OS가 채택



### Deadlock Prevention

- Mutual Exclusion
  - 공유해서는 안되는 자원의 경우 반드시 성립해야 함 . (배제할 수 있는 조건이 아님)
- Holt and wait
  - 프로세스가 자원을 요청할 때 다른 어떤 자원도 가지고 있지 않아야 한다. 
  - 방법 1. 프로세스 시작 시 모든 필요한 자원을 할당받게 하는 방법 
  - 방법 2. 자원이 필요할 경우 보유 자원을 모두 놓고 다시 요청
- No preemption 
  - process가 어떤 자원을 기다려야 하는 경우 이미 보유한 자원이 선점됨
  - 모든 필요한 자원을 얻을 수 있을 때 그 프로세스는 다시 시작된다. 
  - state를 쉽게 save 하고 restore할 수 있는 자원에서 주로 사용 (CPU, memory)
- Circular Wait
  - 모든 자원 유형에 할당 순서를 정하여 정해진 순서대로만 자원 할당
  - 후순위 자원을 보유중인 경우 선순위 자원을 얻으려면 후순위 자원중을 release 해야 함

> Utilization 저하, throughput 감소, starvation 문제 



### Deadlock Avoidance

- Deadlock Avoidance
  - 자원 요청에 대한 부가정보를 이요애서 자원 할당이 deadlock으로부터 안전한지를 동적으로 조사해서 안정한 경우에만 할당
  - 가장 단순하고 일반적인 모델은 프로세스들이 필요호 하는 각 자원별 최대 사용량을 미리 선언하도록 하는 방법임.
- safe state
  - 시스템 내의 프로세스들에 대한 safe sequence가 존재하는 상태

- safe sequence
  - 프로세스의 sequence <P1, P2, ... , Pn> 이 safe 하려면 Pi의 자원 요청이 "가용 자원 + 모든 Pj(j < i)"에 의해 충족되어야 함
  - 조건을 만족하면 다음 방법으로 모든 프로세스의 수행을 보장
    - Pi의 자원 요ㅗ청이 즉시 충족될 수 없으면 모든 Pj(j < i)가 종료될 때까지 기다린다. 
    - Pi+1이 종료되면 Pi의 자원 요청을 만족시켜 수행한다. 
- 시스템이 safe state에 있으면 => No deadlock
- 시스템이 unsafe state에 있으면 => Possibility of deadlock
- 시스템이 unsafe state에 들어가지 않는것을 보장

- 2가지 경우의 avoidance 알고리즘
  - single instance per resource types
    - Resource Allocation Graph Algorithm 사용
  - multiple instance per resource types
    - Banker's Algorithm 사용



### Resource Allocation Graph algorithm

- Claim edge Pi -> Rj
  - 프로세스 Pi가 자원 Rj를 미래에 요청할 수 있음을 뜻함 (점선)
  - 프로세스가 해당 자원 요청 시 request edge로 바뀜 (실선)
  - Rj가 release되면 assignment edge는 다시 claim edge로 바뀐다. 
- request edge의 assignment edge 변경 시 점선을 포함하여 cycle이 생기지 않는 경우에만 요청 자원을 할당한다. 
- Cycle 생성 여부 조사시 프로세스의 수가 n일 때 O(n^2) 시간이 걸린다. 



### Banker's Algorithm

- 프로세스마다 최대로 사용 가능한 자원의 수를 알고 있음. 
- 항상 최악의 경우를 가정하고 자원을 할당(현재 사용 가능한 자원이 있어도 할당하지 않음)
- 최대 요청을 처리할 수 있을 때에만 요청을 받아들임. 
- 항상 safe state를 유지할 수 있게 함. 



### Deadlock Detection and Recovery

- deadlock을 일단 생기게 놔두고 문제가 생기면 처리하는 방법
- Deadlock Detection
  - Resource type 당 single instance인 경우
    - 자원 할당 그래프에서의 cycle이 곧 deadlock을 의미
  - Resource type 당 multiple instance인 경우
    - banker's 알고리즘과 유사한 방법 활용
- Wait-for graph 알고리즘 
  - resource type 당 single instance인 경우 
  - wait-for graph
    - 자원할당 그래프의 변형
    - 프로세스만으로 node구성
    - Pi가 가지고 있는 자원을 Pk가 기다리는 경우 Pk -> Pi
  - algorithm
    - wait-for graph에 사이클일 존재하는지를 주기적으로 조사
    - O(n**2)의 시간이 걸림 
- Recovery
  - Process termination
    - 프로세스를 종료시키는 방법
    - Abort all deadlocked processes
    - Abort one process at a time until the deadlock cycle is eliminated
  - Resource Preemption
    - 프로세스로부터 자원을 뺏는 방법
    - 비용을 최소화 할 victim의 선정
    - safe state로 rollback하여 process 를 restart
    - Starvation 문제
      - 동일한 프로세스가 계속해서 victim으로 선정되는 경우
      - cost factor에 rollback 횟수도 같이 고려



### Deadlock Ignorance

- Deadlock이 일어나지 않는다고 생각하고 아무런 조치도 취하지 않음
  - Deadlock이 매우 드물게 발생하므로 deadlock에 대한 조치 자체가 더 큰 overhead일 수 있음
  - 만약, 시스템에 deadlock이 발생한 경우 시스템이 비정상적으로 작동하는 것을  사람이 느낀 후 직접 process를 죽이는 등의 방법으로 대처
  - UNIX, windows 등 대부분의 범용 OS가 채택

